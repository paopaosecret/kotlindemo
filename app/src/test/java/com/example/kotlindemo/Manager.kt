package com.example.kotlindemo

class Manager {
    fun enter(id: Int, callBack: Callback) {
        if (id > 0) {
            callBack.onSuccess("success")
        } else {
            callBack.onFail(-1)
        }
    }
}