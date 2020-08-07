package com.example.kotlindemo.c函数

import com.example.kotlindemo.a基础.c

/**
 * 3.1、函数
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 16:08
 */
class Function31{
}

//TODO 1、函数声明
fun f1(x:Int, y:Int): Int{
    return x+y
}

//TODO 2、函数使用有默认值的参数
fun f2(a: Int, b: Int = 10): Int{
    return a + b
}

//TODO 3、函数使用有默认值的参数，并且之后的某些参数没有默认值
fun f3(a: Int = 10, b:Int): Int{
    return a + b
}

//TODO 4、函数使用
fun main(){
    val sum1 = f1(1,1)
    println(sum1)

    val sum21 = f2(1,1)
    val sum22 = f2(1)  //TODO 函数有默认值的参数可以省略
    println("$sum21 $sum22")

    val sum31 = f3(1,1)
    val sum32 = f3(b = 1)  //TODO 省略默认值，默认值参数之后的参数需要指定名称
    println("$sum31 $sum32")
    println(c)

    testF9()
}

//TODO 5、返回Unit的函数， 返回值类型可以省略
fun f5(){}   //TODO 相当于 fun f5(): Unit{}

//TODO 6、单表达式函数,可以省略花括号
fun f6(i: Int): Int = i*2

//TODO 7、单表达式函数,返回值类型可推断时，也可以省略
fun f7(i: Int)= i*2

//TODO 8、可变数量的参数（vararg）
fun f8(a:String,  vararg t: Int){

}
fun testF8(){
    f8("a",1,2,3,4)
}

//TODO 9、中轴表示法,针对扩展函数
infix fun Int.f9(x: Int): Int = x

fun testF9(){
    println(1.f9(2))

    // 用中缀表示法调用该函数: 可以省略. 和 括号
    println(1 f9 2)
}

//TODO 10、泛型函数
fun <T> f10(vararg t: T){}