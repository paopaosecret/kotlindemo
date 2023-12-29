package com.example.kotlindemo

import android.app.Application
import com.example.mylibrary.router.HyRouter

class KotlinApplication : Application(){

    companion object {
        var id: Long  = 1
        var lightTheme: Boolean = true

        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            KotlinApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
        HyRouter.INSTANCE.init()
    }

}