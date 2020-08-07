package com.example.mylibrary.router.core

import android.content.Context
import android.util.Log
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.entity.TransferEntity
import com.example.mylibrary.router.protocel.HyRouterConstant

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:43
 */
object DispatchCenter {
    fun dispatch(context: Context?, entity: TransferEntity, callBack: CallBack): Boolean {
        Log.d("HyRouter", "DispatchCenter dispatch")
        var handler: IHandler? = null
        handler = when (entity.platform) {
            HyRouterConstant.HYROUTER_HOST_NATIVE -> HandlerFactory.createNativeHandler()
            HyRouterConstant.HYROUTER_HOST_FLUTTER -> HandlerFactory.createFlutterHandler()
            HyRouterConstant.HYROUTER_HOST_WEB -> HandlerFactory.createWebHandler()
            else -> return false
        }
        return handler?.hanlde(context, entity, callBack) ?: false
    }
}