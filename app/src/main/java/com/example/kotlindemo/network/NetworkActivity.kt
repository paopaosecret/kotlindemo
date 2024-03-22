package com.example.kotlindemo.network

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.KotlinApplication
import com.example.kotlindemo.R
import com.example.kotlindemo.network.business.LoginService
import com.example.kotlindemo.network.entity.InfoEntity
import com.example.mylibrary.knet.NetworkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkActivity : AppCompatActivity() {

    private lateinit var textResult: TextView
    private lateinit var editURL: EditText

    private val apiService = NetworkManager.createService(LoginService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KotlinApplication.lightTheme = if (KotlinApplication.lightTheme) {
            setTheme(R.style.ThemeLight)
            false
        } else {
            setTheme(R.style.ThemeBlack)
            true
        }
        setContentView(R.layout.activity_network)

        textResult = findViewById(R.id.tv_result)
        editURL = findViewById(R.id.et_url)


        val btnSend: Button = findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            val response = apiService.getInfo()

            response.enqueue(object : Callback<InfoEntity> {
                override fun onResponse(call: Call<InfoEntity>, response: Response<InfoEntity>) {
                    textResult.text = response.body()?.data
                }

                override fun onFailure(call: Call<InfoEntity>, t: Throwable) {
                    textResult.text = "onFailure"
                }
            })
        }

        val buttonSendCoroutine: Button = findViewById(R.id.btn_send_coroutine)
        buttonSendCoroutine.setOnClickListener {
            // 协程方式
            CoroutineScope(Dispatchers.Main).launch {
                val response = withContext(Dispatchers.IO) {
                    apiService.getInfoBySuspend()     // IO线程中执行网络请求
                }
                textResult.text = response.data       // 切回到主线程中更新 UI
            }
        }

        val buttonClear: Button = findViewById(R.id.btn_clear)
        buttonClear.setOnClickListener {
            textResult.text = ""
        }
    }
}