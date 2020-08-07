package com.example.mylibrary.router.action.event

import android.content.Context
import com.example.mylibrary.router.action.base.BaseEvent
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.entity.TransferEntity

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:52
 */
class WebPageEvent(
    context: Context?,
    transferEntity: TransferEntity?,
    callBack: CallBack
) : BaseEvent(context!!, transferEntity!!, callBack)