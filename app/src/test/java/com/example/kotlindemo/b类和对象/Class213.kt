package com.example.kotlindemo.b类和对象

/**
 * 2.13、内联类
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 15:26
 */
//TODO 定义个内联类
//TODO 内联类必须含有唯一的一个属性在主构造器中初始化
inline class Password(val value: String)
inline class Version(val value: Int){
    //TODO 内联类定义成员, 内联类不能含有幕后字段
    val minVersion: Int
        get() = 1

    //TODO 内联类定义函数
    fun getVersion() : Int{
        return value
    }

//    init { }  //TODO 内联类不能含有 init 代码块
}

class Class213{
    //TODO 运行时，securePassword只包含String，不存在Password的实例
    val securePassword = Password("123456")
    val version = Version(5)
    fun test(){
        println(securePassword.value)
        println("version = ${version.getVersion()} + minVersion = ${version.minVersion}")
    }
}



fun main(){
    val class213 = Class213();
    class213.test()
}
