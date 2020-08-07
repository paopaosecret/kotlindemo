package com.example.kotlindemo.jetpack.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 第一步：定义数据库实体
 */
@Entity(tableName = "tb_word")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "description")
    val description:String
)