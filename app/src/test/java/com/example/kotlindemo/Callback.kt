package com.example.kotlindemo

interface Callback {
    fun onSuccess(result: Any?)
    fun onFail(result: Int)
}