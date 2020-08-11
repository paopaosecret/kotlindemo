package com.example.kotlindemo.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hyrouter.annotation.RouterFunction;
import com.example.mylibrary.router.callback.CallBack;

public class ToastUtils {

    @RouterFunction(key = "print")
    public void printf(Context context, CallBack callBack, String jsonStr){
        Toast.makeText(context, "printf方法被调用", Toast.LENGTH_SHORT).show();
        callBack.onResult("方法调用结束");
        Log.d("ToastUtils", "TEST NATIVE FUCTION");
    }

    @RouterFunction(key = "test")
    public void test(Context context, CallBack callBack, String jsonStr){
        Toast.makeText(context, "test方法被调用：param=" + jsonStr, Toast.LENGTH_SHORT).show();
        callBack.onResult("方法调用结束");
        Log.d("ToastUtils", "TEST NATIVE FUCTION");
    }
}
