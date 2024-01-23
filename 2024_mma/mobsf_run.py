from requests_toolbelt import MultipartEncoder
import requests
import json

class static:
    # api 키 및 server 주소 
    def __init__(self, APIKEY, SERVER) -> None:
        self.APIKEY = APIKEY
        self.SERVER = SERVER
    
    # mobsf static upload
    def upload(self, file):
        # file upload
        print("Uploading file")
        multipart_data = MultipartEncoder(fields={'file': (file, open(file, 'rb'), 'application/octet-stream')})
        headers = {'Content-Type': multipart_data.content_type, 'Authorization': self.APIKEY}
        response = requests.post(self.SERVER + '/api/v1/upload', 
                                 data=multipart_data, 
                                 headers=headers)

        return response.text
    
    def scan(self, data):
        # Scan the file
        print("Scanning file")
        post_dict = json.loads(data)
        headers = {'Authorization': self.APIKEY}
        response = requests.post(self.SERVER + '/api/v1/scan', 
                                 data= post_dict, 
                                 headers=headers)

    def pdf(self, data, file_name):
        # Generate pdf report
        print("Generate PDF report")
        headers = {'Authorization': self.APIKEY}
        data = {"hash": json.loads(data)["hash"]}
        response = requests.post(self.SERVER + '/api/v1/download_pdf', 
                                 data=data,
                                 headers=headers,
                                 stream=True)
        
        with open(file_name+".pdf", 'wb') as f:
            for chunk in response.iter_content(chunk_size=1024):
                if chunk:
                    f.write(chunk)
        print("Report saved as "+file_name+".pdf")

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
        print("app 동적 분석 시작")
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash}
        response = requests.post(self.SERVER + '/api/v1/dynamic/start_analysis',
                                 data=data,
                                 headers=headers)

    
    def dynamic_frida(self, app_hash, fridacode):
        print("frida 우회 시작")
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash,
                'default_hooks':'api_monitor,ssl_pinning_bypass,root_bypass,debugger_check_bypass',
                'auxiliary_hooks':'',
                'frida_code':fridacode}
        response = requests.post(self.SERVER + '/api/v1/frida/instrument',
                                 data=data,
                                 headers=headers)
    
    def dynamic_stop(self, app_hash):
        print('dynamic 종료')
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash,}
        response = requests.post(self.SERVER + '/api/v1/dynamic/stop_analysis',
                                 data=data,
                                 headers=headers)
    
    def dynamic_report_json(self, app_hash):
        print('dynamic json report')
        headers = {'Authorization': self.APIKEY}
        data = {'hash':app_hash}
        response = requests.post(self.SERVER + '/api/v1/dynamic/report_json',
                                 data=data,
                                 headers=headers).json()
        
        with open('./dynamic_report.json', 'w') as f:
            json.dump(response, f, indent=2, sort_keys=True)
    

