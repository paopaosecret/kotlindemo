package com.example.kotlindemo.b类和对象

/**
 * 2.11、对象表达式、对象声明、伴生对象
 *
 * 对象表达式是在使用他们的地方立即执行（及初始化）的；
 * 对象声明是在第一次被访问到时延迟初始化的；          相当于Java 静态内部类的初始化时机
 * 伴生对象的初始化是在相应的类被加载（解析）时，与 Java 静态初始化器的语义相匹配。   相当于Java 静态代码块的处理时机
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 14:22
 */

fun main(){

    //TODO 1、如果只是需要一个对象，并不需要知道对象的类型可以使用对象表达式返回一个对象
    val obj1 = object {
        var x: Int = 0
        var y: Int = 0
    }
    println("${obj1.x} + ${obj1.y}")

    val clas211 = Class211()
    clas211.test1()
    clas211.test2()
    clas211.test3()
}

//说明辅助类
open class DataA{
    val a = 0;
}

class Class211{

    val a = 10

    //TODO 2、匿名对象可以用作只在本地和私有作用域中声明的类型
    // 如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，
    // 如果你没有声明任何超类型，就会是 Any

    // TODO 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
        fun v(){
            //TODO 对象表达式中的代码可以访问来自包含它的作用域的变量
            println("$a")
        }
    }

    // TODO 公有函数，没有指定类型，所以其返回类型是 Any
    public fun publicFoo1() = object {
        val x: String = "Y${a}"
    }

    //TODO 公有函数，指定返回类型，所以其返回类型是指定类型
    fun publicFoo2(): DataA = object: DataA(){
        val y: String = "y"
    }


    fun test1(){
        println(foo().x)
//      println(publicFoo1().x)  // publicFoo1返回的Any 没有x属性
        println(publicFoo2().a)  //publicFoo2返回的DataA 有属性a,没有y属性
    }


    //TODO 3、对象声明, 当在第一次访问到时延迟初始化，类.调用,可用作单例，
    object Data{
        fun getData(): String{
            return "data data data"
        }
    }

    object Data2{
        fun getData(): String{
            return "Class25()"
        }
    }

    fun test2(){
        println(Class211.Data.getData())
    }

    //TODO 4、定义伴生对象,伴生对象的Factory名称可省略
    // 伴生对象的成员直接使用类.调用
    // 伴生对象时唯一的，对象声明可定义多个
    companion object Factory{
        fun create(): Class211 = Class211()
    }

    fun test3(){
        Class211.create()   //TODO 4、伴生对象的成员可以使用类名作为限定符来调用
    }

}

