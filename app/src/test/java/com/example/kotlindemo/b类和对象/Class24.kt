package com.example.kotlindemo.b类和对象

/**
 * 2.4、可见性修饰符：
 *
 * 类、对象、接口、构造函数、方法、属性和它们的 setter 都可以有 可见性修饰符。
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 11:45
 */
//TODO 第一：修饰顶层属性、函数、类、接口、对象如果不指定可见性修饰符，则默认是public
var i = 10
fun test(){}
class A{}
interface B{}
var a: A = A()

//protected  var proVar = 1;       //TODO 不能使用在顶层模块中
private var c = 1   //TODO 在本文件中可见
internal var d = 3  //TODO 相同模块内可见（Class22 #main 方法中可测试）

//TODO 第二：修饰类内部声明的成员
// private--   意味着只在这个类内部（包含其所有成员）可见；
// protected—— 和 private一样 + 在子类中可见。
// internal —— 能见到类声明的 本模块内 的任何客户端都可见其 internal 成员； （TUICallKit的 TUICallState 可以优化为这个修饰符，防止外部module修改）
// public ——   能见到类声明的任何客户端都可见其 public 成员。
class Class24 : E(){
    fun testField(){
//        this.a = 6           //私有属性，子类不可见
        this.b = 4
        this.c = 5
    }
}

open class E{
    private var a = 1            //本类中可见
    protected var b = 2          //本类 + 子类 可见
    internal var c = 3           //有类声明的 模块内可见
}


