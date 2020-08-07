package com.example.mylibrary.router.action.bus

//事件订阅注解
@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class Subscribe(val threadModel: ThreadModel = ThreadModel.POSTING)