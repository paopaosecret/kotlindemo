package com.example.kotlindemo.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hyrouter.annotation.RouterPage
import com.example.kotlindemo.R

@RouterPage(key = "testpage")
class TestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val btnExit: Button = findViewById(R.id.btn_exit)
        btnExit.setOnClickListener{
            finish()
        }
    }
}