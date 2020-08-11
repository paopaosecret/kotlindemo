package com.example.hyrouter.mapping

class HyRouterManager private constructor(){
    private val nativePageMapping: MutableMap<String, String> = mutableMapOf()

    private val flutterPagerMapping: MutableMap<String, String> = mutableMapOf()

    private val webPageMapping: MutableMap<String, String> = mutableMapOf()

    private val nativeFunctionMapping: MutableMap<String, String> = mutableMapOf()

    val allMapping: MutableMap<String, String> = mutableMapOf()

    fun initMapping() {
        //TODO 添加Native页面初始化 start
        nativePageMapping["jetpack"] to "com.example.kotlindemo.jetpack.JetpackActivity"
        //TODO 添加Native页面初始化 end

        //TODO 添加Native方法初始化 start
        nativeFunctionMapping["print"] to "com.example.kotlindemo.utils.ToastUtils#printf"
        nativeFunctionMapping["test"] to "com.example.kotlindemo.utils.ToastUtils#test"
        //TODO 添加Native方法初始化 end

        allMapping.clear()
        allMapping.putAll(flutterPagerMapping)
        allMapping.putAll(webPageMapping)
        allMapping.putAll(nativePageMapping)
        allMapping.putAll(nativeFunctionMapping)
    }

    companion object {
        val INSTANCE: HyRouterManager = HyRouterManager()
    }

}