function Hook(){
  
}

function bypass_locale(){
	Java.perform(function(){
    	var local = Java.use(Java.utill.Locale).getLanguage.overload()
        local.getLanguage.implementation = function(){
          return "ko"
        };
    })
}