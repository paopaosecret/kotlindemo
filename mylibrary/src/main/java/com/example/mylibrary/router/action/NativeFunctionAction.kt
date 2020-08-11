package com.example.mylibrary.router.action

import android.util.Log
import com.example.mylibrary.router.action.base.IAction
import com.example.mylibrary.router.action.bus.RxBus
import com.example.mylibrary.router.action.bus.Subscribe
import com.example.mylibrary.router.action.event.NativeFunctionEvent
import com.example.hyrouter.mapping.HyRouterManager

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:41
 */
class NativeFunctionAction : IAction {
    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NativeFunctionAction()
        }
    }

    override fun init() {
        RxBus.register(this)
    }

    @Subscribe
    fun call(event: NativeFunctionEvent){
        Log.d("HyRouter","NativeFunctionEvent:aaaaaaaaaaaaaaaaaaaaa")

        val value = HyRouterManager.INSTANCE.allMapping.get(event.transferEntity.key)     //printf方法反射测试
        if(value != null){
            val array = value.split("#")
            if(array.size >= 2){
                val className = array[0]
                val functionName = array[1]
                val classImpl = Class.forName(className)
                if(classImpl.declaredMethods != null && classImpl.declaredMethods.size > 0){
                    for(method in classImpl.declaredMethods){
                        if(functionName.equals(method.name)){
                            val obj = classImpl.newInstance()
                            method.invoke(obj, event.context, event.callBack, event.transferEntity.params)
                        }
                    }
                }
            }
        }
        event.callBack?.onResult("this is result")
    }

    override fun unInit() {
        RxBus.unRegister(this)
    }
}