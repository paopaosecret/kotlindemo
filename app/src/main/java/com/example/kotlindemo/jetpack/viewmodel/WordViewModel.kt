package com.example.kotlindemo.jetpack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlindemo.jetpack.entity.Word
import com.example.kotlindemo.jetpack.repository.WordRepository
import com.example.kotlindemo.jetpack.room.WordDatabase
import kotlinx.coroutines.launch

/**
 * 什么是ViewModel
 * ViewModel的作用是提供数据、UI的映射，ViewModel充当存储库和UI之间的通信中心。
 *
 * 使用ViewModel好处
 * 您可以将观察者放在数据上（而不是轮询更改），并且仅在数据实际更改时才更新UI。
 *
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: WordRepository
    val mAllWords: LiveData<List<Word>>

    init {
        val wordDao = WordDatabase.getDatabase(application, viewModelScope).wordDao()

        mRepository = WordRepository(wordDao)
        mAllWords = mRepository.mAllWords
    }

    fun insert(todo: Word) = viewModelScope.launch {
        mRepository.insert(todo)
    }

}