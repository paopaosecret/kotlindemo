package com.example.mylibrary.router

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.mylibrary.router.action.FlutterPageAction
import com.example.mylibrary.router.action.NativeFunctionAction
import com.example.mylibrary.router.action.NativePageAction
import com.example.mylibrary.router.action.WebPageAction
import com.example.mylibrary.router.callback.CallBack
import com.example.mylibrary.router.core.DispatchCenter
import com.example.hyrouter.mapping.HyRouterManager
import com.example.mylibrary.router.entity.TransferEntity
import com.example.mylibrary.router.parser.TransferEntityParse

/**
 * 路由跳转工具类
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/29 11:27
 */
class HyRouter {
    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            HyRouter()
        }
    }

    fun transfer(context: Context?, uri: Uri?, callBack: CallBack): Boolean {
        Log.d("HyRouter", "transfer")
        val entity: TransferEntity = TransferEntityParse.parseEntity(uri) ?: return false
        return DispatchCenter.dispatch(context, entity, callBack)
    }

    fun init() {
        HyRouterManager.INSTANCE.initMapping()

        FlutterPageAction.INSTANCE.init()
        NativePageAction.INSTANCE.init()
        WebPageAction.INSTANCE.init()
        NativeFunctionAction.INSTANCE.init()
    }

    fun unInit() {
        FlutterPageAction.INSTANCE.unInit()
        NativePageAction.INSTANCE.unInit()
        WebPageAction.INSTANCE.unInit()
        NativeFunctionAction.INSTANCE.unInit()
    }
}