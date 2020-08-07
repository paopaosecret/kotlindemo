package com.example.mylibrary.router.action.bus

enum class ThreadModel {
    //同事件发起线程
    POSTING,
    //主线程
    MAIN,
    //子线程
    ASYNC
}