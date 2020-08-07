package com.example.kotlindemo.b类和对象

/**
 * 2.9、嵌套类和内部类
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/23 9:51
 */
class Class29 {}

//TODO 嵌套类
class Outer1{
    private val bar: Int = 1
    class Nested{
        fun foo(): Int{
            return 1
        }
    }
}

//TODO 内部类
// 标记为 inner 的嵌套类能够访问其外部类的成员。内部类会带有一个对外部类的对象的引用：
class Outer2{
    private val bar: Int = 1
    inner class Inner{
        fun foo() = bar
    }
}

//TODO 匿名内部类
fun main(){
    //TODO 测试嵌套类
    val nested = Outer1.Nested().foo();
    println(nested)

    //TODO 内部类会带有一个对外部类的对象的引用
    val inner = Outer2().Inner().foo()
    println(inner)

    //TODO 测试匿名内部类
    val thread = Thread({
        for(i in 1..10){
            println(i)
            Thread.sleep(1000)
        }
    }, "Thread-1").start()
}
