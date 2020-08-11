package com.example.kotlindemo.反射

import org.junit.Test

class ReflectTest {

    class A(val p: Int)

    @Test
    fun testProp(){
        val prop = A::p;  //通过反射获取A类对象的p属性
        var pValue: Int = prop.get(A(10)) //获取A(10)这个对象上的p属性
        println(pValue)
    }
}

fun main(){
//    testMethod1()
    testMethod2()
}

fun testMethod1(){
    val value = "com.example.ToastUtils#printf"    //printf方法反射测试
    if(value != null){
        val array = value.split("#")
        if(array.size == 2){
            val className = array[0]
            val functionName = array[1]

            val classImpl = Class.forName(className)
            val obj = classImpl.newInstance()
            val method = classImpl.getDeclaredMethod(functionName)
            method.invoke(obj)
        }
    }
}

fun testMethod2(){
    val value = "com.example.ToastUtils#printfStr"    //printf方法反射测试
    if(value != null){
        val array = value.split("#")
        if(array.size >= 2){
            val className = array[0]
            val functionName = array[1]
            if(array.size > 2){
                val classImpl = Class.forName(className)
                if(classImpl.declaredMethods != null && classImpl.declaredMethods.size > 0){
                    for(method in classImpl.declaredMethods){
                        if(functionName.equals(method.name)){
                            val obj = classImpl.newInstance()
                            method.invoke(obj, "hello word")
                        }
                    }
                }
            }else{
                val classImpl = Class.forName(className)
                val obj = classImpl.newInstance()
                val method = classImpl.getDeclaredMethod(functionName)
                method.invoke(obj)
            }

        }
    }
}