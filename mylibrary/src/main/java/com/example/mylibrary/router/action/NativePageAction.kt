package com.example.mylibrary.router.action

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.hyrouter.mapping.HyRouterManager
import com.example.mylibrary.router.action.base.IAction
import com.example.mylibrary.router.action.bus.RxBus
import com.example.mylibrary.router.action.bus.Subscribe
import com.example.mylibrary.router.action.event.NativePageEvent

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
        val entity = event.transferEntity
        if(entity == null){
            Log.d("HyRouter","NativePageEvent:transferEntity is null")
            event.callBack.onResult("transferEntity is null")
            return
        }

        val intent = Intent()
        val className = HyRouterManager.INSTANCE.allMapping.get(entity.key)
        if(className != null){
            intent.setClassName(event.context, className)
            intent.putExtra("jump_is_finish", entity.isFinish)
            intent.putExtra("COMMON_PARAMS", entity.commonParams)
            startActivity(event.context, intent)
        }
        event.callBack.onResult("this is result")
    }

    fun startActivity(context: Context, intent: Intent?): Boolean {
        return if (intent == null) {
            false
        } else {
            val isFinish = intent.getBooleanExtra("jump_is_finish", false)
            context.startActivity(intent)
            if (isFinish && context is Activity) {
                context.finish()
            }
            true
        }
    }

    override fun unInit() {
        RxBus.unRegister(this)
    }
}