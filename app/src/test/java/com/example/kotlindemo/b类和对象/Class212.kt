package com.example.kotlindemo.b类和对象

/**
 * 2.12、类型别名
 * 类型别名为现有的类型提供替代名称，
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 15:14
 */
class Class212 {
}
class Z{
    inner class Inner
}

//TODO 1、缩短泛型类
typealias InnerSet = Set<Z.Inner>

//TODO 2、为函数提供别名
typealias addHanler = (Int, Int) -> Int    //TODO 普通函数
typealias fxHandler<T> = (T) -> Boolean    //TODO 泛型函数

//TODO 3、为内部类提供别名
typealias ZInner = Z.Inner
fun main(){
    val a:InnerSet? = null
}