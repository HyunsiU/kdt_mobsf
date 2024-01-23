function bypass_locale(){
  Java.perform(function(){
    var getlanguage = Java.use('java.util.Locale').getLanguage.overload()
    getlanguage.implementation = function(){
      return 'ko' 
    }
  })

}

function bypass_tags(){
  Java.perform(function(){
    var contains = Java.use('java.lang.String').contains.overload('java.lang.CharSequence')
    contains.implementation = function(compare_str){
      if(compare_str == 'test-keys'){
        return false 
      } else{
        return contains.call(this, compare_str)
      }
    }
  })
}

function bypass_superuser(){
  Java.perform(function(){
    var file_class = Java.use('java.io.File').$init.overload('java.lang.String')
    file_class.implementation = function(arg){
      if(arg == '/system/app/Superuser.apk'){
        return file_class.call(this, '/nothing')
      } else{
        return file_class.call(this, arg)
      }
    }
  })
}

function bypass_proc(){
  Java.perform(function(){
    var index_of = Java.use('java.lang.String').indexOf.overload('java.lang.String')
    index_of.implementation = function(str){
      if(str == 'goldfish'){
        return -1
      } else {
        return index_of.call(this, str)
      }
    }
  })
}

function bypass_secure(){
  Java.perform(function(){
    var getint = Java.use('android.provider.Settings$Secure').getInt.overload('android.content.ContentResolver', 'java.lang.String', 'int')
    getint.implementation = function(cr, name, flag) {
      if(name == 'adb_enabled'){
        return 0
      } else{
        getint.call(this, cr, name, flag)
      }
    }
  })
  
}

function bypass_proc2(){
  Java.perform(function(){
    var sys = Java.use('java.lang.System')
    var get = sys.getProperty.overload('java.lang.String')
    get.implementation = function(key){
      if(key == 'http.proxyHost' || key =='http.proxyPort' ){
        console.log(key)
        return null
      } else {
      return get.call(sys, key)
      }
    
    }
  })
}

function bypass_network(){
  Java.perform(function(){
    var equals = Java.use('java.lang.String').equals.overload('java.lang.Object')
    equals.implementation = function(compare){
      if(compare == "tun0" || compare == 'ppp0'){
        return false
      } else{
        return equals.call(this, compare)
      }
      
    }
  })
}

bypass_locale()
bypass_tags()
bypass_superuser()
bypass_proc()
bypass_secure()
bypass_proc2()
bypass_network()