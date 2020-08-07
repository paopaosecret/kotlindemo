package com.example.kotlindemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        char c = 'c';
        if(c == 99){
            System.out.println("true");
        }else{
            System.out.println((int)c);
        }

    }

    static void demo(Source<String> strSource){
//        Source<Object> objSource = strSource;   //Java不允许，
//        由于Java 运行时, objSource 会指向 strSource的内存空间， objSource.put的对象是Object, 不能转换为String对象
//        在Kotlin 可以通过声明处型变  out T  只生产 不消费  ：out 协变 例如nextT方法返回的参数为String，也可以当做Object用


        Comparable<Number> numberComparable= new Comparable<Number>(){

            @Override
            public int compareTo(Number other) {
                return 0;
            }
        };

//        Comparable<Integer> intComparable = numberComparable;
        //Java运行时 如果支持， intComparable 和 numberComparable指向同一内存，如果使用intComparable生产出来个某个属性应该是Integer, 结果拿出来的却是Number，所以不符合，编译时会报错
        //Kotlin 可以通过声明处型变  in：逆变 保证参数不生产  只消费， 例如：compareTo方法传入的参数如果是Integer 也可以转为Number使用
    }

    public void testPrint(){
        System.out.println("Java Test");
    }
}

//TODO 测试型变
interface Source<T>{
    T nextT();
}

//TODO 测试逆变
interface Comparable<T>{
    int compareTo(T other);        //会接收传入的参数
}