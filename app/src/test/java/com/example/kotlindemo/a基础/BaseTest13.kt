package com.example.kotlindemo.a基础

/**
 *
 * 控制流： if 、when 、for 、 while
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/21 15:34
 */

fun main(){
//    testIf()
//    testWhen()
    testFor()
//    testWhile()
}

fun testIf(){
    var a = 10
    var b = 20;
    var max = 0;

    //TODO 传统用法
    if(a > b){
        max = a
    }else{
        max = b
    }

    //TODO 注意if 作为表达式，会返回一个值
    val max1 = if(a > b){
        a
    } else {    //TODO if作为表达式返回一个值  必须有else分支
        b
//        max = 10
    }

    println(max)
    println(max1)
}

fun testWhen(){
    val i = 2
    when(i){     //TODO 1.流程控制
        is Int  -> {
            println("is Int")
            "is Int"
        }

        2 -> {
            println(" == 2")
            "is 2"
        }
    }
    println(describe(1))        //TODO 2.当做一个表达式有返回值,  和 if表达式一样  当作为表达式赋值时，需要else
    println(describe("hello"))
    println(describe(1L))
    println(describe(2))
    println(describe("other"))
}

fun describe(obj: Any): String =
    when (obj) {
    1          -> {
        println("one")
        "one"
    }
    "Hello"    -> "Greeting"
    is Long    -> "Long"
    !is String -> "Not a string"
    else       -> "Unknown"
}

fun testFor(){
    val array = IntArray(5){it}  //[0,1,2,3,4]
    for (item in array){      //TODO 遍历数组方式一, 迭代遍历
        print("$item ")
    }
    println("")
    //下标遍历
    for(index in array.indices){  //TODO 遍历数组方式二, 下标遍历
        print("${array[index]} ")
    }


    println("")
    for(i in 1..5 step 2) {        //TODO 三、使用区间表达式遍历 [1,5]闭空间上 每次加2
        print("$i ")
    }
    println("")

    for(i in 6 downTo 0 step 2){   //TODO 四、区间上降序遍历，[0, 6]闭空间上 降序，每次减2遍历
        print("$i ")
    }
}
public var c = 1
fun testWhile(){
    var i = 3
    while(i> 0){              //TODO  while循环
        print("$i ")
        i--
    }
    println("")

    do{                      //TODO   do while循环
        print("$i ")
        i++
    }while (i < 3)
}