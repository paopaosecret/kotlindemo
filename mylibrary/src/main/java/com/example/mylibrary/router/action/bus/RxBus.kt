package com.example.mylibrary.router.action.bus

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors


/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/8/4 11:13
 */
object RxBus {
    //订阅者和事件缓存map
    private val map: MutableMap<Any, List<SubscribeMethod>> = mutableMapOf()
    private val handler = Handler(Looper.getMainLooper())
    private val executors = Executors.newCachedThreadPool()

    /**
     * 注册
     * @param subscribe
     */
    fun register(subscribe: Any) {
        var list = map[subscribe]
        if (list == null) {
            list =
                getSubscribeMethods(
                    subscribe
                )
            map[subscribe] = list
        }
    }

    /**
     * 取消注册
     * @param subscribe
     */
    fun unRegister(subscribe: Any) {
        val list = map[subscribe]
        if (list != null) {
            map.remove(subscribe)
        }
    }

    /**
     * 获取订阅方法
     * @param subscribe
     */
    private fun getSubscribeMethods(subscribe: Any): List<SubscribeMethod> {
        val list: MutableList<SubscribeMethod> = mutableListOf()
        val clazz = subscribe.javaClass
        val name = clazz.name
        if (name.startsWith("java.") ||
            name.startsWith("javax.") ||
            name.startsWith("android.") ||
            name.startsWith("androidx.")
        ) {
            return emptyList()
        }
        val declaredMethods = clazz.declaredMethods
        declaredMethods.forEach {
            val annotation = it.getAnnotation(Subscribe::class.java) ?: return@forEach
            val parameterTypes = it.parameterTypes
            if (parameterTypes.size != 1) {
                throw RuntimeException("AndroidBus只能接收一个参数")
            }
            val threadModel = annotation.threadModel
            val subscribeMethod = SubscribeMethod(
                it,
                threadModel,
                parameterTypes[0]
            )
            list.add(subscribeMethod)
        }
        return list
    }

    /**
     * 发送事件
     * @param bean
     */
    fun post(bean: Any) {
        map.forEach {
            val list = it.value
            list.forEach { subscribeMethod ->
                if (subscribeMethod.eventType?.isAssignableFrom(bean::class.java)!!) {
                    when (subscribeMethod.threadModel) {
                        ThreadModel.POSTING -> {
                            callback(
                                subscribeMethod,
                                it.key,
                                bean
                            )
                        }
                        ThreadModel.MAIN -> {
                            if (Looper.myLooper() == Looper.getMainLooper()) {
                                callback(
                                    subscribeMethod,
                                    it.key,
                                    bean
                                )
                            } else {
                                handler.post {
                                    callback(
                                        subscribeMethod,
                                        it.key,
                                        bean
                                    )
                                }
                            }
                        }
                        ThreadModel.ASYNC -> {
                            if (Looper.myLooper() != Looper.getMainLooper()) {
                                callback(
                                    subscribeMethod,
                                    it.key,
                                    bean
                                )
                            } else {
                                executors.execute {
                                    callback(
                                        subscribeMethod,
                                        it.key,
                                        bean
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 回调
     * @param subscribeMethod 注册方法
     * @param subscribe 订阅类
     * @param bean 事件
     */
    private fun callback(subscribeMethod: SubscribeMethod, subscribe: Any, bean: Any) {
        val method = subscribeMethod.method
        method?.invoke(subscribe, bean)
    }
}