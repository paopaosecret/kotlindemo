package com.example.kotlindemo.b类和对象

/**
 * 2.10、枚举类
 *
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 10:54
 */
class Class210

//TODO 基本实现  作用：实现类型的安全
enum class Season{
    SPRING, SUMMER, AUTUMN, WINTER
}

//TODO 带参数的枚举类实现
enum class Color(val rgb: Int){
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//TODO 使用匿名类：枚举常量还可以声明其带有相应方法
enum class State{
    WAITING{
        override fun signal() = TALKING
    },
    TALKING{
        override fun signal() = WAITING
    };
    abstract fun signal(): State
}

fun main(){
    //TODO 枚举遍历
    enumValues<Season>().iterator().forEach {
        println(it)
    }

    println("")

    State.values().iterator().forEach {
        println("${it} + ${it.signal()}")
    }
}