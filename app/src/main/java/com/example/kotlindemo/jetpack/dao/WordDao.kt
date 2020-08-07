package com.example.kotlindemo.jetpack.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlindemo.jetpack.entity.Word

/**
 * 第二步：定义dao层（数据访问对象），对数据库数据进行增删改查的封装
 */
@Dao
interface WordDao{
    @Query("SELECT * from tb_word ORDER BY id DESC")
    fun getWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM tb_word")
    suspend fun deleteAll()
}