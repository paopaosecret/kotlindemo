package com.example.kotlindemo.b类和对象

/**
 * 2.1、类与继承
 *
 * 类可以包含：
 * 构造函数 和 初始化块
 * 函数
 * 属性
 * 嵌套类 内部类
 * 对象
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/21 16:11
 */

//TODO 类定义语法：class 类名 constructor(f:Type) : supercClass {}
// class 类名 如果主构造器没有任何参数和可见修饰符  “constructor”可省略
class Class21 constructor(className: String){
    var className = ""
    var version = 0
    var type = "class"

    init {
        this.className = className   //TODO 使用主构造器是初始化成员变量
        println("Class21类开始初始化对象 $className， version = $version, type = $type")  //TODO 主构造器的参数可以在初始化代码块中使用
    }

    init {
        println("Class21类开始初始化对象 ${className.length}")
    }

    //TODO 定义次构造函数  主构造函数只能定义一个  次构造函数可以定义多个
    //TODO 如果定义了主构造函数  定义次构造函数需要委托给主构造函数
    constructor(n: String, v: Int) : this(n) {
        version = v
        println("构造方法调用className =  $className， version = $version, type = $type")
    }

    constructor(n: String, t:String, v: Int) : this(n,v) {   //TODO 次构造函数通过次构造函数委托主构造函数
        type = t;
        println("构造方法调用className =   $className， version = $version, type = $type")
    }
}

fun main(){
    val obj1 = Class21("第一个对象")          //使用第一个构造函数
    val obj2 = Class21("第二个对象", 1)            //使用第二个构造函数
    val obj3 = Class21("第三个对象","Test", 1)   //使用第三个构造函数
}

//TODO 继承
class ClassA{}   //TODO 隐式继承自Any
open class ClassB{}  //TODO 默认情况下是final的，不能被继承

class ClassC constructor(s:String) : ClassB() { //TODO 基类必须用派生类初始化主构造器， 因为ClassB的主构造器为默认的， 抽象类和接口没有主构造器
}
class ClassD : ClassB{
    constructor(s: String) : super()
}//TODO 同ClassC一样， 简写