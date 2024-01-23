import json
import requests
from requests_toolbelt import MultipartEncoder
from tkinter import filedialog
from tkinter import messagebox
import file_analyze



class static:

    def __init__(self, APIKEY, SERVER) :
        self.APIKEY = APIKEY
        self.SERVER = SERVER

    def upload(self):
        # file upload
        print("Uploading file")
        multipart_data = MultipartEncoder(fields={'file': (FILE, open(FILE, 'rb'), 'application/octet-stream')})
        headers = {'Content-Type': multipart_data.content_type, 'Authorization': APIKEY}
        response = requests.post(SERVER + '/api/v1/upload', data=multipart_data, headers=headers)
        print(response.text)
        return response.text

    def scan(self, data):
        # Scan the file
        print("Scanning file")
        post_dict = json.loads(data)
        headers = {'Authorization': self.APIKEY}
        response = requests.post(self.SERVER + '/api/v1/scan', data= post_dict, headers=headers)
        
        #print(response.text)
    
    def pdf(self, data):
        # Generate pdf report
        print("Generate PDF report")
        headers = {'Authorization': self.APIKEY}
        data = {"hash": json.loads(data)["hash"]}
        response = requests.post(self.SERVER + '/api/v1/download_pdf', 
                                 data=data,
                                 headers=headers,
                                 stream=True)
        
        with open("report.pdf", 'wb') as f:
            for chunk in response.iter_content(chunk_size=1024):
                if chunk:
                    f.write(chunk)
        print("Report saved as report.pdf")


class dynamic:

    def __init__(self, APIKEY, SERVER) :
        self.APIKEY = APIKEY
        self.SERVER = SERVER

    def dynamic_get_app(self, app_name):
        headers = {'Authorization': self.APIKEY}
        response = requests.get(self.SERVER + '/api/v1/dynamic/get_apps',
                     headers=headers).json()

        
        for i in response['apps']:
            if(i['FILE_NAME'] == app_name):
                return i['MD5']
            

    
    def dynamic_start(self, app_hash):
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash}
        response = requests.post(self.SERVER + '/api/v1/dynamic/start_analysis',
                                 data=data,
                                 headers=headers)
        #print(data)
        #print(response.text)
    
    def dynamic_frida(self, app_hash, fridacode):
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash,
                'default_hooks':'api_monitor,ssl_pinning_bypass,root_bypass,debugger_check_bypass',
                'auxiliary_hooks':'',
                'frida_code':fridacode}
        response = requests.post(self.SERVER + '/api/v1/frida/instrument',
                                 data=data,
                                 headers=headers)
    
    def dynamic_stop(self, app_hash):
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash,}
        response = requests.post(self.SERVER + '/api/v1/dynamic/stop_analysis',
                                 data=data,
                                 headers=headers)
    
    def dynamic_report_json(self, app_hash):
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash,}
        response = requests.post(self.SERVER + '/api/v1/dynamic/report_json',
                                 data=data,
                                 headers=headers).json()
        
        with open('./dynamic_report.json', 'w') as f:
            json.dump(response, f, indent=2, sort_keys=True)
        
    



if __name__ == '__main__':
    analyzer_type = input('Application Analyze type (Static[S]/Dynamic[D]): ').lower()
    #analyzer_type = 'd'
    
    SERVER = 'http://localhost:8000'
    APIKEY = '4abeecd72a14e6d537f5701b8bca47bcf488dcb0a2d68c134e029f8b374ef840'
    
    
    if (analyzer_type == 's'):
        s = static(APIKEY, SERVER)
        print("file select")
        FILE = filedialog.askopenfilename(initialdir = './',    
                                        title = "파일선택",
                                        filetypes = (("*.apk","*apk"),("전체보기", "*.*")))
        
        RESP = s.upload() 
        main_RESP = RESP
        s.scan(RESP)

        analyze = file_analyze.analyze(FILE)
        
        analyze.unpack() # 어플리케이션 파일 unpack()
        print('application tree')
        analyze.app_tree(0) # 어플리케이션 수상한 파일 탐지 및 app tree

        if(analyze.analyze_file):
            print('수상한 파일 존재')
        for file in analyze.analyze_file:
            if('.apk' in file): # 앱안에 앱이 발견시 업로드 및 스캔
                print("※ 앱 안에 에플리케이션 발견 업로드 및 스캔")
                FILE = file
                RESP = s.upload()
                s.scan(RESP)

            elif ('.dex' in file): # dex 파일 발견시 decrypt 
                print('※ dex 파일 발견')
                analyze.aes_decrypt(b'dbcdcfghijklmaop')

        analyze.repack()
        FILE = './repack.apk'
        RESP = s.upload()
        s.scan(RESP)



    elif (analyzer_type == 'd'):
        d = dynamic(APIKEY, SERVER)        
        app_hash = d.dynamic_get_app('sample.apk')
        with open('./firda script.js') as f:
            fridacode =f.read()
        print("app 동적 분석 시작")
        d.dynamic_start(app_hash)

        print("frida 우회 시작")
        d.dynamic_frida(app_hash, fridacode)

        input("동적 클릭 종료대면 입력")

        print('dynamic 종료')
        d.dynamic_stop(app_hash)

        print('dynamic json report')
        d.dynamic_report_json(app_hash)
        pass
    else :
        print("Wrong input")
        exit()
    
    

    

    
    
