package com.techskaud.sampleapp.service


// this class handles the instance of singleton class
// this class is required if we want to init singleton class with some parameter which is not possible in object class
// this class also allows us with lazy initialization and double locking system
open class SingletonManager<out T: Any, in A>(creator: (A) -> T) {

    private var creator : ((A) -> T)? = creator
    @Volatile private  var instance : T? = null

    fun getInstance(arg : A) : T{
        val checkInstance = instance
        if(checkInstance != null){
            return checkInstance
        }

        return synchronized(this){
            val checkInstanceAgain = instance
            if(checkInstanceAgain != null){
                checkInstanceAgain
            }else{
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}


