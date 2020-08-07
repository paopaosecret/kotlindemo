package com.example.kotlindemo.反射.callback

import okhttp3.Call


/**
 * 网络请求结果回调封装
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/14 20:07
 */
abstract class AbstractCallBack<T> {

    /**
     * 网络请求成功之后回调
     */
    abstract fun onSuccess(call: Call, response: T)

    /**
     * 网络请求失败之后回调
     */
    abstract fun onFail(call: Call, e: Exception)
}