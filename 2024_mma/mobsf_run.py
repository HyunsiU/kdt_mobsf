from requests_toolbelt import MultipartEncoder
import os
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.backends import default_backend
import requests
import json


class static:
    def __init__(self) -> None:
        self.api_key = '4abeecd72a14e6d537f5701b8bca47bcf488dcb0a2d68c134e029f8b374ef840'
        self.visit_apk = dict()
    def aes_decrypt(key, encrypted_data):
        key_bytes = key.encode('utf-8')
        cipher = Cipher(algorithms.AES(key_bytes), modes.ECB(), backend=default_backend())
        decryptor = cipher.decryptor()
        decrypted_data = decryptor.update(encrypted_data) + decryptor.finalize()

        return decrypted_data

    def dir_decryption(self, dir_name, depth):
        dirs = os.listdir(dir_name)
    
        for i, dir in enumerate(dirs):
            pathName = os.path.join(dir_name, dir)
            if str(pathName).endswith("dex"):
                try:
                    f = open(pathName, 'rb')
                    data = f.read()
                    f.close()
                    if data[0] == 0x64 and data[1] == 0x65 and data[2] == 0x78:
                        print("PASS " + pathName)
                    else:
                        print("Decryted " + pathName)
                        os.remove(f.name)
                        key = "dbcdcfghijklmaop"
                        data = self.aes_decrypt(key, data)
                        new_link = os.path.join(dir_name, 'classes{i}.dex'.format(i=i))
                        f2 = open(new_link, 'wb')
                        f2.write(data[:-4])
                        f2.close()
                except Exception as e:
                    print(e) 
            elif str(pathName).endswith(".apk"):
                self.analyze_apk(pathName, False)
                print(pathName)
        
            if os.path.isdir(pathName):
                self.dir_decryption(pathName, depth + 1)

    def depack(self, apk_name, depack_name):
        print(apk_name, depack_name)
        res = os.popen("java -jar apktool.jar d -s {apk_name} -o {depack_name}".format(apk_name=apk_name, depack_name=depack_name)).read()
        print(res)
        print()

    def repack(self, depack_name, repack_name):
        res = os.popen("java -jar apktool.jar b {depack_name} -o {repack_name}".format(depack_name=depack_name, repack_name=repack_name)).read()
        print(res)
        print()

    def upload_apk(self, apk_name):   
        with open(apk_name, 'rb') as file_pointer:    
            resp = requests.post('http://localhost:8000/api/v1/upload', headers={"Authorization":self.api_key}, files={'file':file_pointer})     
            print(resp, 'upload result')
            return json.loads(resp.text)
        
        

    def scan_apk(self, resp):
        print(resp)
        new_resp = requests.post('http://localhost:8000/api/v1/scan',  headers={"Authorization":self.api_key}, data={'scan_type':resp['scan_type'], 'file_name':resp['file_name'], 'hash':resp['hash']})
        print(new_resp)
        scan_result = json.loads(new_resp.text)
        print(scan_result['android_api'])


    def analyze_apk(self, apk_name, raw):
        try:
            if self.visit_apk[apk_name] == True:
                return
        except:
            pass
        
        self.visit_apk[apk_name] = True
        
        names = apk_name.split('\\')
        
        apk_path = '\\'.join(names[:-1])
        apk_name = names[-1]
        
        
        if apk_name == 'repack.apk':
            return
        
        print(apk_name)
        
        if(raw == True):
            resp = self.upload_apk('./'+apk_name)
            self.scan_apk(resp)
        

        print(apk_path, apk_name)
        
        depack_name = (apk_path + '\\depack').lstrip('\\')
        print(depack_name)
        repack_name = (apk_path + '\\repack.apk').lstrip('\\')
        print(repack_name)
        
        self.depack((apk_path + '\\{apk_name}'.format(apk_name=apk_name)).lstrip('\\'), depack_name)
        self.dir_decryption(depack_name, 0)
        self.repack(depack_name, repack_name)
        
        resp = self.upload_apk(repack_name)
        self.scan_apk(resp)


    def main(self):
        apk_name = input("APK Name >> ")
        self.analyze_apk(apk_name, True)

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
    

