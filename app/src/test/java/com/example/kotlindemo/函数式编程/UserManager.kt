package com.example.kotlindemo.函数式编程

import com.example.kotlindemo.Manager
import com.example.kotlindemo.b类和对象.User
import kotlinx.coroutines.flow.flow

import com.example.kotlindemo.*;
import kotlinx.coroutines.flow.Flow


class UserManager {
    fun getUserById(id: String): Flow<User> {
        return flow {
            var user = User(name = "zhans", age = 18)

            Manager().enter(1, object : Callback {
                override fun onSuccess(result: Any?) {

                }

                override fun onFail(result: Int) {
                }

            })
            emit(user)

        }
    }



}