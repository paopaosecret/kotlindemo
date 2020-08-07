package com.example.kotlindemo.反射

import com.example.mylibrary.net.bean.HttpHeaders
import com.example.mylibrary.net.bean.HttpParams

class HttpUtils {
    companion object {
        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            HttpUtils()
        }
    }


    fun getCommonHttpPamas(): HttpParams?{
        return null
    }

    fun getCommonHttpHeaders(): HttpHeaders?{
        return null
    }
}