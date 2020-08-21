package com.example.kotlindemo.b类和对象

/**
 * 2.5、扩展
 * Kotlin能够扩展一个类的新功能而无需继承该类或者使用像装饰者这样的设计模式，这通过佳作扩展的特殊声明完成
 *
 * 应用：对于第三方库中的某个类的某个函数，我们可以通过扩展编写一个新的函数，这个函数像普通方法一样调用。 这种机制成为扩展函数
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 14:53
 */
class Class25(){
    val num = 10
}

//TODO 给MutableList<Int> 添加一个 swap函数
fun MutableList<Int>.swap(index1: Int, index2: Int){
    val temp = this[index1];
    this[index1] = this[index2]
    this[index2] = temp
}
fun Class25.sayHello(age: Int){
    this.num
    println("Hello I'm Class25, ${age}岁了")
}

//TODO 给MutableList<Int> 添加一个 max属性
val MutableList<Int>.maxVlaue: Int
    get() = 65536
//var ageValue: Int = 0;

var Class25.age: Int
    get() = 20
    set(value) {
//        field = value           扩展属性没有幕后字段来存储属性值
    }

fun main(){
    //TODO 注意扩展是静态解析的，并不会给MutableList<Int>类中添加新的函数，只是在调用的时候，使用.表达式调用一个新函数
    val list = mutableListOf(0,1,2)
    list.swap(1,2)
    list.iterator().forEach { print("$it ") }
    println("\n${list.maxVlaue}")

    val class25 = Class25()
    class25.sayHello(class25.age)
    class25.age = 100;
    class25.sayHello(class25.age)
}