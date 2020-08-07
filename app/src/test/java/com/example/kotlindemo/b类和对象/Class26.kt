package com.example.kotlindemo.b类和对象

/**
 * 2.6、数据类
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 15:34
 */
class Class26{}

data class User(
    val name: String,
    val age: Int
){ //TODO 主构造器中的属性  可以在默认生成的方法中生成例如：hashCode equals toString
    var phone: String =""
}


fun main(){
    val obj: User = User("张三",18)
    println("name = ${obj.name} ，phone = ${obj.phone}")
    println("toString():${obj.toString()}")
    println("hashCode():${obj.hashCode()}")

    val obj1: User = User("张三",18)
    obj1.phone = "15425677896"
    println("toString():${obj1.toString()}")
    println("hashCode():${obj1.hashCode()}")
    println("name = ${obj1.name} ，phone = ${obj1.phone}")
    println(obj.equals(obj1))
}