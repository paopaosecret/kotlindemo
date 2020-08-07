package com.example.mylibrary.net.parser

import com.google.gson.Gson
import okhttp3.Response
import java.lang.reflect.Type

/**
 * 提供统一的Json解析封装
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 9:58
 */
class JsonParser<T>(val type: Type, private val className: String) : IParser {
    private val CONTENT_TYPE = "application/json;charset=UTF-8"
    private val gson = Gson()

    override fun isNeedParser(response: Response): Boolean {
        TODO("判断是否需要进行JSON解析")
        if(CONTENT_TYPE != response.body()?.contentType().toString()){
            return false
        }

        if(FILTER_STR.filter { it  == className }.size > 0){
            return false
        }

        return true
    }

    override fun parse(response: Response): Any? {
        TODO("Not yet implemented")
    }

    override fun unParse(response: Response): Any? {
        TODO("Not yet implemented")
    }

    companion object {
        const val NEED_PARSE = "NEED_PARSE"
        const val STRING_NAME = "java.lang.String"
        const val RESPONSE_BODY_NAME = "okhttp3.ResponseBody"
        val FILTER_STR = arrayOf(STRING_NAME, RESPONSE_BODY_NAME)
    }
}