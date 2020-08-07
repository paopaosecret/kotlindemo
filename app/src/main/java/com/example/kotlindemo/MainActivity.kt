package com.example.kotlindemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.jetpack.JetpackActivity
import com.example.mylibrary.router.HyRouter
import com.example.mylibrary.router.action.FlutterPageAction
import com.example.mylibrary.router.callback.CallBack

class MainActivity : AppCompatActivity() {
    var TAG = "MainActivity"
    var etInput: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvShow:TextView = findViewById(R.id.tv_show)
        val btnClick:Button = findViewById(R.id.btn_click)
        val jetPack: Button = findViewById(R.id.btn_jetpack)
        val rxbus: Button = findViewById(R.id.btn_rxbus)
        etInput = findViewById(R.id.et_input)


        btnClick.setOnClickListener {
            tvShow.setText("channge Text")
            if(it is Button){
                Log.d(TAG, it.text.toString())
            }
        }

        jetPack.setOnClickListener{
            val intent = Intent(it.context, JetpackActivity::class.java)
            startActivity(intent)
        }

        rxbus.setOnClickListener{
            val str = etInput?.text.toString()
            HyRouter.INSTANCE.transfer(it.context, Uri.parse(str), object : CallBack {
                override fun onResult(result: Any) {
                    Log.d("HyRouter", result as String)
                }
            })
        }
    }

    override fun onDestroy() {
        FlutterPageAction.INSTANCE.unInit()
        super.onDestroy()
    }
}