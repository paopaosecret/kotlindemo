package com.example.kotlindemo.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hyrouter.annotation.RouterPage
import com.example.kotlindemo.KotlinApplication
import com.example.kotlindemo.R
import com.example.kotlindemo.jetpack.adapter.WordAdapter
import com.example.kotlindemo.jetpack.entity.Word
import com.example.kotlindemo.jetpack.viewmodel.WordViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

@RouterPage(key = "jetpack")
class JetpackActivity: AppCompatActivity() {
    private lateinit var  mWordViewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)

        initView()
    }

    private fun initView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_test)
        val adapter = WordAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mWordViewModel =  ViewModelProvider(this).get(WordViewModel::class.java)

        mWordViewModel.mAllWords.observe(this, Observer {
            adapter.setWords(it)
        })

        val button = findViewById<FloatingActionButton>(R.id.fab_add_word)
        button.setOnClickListener {
            val num = Random().nextInt(100000)
            val word = Word( KotlinApplication.id++,"word$num", "description$num")
            mWordViewModel?.insert(word)
        }
    }
}