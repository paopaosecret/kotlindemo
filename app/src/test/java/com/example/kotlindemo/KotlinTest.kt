package com.example.kotlindemo

import androidx.constraintlayout.solver.widgets.Rectangle
import com.example.kotlindemo.b类和对象.Class25
import com.example.kotlindemo.b类和对象.sayHello
import org.junit.Test

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/21 14:17
 *
 * Kotlin 基础语法熟悉
 *
 * 1、基础
 *      1.1 基本类型
 *      1.2 包和导入
 *      1.3 控制流
 *      1.4 返回和跳转
 *
 * 2、类和对象
 *      2.1 类与继承
 *      2.2 属性与字段
 *      2.3 接口
 *      2.4 可见性修饰符
 *      2.5 扩展
 *      2.6 数据类
 *      2.7 密封类
 *      2.8 泛型
 *      2.9 嵌套类
 *      2.10 枚举类
 *      2.11 对象
 *      2.12 类型别名
 *      2.13 内联类
 *      2.14 委托
 *      2.15 委托属性
 *
 * 3. 函数与Lambda表达式
 *      3.1 函数
 *      3.2 Lambda表达式
 *      3.3 内联函数
 */
class KotlinTest {

    @Test
    fun testBianLiang(){
        //val 定义只读变量 只能赋值一次
        val a: Int = 1  //立即赋值
        val b = 2       //自动推断出Int类型
        val c: Int      //如果没有初始化值，类型不能省略
        c = 3           //明确赋值

        //var 定义可重新赋值的变量
        var x = 5;      //自动推断出Int类型
    }

    @Test
    fun testZiFuChuan(){
        val a = 1
        //字符串中引用变量
        val s1 = "a is $a"

        //字符串中引用表达式
        val s2 = "a + a is ${a + a}"
    }

    @Test
    fun testIfElse(){
        var a = 5
        if(a > 10){
            print("a 大于 10")
        }else{
            print("a 不大于 10")
        }
    }

    //测试方法不能有返回值，所以另起方法测试
    @Test
    fun testNull(){
        testAllNull()
    }

    //当某个变量的值可以为null时， 必须在声明处的类型后面添加“？”,返回值也一样
    fun testAllNull(): Int?{
        var str: String? = null
        if(str == null){
            print("str is null")
        }else{
            print("str is not null")
        }
        return null
    }


    @Test
    fun testHanShu() {
        println("Hello world!")
        sum(1,1)
        sum(1,1,1)
        printSum(1,2)
    }

    /**
     * 函数1
     * 传入参数 a, b类型为Int
     * 返回值Int
     */
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * 函数2
     * 将表达式作为函数体，返回值类型自动推断的函数
     */
    fun sum(a: Int, b: Int, c: Int) = a + b + c;

    /**
     * 函数3
     * 函数返回无意义的值
     * 注意：返回无意义的值时，"： Unit"可以省略
     */
    fun printSum(a: Int, b: Int): Unit{
        print("sum of $a + $b is ${a + b}");
    }

    @Test
    fun testLeiXing(){
        var obj = "Hello";
        //测试类型检测
        if(obj is String){
            println("$obj is String");
        }

        if(obj !is String){
            println("$obj is not String");
        }

        //测试类型转换,如果使用类型检测，检测分支中可以直接使用，无需再显式转换
        if(obj is String){
            println("String length is ${obj.length}")
        }
    }

    @Test
    fun testFor(){
        val list = listOf("1","2","3")

        //下标遍历
        for(index in list.indices){
            println("item is ${list[index]}")
        }

        //不带下标的遍历，相当于Java foreach
        for(item in list){
            println(item)
        }
    }




    @Test
    fun testJiHe(){
        //list
        val strList: List<String> = listOf("apple","banana", "orange")
        strList
            .filter { it.contains("e")}
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it)}

        //map
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)

        println(strList
            .filter {
                it == "a"
            }.size)
    }

    @Test
    fun testLei(){

        val rectangle = Rectangle()
    }

}

fun main(){
    val class25 = Class25()
    class25.sayHello(1)
}