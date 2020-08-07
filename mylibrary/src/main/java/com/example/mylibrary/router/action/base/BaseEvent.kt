package com.example.mylibrary.router.action.base

import android.content.Context
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.entity.TransferEntity
import java.io.Serializable

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 11:25
 */
open class BaseEvent protected constructor(context: Context, transferEntity: TransferEntity, callBack: CallBack) : Serializable {
    val transferEntity: TransferEntity
    val context: Context
    val callBack: CallBack

    init {
        this.transferEntity = transferEntity
        this.context = context
        this.callBack = callBack
    }
}