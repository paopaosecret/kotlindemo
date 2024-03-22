package com.example.kotlindemo

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import kotlin.reflect.KClass

abstract class BaseDao<T : Any> {
    private val tag: String = "BaseDao"


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(word: T)

    @Query("SELECT * from tb_word ORDER BY id DESC")
    abstract suspend fun getAll(): LiveData<List<T>>

    suspend fun deleteAll() {
        val query = SimpleSQLiteQuery(
            "delete from ${getTableName()}"
        )
        if (query != null) {
            deleteAll(query)
        }
    }


    @RawQuery
    abstract suspend fun deleteAll(query: SupportSQLiteQuery): Int

    abstract fun getTableName(): String


//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(entity: T): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(vararg entityArray: T): LongArray?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    abstract fun insert(personList: List<T>): List<Long>
//
//    @Delete
//    abstract fun delete(obj: T)
//
//    fun deleteAll(): Int {
//        val query = SimpleSQLiteQuery(
//            "delete from $tableName"
//        )
//        return doDeleteAll(query)
//    }
//
//    fun deleteByParams(params: String, value: String): Int {
//        val query = SimpleSQLiteQuery("delete from $tableName where $params='${value}'")
//        return doDeleteByParams(query)
//    }
//
//    @Update
//    abstract fun update(vararg obj: T): Int
//
//    fun findAll(): List<T> {
//        val query = SimpleSQLiteQuery("select * from $tableName")
//        print("select * from $tableName")
//        return doFindAll(query)
//    }
//
//    fun find(id: Long): T? {
//        val query = SimpleSQLiteQuery(
//            "select * from $tableName where id = ?", arrayOf<Any>(id)
//        )
//        return doFind(query)
//    }
//
//    fun doQueryByLimit(vararg string: String, limit: Int = 10, offset: Int = 0): List<T> {
//        val query =
//            SimpleSQLiteQuery("SELECT * FROM $tableName WHERE ${string[0]} = '${string[1]}' limit $limit offset $offset")
//        return doQueryByLimit(query)
//    }
//
//    fun doQueryByOrder(vararg string: String, limit: Int = 10, offset: Int = 10): List<T> {
//        val query =
//            SimpleSQLiteQuery("SELECT * FROM $tableName ORDER BY ${string[0]} desc limit '${limit}' offset '${offset}'")
//        return doQueryByLimit(query)
//    }
//
    @RawQuery
    protected abstract fun doFindAll(query: SupportSQLiteQuery): List<T>
//
//    @RawQuery
//    protected abstract fun doFind(query: SupportSQLiteQuery): T
//
//    @RawQuery
//    abstract fun doDeleteAll(query: SupportSQLiteQuery): Int
//
//    @RawQuery
//    protected abstract fun doDeleteByParams(query: SupportSQLiteQuery): Int
//
//    @RawQuery
//    protected abstract fun doQueryByLimit(query: SupportSQLiteQuery): List<T>
//
//    @RawQuery
//    protected abstract fun doQueryByOrder(query: SupportSQLiteQuery): List<T>
}