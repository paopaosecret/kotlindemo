package com.example.kotlindemo.b类和对象

/**
 * 2.14、委托、代理类
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 15:46
 */
class Class214

//TODO 委托或者代理模式
interface Base{
    fun print()
    fun printTestOverride()
}

class BaseImpl(val x: Int): Base{
    override fun print() {
        println(x)
    }

    override fun printTestOverride() {
        println(x)
    }
}

//TODO by b：表示b将会在BaseProxy中内部存储
class BaseProxy(b: Base): Base by b
{
    override fun printTestOverride() {
        println("测试覆盖")
    }
}

fun main(){
    val b = BaseImpl(10)
    BaseProxy(b).print()
    BaseProxy(b).printTestOverride()
}
