package com.example.kotlindemo.d集合

class MapTest {
    private val nativePageMapping: MutableMap<String, String> = mutableMapOf()

    private val flutterPagerMapping: MutableMap<String, String> = mutableMapOf()

    private val webPageMapping: MutableMap<String, String> = mutableMapOf()

    private val nativeFunctionMapping: MutableMap<String, String> = mutableMapOf()

    val allMapping: MutableMap<String, String> = mutableMapOf()

    fun initMapping() {
        //TODO 添加Native页面初始化 start
        nativePageMapping.put("jetpack","com.example.kotlindemo.jetpack.JetpackActivity")
        //TODO 添加Native页面初始化 end


        //TODO 添加Native方法初始化 start
        nativeFunctionMapping["print"] to "com.example.kotlindemo.utils.ToastUtils#printf"
        nativeFunctionMapping["test"] to "com.example.kotlindemo.utils.ToastUtils#test"
        //TODO 添加Native方法初始化 end
        println("nativePageMapping.size = ${nativePageMapping.size}")
        println("nativeFunctionMapping.size = ${nativeFunctionMapping.size}")
        allMapping.clear()
        allMapping.putAll(flutterPagerMapping)
        allMapping.putAll(webPageMapping)
        allMapping.putAll(nativePageMapping)
        allMapping.putAll(nativeFunctionMapping)
        println("allMapping.size = ${allMapping.size}")
    }

    companion object {
        val INSTANCE: MapTest = MapTest()
    }
}

fun main(){
    MapTest.INSTANCE.initMapping()

    if(MapTest.INSTANCE.allMapping.containsKey("test")){
        println("包含key")
    }else{
        println("不包含key")
    }
}