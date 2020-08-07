package com.example.mylibrary.router.core

import android.content.Context
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.entity.TransferEntity

/**
 * 处理各个平台的请求接口
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 9:07
 */
interface IHandler {
    fun hanlde(
        context: Context?,
        entity: TransferEntity,
        callBack: CallBack
    ): Boolean
}