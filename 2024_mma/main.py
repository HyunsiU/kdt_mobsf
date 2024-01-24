import json
import mobsf_run
import file_analyze

if __name__ == '__main__':
    with open('./config.json') as f:
        config = json.load(f)
    
    server = config['server']
    apikey = config['api_key']
    analyze_app_path = config['app_path']

    while 1:
        analyze_mode = input('Application Analyze type (Static[S]/Dynamic[D])').lower()

        if(not(analyze_mode == 's' or analyze_mode == 'd')):
            print("Wrong input")
        else:
            break
    
    if(analyze_mode =='s'):
        # sample.apk 업로드 및 스캔 pdf 생성
        static = mobsf_run.static(apikey, server)
        response = static.upload(analyze_app_path)
        static.scan(response)
        static.pdf(response, './sample_apk')

    
        # apk 파일 unpack 후 aec decrypt, repack
        aes_key = config['aes_key'].encode('utf-8')
        analyze_file = file_analyze.analyze(analyze_app_path)
        analyze_file.unpack()
        analyze_file.app_tree('depack', False)
        
        if(analyze_file.analyze_file):
            print("수상한 파일 존재: ", analyze_file.analyze_file)
            count = 0
            for file in analyze_file.analyze_file:
                if('.dex' in file):
                    # classes.dex file 외 의 파일이 있는경우 decrypt후 repack 하여 파일 올려 스캔 후 pdf
                    count +=1
                    decrypt_file = 'classes'+str(count)+'.dex'
                    analyze_file.aes_decrypt(file, decrypt_file, aes_key)
                    analyze_file.repack()

                    response = static.upload('./repack.apk')
                    static.scan(response)
                    static.pdf(response, './repack.apk')
                    
                    pass
                elif('.apk' in file):
                    # 앱 안에 앱이 있을경우 업로드 및 스캔, pdf 
                    print(file+' mobsf 업로드')
                    pdf_name = file.split('/')[-1:][0]
                    
                    response = static.upload(file)
                    static.scan(response)
                #     static.pdf(response, './'+pdf_name)
        pass
    else:
        dynamic = mobsf_run.dynamic(apikey, server)
        app_hash = dynamic.dynamic_get_app('sample.apk')
        dynamic.dynamic_start(app_hash)
        with open(config['frida_file'], 'r') as f: 
            frida_script = f.read()
            
        dynamic.dynamic_frida(app_hash, frida_script)
        input("동적 클릭 종료대면 입력")
        dynamic.dynamic_stop(app_hash)
        dynamic.dynamic_report_json(app_hash)
