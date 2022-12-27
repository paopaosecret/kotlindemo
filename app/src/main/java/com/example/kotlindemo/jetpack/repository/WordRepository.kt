package com.example.kotlindemo.jetpack.repository

import androidx.lifecycle.LiveData
import com.example.kotlindemo.jetpack.dao.WordDao
import com.example.kotlindemo.jetpack.entity.Word

/**
 * https://developer.android.google.cn/topic/libraries/architecture/livedata
 *
 * 第三步：定义数据的来源，数据更改时，通常需要更新UI,这意味了必须观察数据
 * LiveData，用于数据观察的生命周期库类可解决此问题。LiveData在方法描述中使用类型的返回值，然后Room会生成所有必要的代码来更新LiveData数据库。
 *
 * WordRepository：向业务提供api的封装，可包含网络请求     和  数据库请求
 *
 */
class WordRepository(private val wordDao: WordDao) {

    var mAllWords: LiveData<List<Word>> = wordDao.getWords() //数据可以从网络来，也可以从数据库缓存来

    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteAll() {
        wordDao.deleteAll();
    }
}