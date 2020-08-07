package com.example.mylibrary.router.core

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:42
 */
object HandlerFactory {
    fun createFlutterHandler(): IHandler {
        return FlutterHandler()
    }

    fun createNativeHandler(): IHandler {
        return NativeHandler()
    }

    fun createWebHandler(): IHandler {
        return WebHandler()
    }
}