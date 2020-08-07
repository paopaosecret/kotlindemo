package com.example.kotlindemo.b类和对象

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


/**
 * 2.15、委托、代理属性
 * 作用：
 * 1、延迟初始化
 * 2、属性变化可感知
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 16:07
 */
class Class215{
    val a: Int = 10

    //TODO 1、Kotlin提供的标准委托
    val lazyValue: String by lazy{
        "lazyValue"
    }

    //TODO 2、自定义委托、代理
    var p: String by Delegate()

    //TODO 3、可观察属性
    var name: String by Delegates.observable("zhangsan"){
        property, oldValue, newValue ->
        println("${oldValue}---${newValue}")
    }
}

class Delegate{
    var str: String? = ""
    operator fun getValue(class215: Class215, property: KProperty<*>): String {
        return "${property.name} getValue: $str + ，class215.a=${class215.a}"
    }

    operator fun setValue(class215: Class215, property: KProperty<*>, value: String) {
        str = value
        println("${property.name} setValue $str")
    }
}

//TODO 4、把属性映射到map中,  应用在JSON 解析
class PMap(val map: Map<String, Any?>){
    val name: String by map
    val age: Int by map
}

fun main(){
    val class215 = Class215()
    println(class215.lazyValue)
    println(class215.p)

    class215.p = "12"
    println(class215.p)

    println(class215.name)
    class215.name = "lisi"

    val user = PMap(mapOf(
        "name" to "wangwu",
        "age" to 20
    ))
    println("user.name = ${user.name}, user.age = ${user.age}")

}
