package com.example.mylibrary.net.parser

import okhttp3.Response

/**
 * 网络请求统一接口
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 9:38
 */
interface IParser {

    /**
     * 判断是否需要走解析流程
     */
    fun isNeedParser(response: Response): Boolean

    /**
     * 将网络返回的数据解析为我们需要的bean对象
     */
    fun parse(response: Response): Any?

    fun unParse(response: Response): Any?
}