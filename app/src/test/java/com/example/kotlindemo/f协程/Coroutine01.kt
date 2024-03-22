package com.example.kotlindemo.f协程

import com.example.kotlindemo.Callback
import com.example.kotlindemo.Manager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.junit.Test
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


/**
 * TODO [官网说明](https://developer.android.google.cn/kotlin/coroutines?hl=zh-cn)
 *
 * TODO 协程：协程是一种并发设计模式，您可以在 Android 平台上使用它来简化异步执行的代码。
 */
class Coroutine01 {
    //TODO 创建一个协程作用域
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private fun exeCoroutine1(): String {
        //TODO Job是协程的句柄。使用 launch 或者 async 创建的每个协程都会 返回一个 Job 实例，
        // 该实例是相应协程的唯一标识并管理其生命周期
        val job: Job = coroutineScope.launch {
            println("exeCoroutine1 start:${System.currentTimeMillis()}")
            delay(2000)
            println("exeCoroutine1   end:${System.currentTimeMillis()}")
        }
        return "Coroutine1"
    }

    private fun exeCoroutine2(): String {
        coroutineScope.launch {
            println("exeCoroutine2 start:${System.currentTimeMillis()}")
            delay(2000)
            println("exeCoroutine2   end:${System.currentTimeMillis()}")
        }
        return "Coroutine2"
    }


    // 使用携程 将 callback 转换为 阻塞函数
    @Test
    fun exeCoroutine3() {
        println("testCoroutine3 start")
        coroutineScope.launch(Dispatchers.IO) {

            // 以下方法将 回调转换为 阻塞函数
            var result = withContext(Dispatchers.IO) {
                suspendCancellableCoroutine<String> { continuation ->
                    val task = Runnable {
                        Manager().enter(100, object : Callback {
                            override fun onSuccess(obj: Any?) {
                                println("testCoroutine3 onSuccess obj = $obj")
                                if (continuation.isActive) {
                                    continuation.resume(obj as String)
                                }
                            }

                            override fun onFail(result: Int) {
                                println("testCoroutine3 onFail result = $result")
                                if (continuation.isActive) {
                                    continuation.resumeWithException(Exception("Error: $result"))
                                }
                            }
                        })
                    }

                    val executor = Executors.newSingleThreadExecutor()
                    executor.submit(task)

                    // 在协程被取消时取消异步任务
                    continuation.invokeOnCancellation {
                        executor.shutdown()
                    }
                }
            }
            println("testCoroutine3 result3 = $result")

        }
        Thread.sleep(10000)
        println("testCoroutine3 end")
    }

    @Test
    fun testCoroutine() {
        println("testCoroutine start:${System.currentTimeMillis()}")
        var result1 = exeCoroutine1()
        var result2 = exeCoroutine2()

        Thread.sleep(50000)
        println("testCoroutine   end:${System.currentTimeMillis()} result1= $result1, result2=$result2")
    }
}