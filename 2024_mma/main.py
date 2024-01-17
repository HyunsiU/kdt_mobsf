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

if __name__ == '__main__':
    analyzer_type = input('Application Analyze type (Static[S]/Dynamic[D]): ').lower()
    # analyzer_type = 's'
    
    SERVER = 'http://localhost:8000'
    APIKEY = '65d6e9dc7fa1251bf799ed5e50811107a1576e97184491cd70b093a30f90bf7a'
    print("file select")
    FILE = filedialog.askopenfilename(initialdir = './',
                                        title = "파일선택",
                                        filetypes = (("*.apk","*apk"),("전체보기", "*.*")))
    
    if (analyzer_type == 's'):
        s = static(APIKEY, SERVER)

        RESP = s.upload() # 기본적인 apk 업로드
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
        pass
    else :
        print("Wrong input")
        exit()
    
    

    

    
    
