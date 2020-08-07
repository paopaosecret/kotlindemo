package com.example.kotlindemo.b类和对象

/**
 *  2.8、泛型
 *      协变 逆变：声明处型变：类C 在 参数T 上是协变，或者逆变   out:协变 生产者  in:逆变 消费者
 *      类型投影：使用处型变
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 16:02
 */

//TODO 定义泛型类
class Class28<T>(t: T){
    val value = t
}

fun main(){
    val obj1: Class28<String> = Class28<String>("hello")
    val obj2 = Class28<Int>(10)
    val obj3 = Class28('c')                  //TODO 通过传参可以确定类型参数，所以允许省略类型参数

    val intArray: Array<Int> = Array(10)
    val anyArray: Array<Any> = Array(10)
    copy(intArray, anyArray)  //使用时协变
}

//TODO 声明处型变
//TODO 参考Test列中的demo方法
interface Source<out T>{      //TODO 声明处向编译器解释Java中遇到的问题， 这称为声明处型变
    fun nextT(): T            //TODO 只会生产对象T，不使用T

}

fun testOut(strs: Source<String>){
    val objects: Source<Any> = strs  //TODO 这个没问题，因为T是一个out-参数，只生产，不消费  //out称为型变注解
}

interface Comparable<in T>{                //TODO T类型作为参数的时候可以用子类
    operator fun compareTo(other: T): Int  //TODO 会接收传入的参数other, 该参数只使用，不返回（不消费） //in称为 逆变注解
}

fun testIn(x: Comparable<Number>){
    //TODO 注意x的类型为Comparable<Double>
    x.compareTo(1.0)       //TODO 1.0 拥有类型Double, 它是Double,是Number的子类型，因此可以将x赋给类型为Comparable<Double>的变量
    val y: Comparable<Double> = x
}

//TODO 类型投影
//TODO 有些时候定义的时候不能只限制返回T 或者传入T,有可能会混合使用，所以在使用时需要限制，例如 如下
class Array<T>(val size: Int){
    fun get(index: Int): T?{ return null}      //既需要返回T
    fun set(index: Int, t: T){}                //又需要传入T
}

fun copy(from: Array<out Any>, to: Array<Any>){

}
