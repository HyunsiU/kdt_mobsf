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

bypass_locale
bypass_tags()