package com.example.kotlindemo.c函数

/**
 * 3.2 高阶函数 和 Lambda表达式
 *
 * 高阶函数: 将函数作为参数 或者返回值的函数
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 16:08
 */
class Function32{}

fun f1(): String{
    return "abc"
}

val onClick: () -> String = { f1() }


//TODO 1、定义一个高阶函数
// 参数f 为一个函数， 返回值为一个函数
fun f2(a: Int, f:()-> String): ()-> String{
    return onClick
}

fun main(){
    println(onClick())
    val f3 = f2(1, onClick)

    println(f3)
    println(f3.invoke())  //函数执行的结果
    testSum()

    println(testReturn(1,2))
}

//TODO Lambda表达式:表示函数的特定的语言结构
//TODO 2、Lambda表达式 完整语法：val sum:(Int,Int)->Int = { x:Int, y:Int -> x + y }
val sum = { x:Int, y:Int -> x + y }
fun testSum(){
    println(sum.invoke(1,1))
}

//TODO 如果Lambda表达式最后一个参数是函数，那么相应的参数传入的lambda可以放在圆括号之外
fun fold(a:Int, f:(s1: String, s2: String) -> Int?){}

fun testFold(){
    //TODO 传入Lambda表达式为函数的最后一个参数时，可放在圆括号外面
    fold(1) { s1, s2 -> s1.length + s2.length}
}


fun only(f:(s1: String, s2: String) -> Int) : Int{
    var a = "aaa"
    var b = "bbb"
    var c = f.invoke( a,b)               //TODO 理解，传递函数可以 在调用方法中 执行被调用函数， 被调用函数的参数依赖于调用函数的执行中间变量

    return c
}

fun testOnly() {
    //TODO 如果只有一个函数参数的函数，lambda 作为参数时，可省略圆括号
    only { s1, s2 -> s1.length + s2.length }
}

//TODO it:单个参数的隐式名称:单个参数的函数，该参数可以隐式声明为 it
fun testIt(){
    val list = listOf(1,1,1).filter { it > 0 }
}

//TODO lambda返回值 默认最后一个表达式为返回
val testReturn: (x: Int, y: Int) -> Int = a@{ x: Int, y: Int ->
   val z = x + y
   z
}







