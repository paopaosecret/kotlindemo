package com.example.mylibrary.net.request

import com.example.kotlindemo.e反射.HttpUtils
import com.example.kotlindemo.e反射.callback.AbstractCallBack
import com.example.mylibrary.net.bean.CacheMode
import com.example.mylibrary.net.cache.CacheManager
import com.example.mylibrary.net.cookie.Cookie
import com.example.mylibrary.net.bean.HttpHeaders
import com.example.mylibrary.net.bean.HttpParams
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor

/**
 *
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 14:46
 */
abstract class BaseRequest<out T>{
    var url: String? = null
    lateinit var tag: Any
    var readTimeOut: Long = 60
    var writeTimeOut: Long = 60
    var connectTimeOut: Long  = 60
    var cacheMode: CacheMode = CacheMode.DEFAULT;
    var cacheKey: String? = null
    var cacheTime: Long = -1
    var httpPamas: HttpParams = HttpParams()
    var httpHeaders: HttpHeaders = HttpHeaders()
    var interceptors: List<Interceptor>? = null
    var cookies: List<Cookie>? = null
    var cacheManager: CacheManager? = null
    var callBack: AbstractCallBack<Any>? = null

    var httpUrl: HttpUrl? = null;

    constructor(url: String){
        this.url = url
        httpUrl = url.toHttpUrlOrNull()

        var httpUtils: HttpUtils = HttpUtils.mInstance
        cacheManager = CacheManager.mInstance
        if(httpUtils.getCommonHttpPamas() != null){
            httpPamas.put(httpUtils.getCommonHttpPamas())
        }
        if(httpUtils.getCommonHttpHeaders() != null){
            httpHeaders.put(httpUtils.getCommonHttpHeaders())
        }
    }

}