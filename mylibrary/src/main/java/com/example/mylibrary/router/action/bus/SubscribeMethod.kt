package com.example.mylibrary.router.action.bus

import java.lang.reflect.Method

class SubscribeMethod(
    //订阅方法
    var method: Method,

    //线程模式
    var threadModel: ThreadModel,

    //参数类型
    var eventType: Class<*>)
