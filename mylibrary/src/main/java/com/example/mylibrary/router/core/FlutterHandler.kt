package com.example.mylibrary.router.core

import android.content.Context
import android.util.Log
import com.example.mylibrary.router.action.bus.RxBus
import com.example.mylibrary.router.action.event.FlutterPageEvent
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.entity.TransferEntity
import com.example.mylibrary.router.protocel.HyRouterConstant

/**
 * 处理目标页为Flutter的请求
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 9:16
 */
class FlutterHandler : IHandler {
    override fun hanlde(context: Context?, entity: TransferEntity, callBack: CallBack): Boolean {
        Log.d("HyRouter", "FlutterHandler hanlde")
        val key = entity.key;
        if(key != null && com.example.hyrouter.mapping.HyRouterManager.INSTANCE.allMapping?.containsKey(key)){
            when (entity.type) {
                HyRouterConstant.HYROUTER_NATIVE_TYPE_PAGE -> {
                    RxBus.post(FlutterPageEvent(context, entity, callBack))
                    return true
                }
            }
        }else{
            Log.d("HyRouter", "FlutterHandler hanlde 没有key")
            return false
        }
        return false
    }

}