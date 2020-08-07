package com.example.mylibrary.router.action

import android.util.Log
import com.example.mylibrary.router.action.base.IAction
import com.example.mylibrary.router.action.bus.RxBus
import com.example.mylibrary.router.action.bus.Subscribe
import com.example.mylibrary.router.action.event.FlutterPageEvent

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:52
 */
class FlutterPageAction : IAction {
    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            FlutterPageAction()
        }
    }

    override fun init() {
        RxBus.register(this)
    }

    @Subscribe
    fun call(event: FlutterPageEvent){
        Log.d("HyRouter","FlutterPageAction:aaaaaaaaaaaaaaaaaaaaa")
        event.callBack?.onResult("this is result")
    }

    override fun unInit() {
        RxBus.unRegister(this)
    }
}