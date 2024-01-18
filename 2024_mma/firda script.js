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

bypass_locale()
bypass_tags()
bypass_superuser()
bypass_proc()