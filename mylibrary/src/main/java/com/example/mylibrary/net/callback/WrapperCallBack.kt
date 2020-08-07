package com.example.mylibrary.net.callback

import com.example.kotlindemo.反射.callback.AbstractCallBack
import com.example.mylibrary.net.parser.IParser
import okhttp3.Call
import okhttp3.Response

/**
 * 包装AbstractCallBack，对其功能进行增强
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 9:44
 */
class WrapperCallBack<T>(val callBack: AbstractCallBack<T>, val parser: IParser) :
        AbstractCallBack<Response>() {

    override fun onSuccess(call: Call, response: Response) {
        TODO("请求成功之后统一处理")
        try {
            if (parser?.isNeedParser(response)) {
                val data = parser.parse(response)
                callBack.onSuccess(call, data as T)
            } else {
                val data = parser.unParse(response)
                callBack.onSuccess(call, data as T)
            }
        } catch (e: java.lang.Exception) {
            callBack.onFail(call, e)
        }
    }

    override fun onFail(call: Call, e: Exception) {
        TODO("请求失败之后统一处理")
        callBack.onFail(call, e)
    }
}