package com.example.mylibrary.router.core

import android.content.Context
import android.util.Log
import com.example.mylibrary.router.action.bus.RxBus
import com.example.mylibrary.router.action.event.WebPageEvent
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.entity.TransferEntity
import com.example.mylibrary.router.protocel.HyRouterConstant

/**
 * 处理目标页为Web的请求
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 9:16
 */
class WebHandler : IHandler {
    override fun hanlde(context: Context?, entity: TransferEntity, callBack: CallBack): Boolean {
        Log.d("HyRouter", "NativeHandler hanlde")
        val key = entity.key
        if(key != null && HyRouterManager.INSTANCE.allMapping?.containsKey(key)) {
            when (entity.type) {
                HyRouterConstant.HYROUTER_NATIVE_TYPE_PAGE -> {
                    RxBus.post(WebPageEvent(context, entity, callBack))
                    return true
                }
            }
        }else{
            Log.d("HyRouter", "WebHandler hanlde 没有key")
        }
        return false
    }
}