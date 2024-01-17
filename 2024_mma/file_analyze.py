import os
import shutil
from Crypto.Cipher import AES

class analyze:
    def __init__(self, file_path):
        self.file_path = file_path
        self.analyze_file = list()

    def unpack(self):
        print("application apktool unpack")
        shutil.copyfile(self.file_path, self.file_path+'.zip')
        command = 'java -jar apktool.jar d -s ' + self.file_path+'.zip' + " -o depack"
        os.system(command)
    
    def app_tree(self, output):
        print('app tree')
        folder_name = 'depack'
        self.dir_file('./'+folder_name, 0, output)

    def dir_file(self, folder, count, output):
        dirs = os.listdir(folder)
        if(output):
            print(' '*count+folder)
        for dir in dirs:
            if(os.path.isfile(folder+'/'+dir)):
                if(output):
                    print('  '*count+'ㄴ '+ dir)

                if('.apk' in folder+'/'+dir ):
                    self.analyze_file.append(folder+'/'+dir)
                elif('.dex' in folder+'/'+dir):
                    if(not 'classes.dex'  == dir):
                        self.analyze_file.append(folder+'/'+dir)
            else:
                count+=1                
                self.dir_file(folder+'/'+dir, count, output)
                count-=1

    def aes_decrypt(self, key):
        print('kill-classes.dex file decrypt')
        with open('./depack/kill-classes.dex', 'rb') as f:
            data = f.read()
        
        cipher = AES.new(key, AES.MODE_ECB)
        dec = cipher.decrypt(data)
        print("kill-classes.dex decrpt file to classes2.dex create")
        with open('./depack/classes2.dex', 'wb') as f:
           f.write(dec)
    
    def repack(self):
        print("repack.apk create")
        command = 'java -jar apktool.jar b depack -o repack.apk'
        os.system(command)
        

        
if __name__ == "__main__":
    analy = analyze('./malware.apk')
    analy.unpack()
    print('application tree')
    analy.app_tree(0)
    print('수상한 파일')
    print(analy.analyze_file)