package com.example.kotlindemo.c函数

/**
 * 3.3、内联函数: 函数体 会被 直接替换到函数被调用地方
 * 使用高阶函数带来的效率损失:每个函数都是一个对象，并且会捕获一个闭包。即那些在函数体内会访问到的变量。
 * 内存分配（对于函数对象和类）和虚拟调用引入的运行时间开销
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 16:08
 */

class Function33{
    fun f1(a: Int): Int?{
        return a * a
    }

    //TODO 定义一个内联函数,内联函数参数默认也内联的
    inline fun f2(a: String, f:(s1:String, s2: String) -> String): Int {
        val fs = f.invoke(a, a)
        return a.length + fs.length
    }

    //TODO 定义一个内联函数，其参数禁用内联
    inline fun f3(a: String, noinline f:(s1:String, s2: String) -> String): Int {
        val fs = f.invoke(a, a)
        return a.length + fs.length
    }

    fun f4(){
        val str = "abc"
        val fun1 = {s1: String, s2: String -> "$s1$s2"}

        println(f2(str, fun1))   //TODO 内联函数编译时会直接用函数体替换调用的函数，并且参数中的函数默认也是内联的， 如下：
//        val fs = "$str$str"
//        println(str.length + fs.length)

        println(f3(str, fun1))
//        val fs = fun1.invoke(str,str)
//        println(str.length + fs.length)
    }
}

fun main(){
    val function33 = Function33()
    function33.f4()
}
