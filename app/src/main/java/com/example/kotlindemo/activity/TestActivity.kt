package com.example.kotlindemo.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hyrouter.annotation.RouterPage
import com.example.kotlindemo.R

@RouterPage(key = "testpage")
class TestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val params = intent.getSerializableExtra("COMMON_PARAMS")
        if(params != null){
            Toast.makeText(this, "传递参数:" + params.toString(), Toast.LENGTH_SHORT).show()
            Log.d("TestActivity", params.toString())
        }else{
            Log.d("TestActivity", "params == null")
        }

        val btnExit: Button = findViewById(R.id.btn_exit)
        btnExit.setOnClickListener{
            finish()
        }
    }
}