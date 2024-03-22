package com.example.kotlindemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.activity.TestActivity
import com.example.kotlindemo.jetpack.JetpackActivity
import com.example.kotlindemo.network.NetworkActivity
import com.example.mylibrary.router.HyRouter
import com.example.mylibrary.router.action.FlutterPageAction
import com.example.mylibrary.router.callback.CallBack


class MainActivity : AppCompatActivity() {

    val m = arrayOf(
        "merchant://native/page/shangjiatong/jetpack",
        "merchant://native/page/shangjiatong/testpage?id=2",
        "merchant://native/function/shangjiatong/test?id=2&params=zhangsan12",
        "merchant://native/function/shangjiatong/test?id=2",
        "merchant://native/function/shangjiatong/print?id=2"
    )

    var TAG = "MainActivity"
    var etInput: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvShow:TextView = findViewById(R.id.tv_show)
        val btnClick:Button = findViewById(R.id.btn_click)
        val jetPack: Button = findViewById(R.id.btn_jetpack)
        val test: Button = findViewById(R.id.btn_test)
        val rxbus: Button = findViewById(R.id.btn_rxbus)
        val spinner: Spinner = findViewById(R.id.spinner)
        val buttonNetwork: Button = findViewById(R.id.btn_network)
        etInput = findViewById(R.id.et_input)

        //将可选内容与ArrayAdapter连接起来
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //将adapter 添加到spinner中
        spinner.adapter = adapter
        //添加事件Spinner事件监听
        spinner.onItemSelectedListener = SpinnerSelectedListener()

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
        test.setOnClickListener{
            val intent = Intent(it.context, TestActivity::class.java)
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

        buttonNetwork.setOnClickListener {
            val intent = Intent(it.context, NetworkActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        FlutterPageAction.INSTANCE.unInit()
        super.onDestroy()
    }

    //使用数组形式操作
    inner class SpinnerSelectedListener : OnItemSelectedListener {
        override fun onItemSelected(arg0: AdapterView<*>?, arg1: View?, arg2: Int, arg3: Long) {
            etInput?.setText(m.get(arg2))
        }

        override fun onNothingSelected(arg0: AdapterView<*>?) {}
    }
}