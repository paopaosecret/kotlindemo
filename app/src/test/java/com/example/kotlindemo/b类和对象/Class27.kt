package com.example.kotlindemo.b类和对象


/**
 * 2.7、密封类
 * 密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 15:48
 */
class Class27{}

//TODO 密封类自身是抽象的，不能直接实例化
//TODO 密封类不允许有非-private的构造函数， 其构造函数默认是private
sealed class Expr  //TODO 定义一个密封类
data class Const(val number: Double) : Expr()
data class Sum(val e: Expr) : Expr()

fun main(){
//    val expr = Expr();
    val obj = Const(10.0)
    println(obj.number)

    println(eval(Const(1.0)))
    println(eval(Sum(Const(1.0))))
}

//TODO 密封类的作用，当使用When表达式时，验证语句覆盖了所有情况，就不需要为该语句再添加一个else子句了
fun eval(expr: Expr): Double = when(expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e)
}
