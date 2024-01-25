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
        
        static = mobsf_run.static()
        static.main()
        pass

    else:
        apk_name = input("APK Name >> ")
        dynamic = mobsf_run.dynamic(apikey, server)
        app_hash = dynamic.dynamic_get_app(apk_name)
        dynamic.dynamic_start(app_hash)
        with open(config['frida_file'], 'r') as f: 
            frida_script = f.read()
            
        dynamic.dynamic_frida(app_hash, frida_script)
        input("동적 클릭 종료대면 입력")
        dynamic.dynamic_stop(app_hash)
        dynamic.dynamic_report_json(app_hash)
