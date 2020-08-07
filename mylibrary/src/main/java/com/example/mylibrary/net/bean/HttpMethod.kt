package com.example.mylibrary.net.bean

/**
 * http请求方式的封装
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 11:29
 */
enum class HttpMethod(val descriptor: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    DOWNLOAD("DOWNLOAD")
}