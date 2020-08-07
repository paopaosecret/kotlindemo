package com.example.kotlindemo.a基础


/**
 *
 * 包和导入
 * 主要注意：同名类导入可以重命名
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/21 15:34
 */

//TODO 导入
import org.junit.Test
import com.example.kotlindemo.Test as DemoTest  //TODO 当有同个类名时， 可使用as 关键字对某个类做重命名


fun main(){
    test()
}

@Test
fun test(){
    val test : DemoTest = DemoTest()
    test.testPrint()
}