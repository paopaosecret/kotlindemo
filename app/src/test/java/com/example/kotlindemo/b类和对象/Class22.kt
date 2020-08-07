package com.example.kotlindemo.b类和对象

/**
 *
 * 2.2、属性 和 字段
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/21 19:01
 */

class Class22{
    //TODO 定义属性
    //var <propertyName>[: <PropertyType>] [= <property_initializer>]
    //[<getter>]
    //[<setter>]
    var name: String = "Class22"   //TODO 如果需要使用幕后字段属性必须由初始值
        get() = "${field}abc"          //TODO getter setter中使用field幕后字段代替该变量
        set(value){
            field = "${value}abc"
        }

    var version: String = "1.0"  //TODO 同上面的getter setter
}

fun main(){
    var class22 = Class22()             //TODO Kotlin 没有new关键字
    println(class22.name)
    class22.name = "Object22"          //TODO 属性设值
    println(class22.name)
    println(d)                         //TODO Class24中顶层属性d
}