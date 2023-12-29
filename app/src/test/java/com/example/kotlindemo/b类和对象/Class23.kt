package com.example.kotlindemo.b类和对象

/**
 * 2.3、接口、抽象类
 *  接口既可以包含抽象方法的声明，也可以包含实现，与抽象类不同，接口无法保存状态
 *  接口可以有属性，但必须声明为抽象或提供访问器的实现
 * 
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/22 10:31
 */
interface IInterfaceA{
    var name: String       //TODO 注意：接口中的属性是抽象的，该属性必须在子类的构造器中实现
    val nameImpl: String   //TODO 注意：带实现的属性必须是val修饰
        get() = "接口Name"

    fun sayame()
    fun foo(){
        println("IInterfaceA.foo${name}")
    }
}

//TODO 接口继承
interface IInterfaceB : IInterfaceA{

}

interface IInterfaceC{
    fun foo(){
        println("IInterfaceC.foo")
    }
}

//TODO 定义抽象类
abstract class Tools {
    //TODO 这个函数是抽象的，他没有实现，必须被子类重写
    abstract fun animate()

    //TODO 抽象类中的非抽象函数，并不是默认open的，但是可以标注为open
    open fun stopAnimating(){
        println("Tools stopAnimating")
    }

    fun animateTwice(){}
}

//TODO 实现接口 抽象属性需要在构造器中实现， 抽象方法需要实现
class D(override var name: String) : IInterfaceA, IInterfaceC, Tools() {
    override fun sayame() {
        println("d 实现 sayName方法")
    }

    //TODO 解决覆盖冲突
    override fun foo() {
        super<IInterfaceA>.foo()    //调用基类接口A的方法
        super<IInterfaceC>.foo()    //调用基类接口C的方法
        println("D.foo")
    }

    override fun animate() {
        println("D 实现Tools的抽象方法")
    }

    override fun stopAnimating() {
        println("D stopAnimating")
    }

//    override fun animateTwice(){}  //TODO 父类方法默认不是open的  不能重写
}

fun main(){
    val tools:Tools = D("objectD")

    val d: D = D("objectD")
    d.sayame()
    d.foo()
    d.animate()
    d.stopAnimating()
}
