package com.example.kotlindemo.a基础


/**
 * Kotlin 基本数据类型:数字、字符、布尔值、数组、字符串
 *
 * 类型       大小（比特数）
 * Byte            8
 * Short          16
 * Int            32
 * Long           64
 * Float          32
 * Double         64
 *
 * Char           2                *字符类型不能当做数字使用
 * Boolean        true or false
 * String
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/21 14:55
 */
fun main(){
    test1()
}

/**
 * 测试基本数据类型
 */
fun test1(){
    //TODO 定义常量  val|var f_name: f_type = f_value

    val i1: Int = 10
    var i2: Int? = null  //TODO 定义可以为空的对象时，需要给类型后面加个“？”
    var i3 = 20          //TODO 编译器自动推断出i3为Int类型
    i3 = 30
//    i1 = 40            //TODO val定义的变量只能赋值一次(只读)， var可以多次赋值（可变）

    println(i1)
    println(i2)
    println(i3)

    val c: Char = 'c'
//    if(c == 1)             //TODO 字符不能当做数字使用
    println(c)

    val str: String = "abcsd${i1 + i3}"   //TODO Kotlin支持字符串模板“${表达式}”  或者 “$变量”
    println(str)

    //数组初始化
    val array1: IntArray = IntArray(5)               //[0,0,0,0,0]
    val array2 = IntArray(4){10}                     //[10,10,10,10]
    var array3 = IntArray(3){it * 1}                 //[0,1,2]
    array1.iterator().forEach { print("$it ") }
    array2.iterator().forEach { print("$it ") }
    array3.iterator().forEach { print("$it ") }
}
