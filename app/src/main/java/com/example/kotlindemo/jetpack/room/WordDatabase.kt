package com.example.kotlindemo.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlindemo.KotlinApplication
import com.example.kotlindemo.jetpack.dao.WordDao
import com.example.kotlindemo.jetpack.entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 第四步：定义数据库
 * 什么是RoomDatabase?
 * Room是SQLite数据库之上的数据库层。
 * Room负责处理以前使用NET处理的普通任务
 * Room使用DAO向其数据库发出查询
 *
 * 编写RoomDatabase：
 * RoomDatabase类必须是抽象的并且可以扩展RoomDatabase。通常，整个应用程序只需要一个Room数据库实例。
 *
 */
@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var wordDao = database.wordDao()

                    wordDao.deleteAll()

                    var todo = Word(KotlinApplication.id++ ,"word","description")
                    wordDao.insert(todo)
                }
            }
        }
    }

    companion object{
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):  WordDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }


        }
    }


}