package com.example.mylibrary.router.core

class HyRouterManager private constructor() {
    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            HyRouterManager()
        }
    }

    private val flutterPagerMapping: MutableMap<String, String> = mutableMapOf()

    private val nativePageMapping =  mutableMapOf(
        "jetpack" to "com.example.kotlindemo.jetpack.JetpackActivity"
    )

    private val webPageMapping: MutableMap<String, String> = mutableMapOf()

    private val nativeFunctionMapping: MutableMap<String, String> = mutableMapOf(
        "print" to "com.example.kotlindemo.utils.ToastUtils#printf"
    )

    val allMapping: MutableMap<String, String> = mutableMapOf()

    fun registerFlutterPagerMapping(map: Map<out String, String>) {
        flutterPagerMapping.putAll(map)
        allMapping.putAll(map)
    }
    fun registerNativePageMapping(map: Map<out String, String>) {
        nativePageMapping.putAll(map)
        allMapping.putAll(map)
    }
    fun registerWebPageMapping(map: Map<out String, String>) {
        webPageMapping.putAll(map)
        allMapping.putAll(map)
    }


    fun registerNativeFunctionMapping(map: Map<out String, String>) {
        nativeFunctionMapping.putAll(map)
        allMapping.putAll(map)
    }

    fun initMapping(){
        allMapping.putAll(flutterPagerMapping)
        allMapping.putAll(webPageMapping)
        allMapping.putAll(nativePageMapping)
        allMapping.putAll(nativeFunctionMapping)
    }

}