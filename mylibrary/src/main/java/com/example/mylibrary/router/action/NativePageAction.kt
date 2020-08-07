package com.example.mylibrary.router.action

import android.content.Intent
import android.util.Log
import com.example.mylibrary.router.action.base.IAction
import com.example.mylibrary.router.action.bus.RxBus
import com.example.mylibrary.router.action.bus.Subscribe
import com.example.mylibrary.router.action.event.NativePageEvent
import com.example.mylibrary.router.core.HyRouterManager

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:52
 */
class NativePageAction : IAction {
    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NativePageAction()
        }
    }

    override fun init() {
        RxBus.register(this)
    }

    @Subscribe
    fun call(event: NativePageEvent){
        Log.d("HyRouter","NativePageEvent:aaaaaaaaaaaaaaaaaaaaa")
        val intent = Intent()
        val className = HyRouterManager.INSTANCE.allMapping.get(event.transferEntity.key)
        if(className != null){
            intent.setClassName(event.context, className)
            event.context.startActivity(intent)
        }
        event.callBack.onResult("this is result")
    }

    override fun unInit() {
        RxBus.unRegister(this)
    }
}