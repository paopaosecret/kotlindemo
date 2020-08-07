package com.example.mylibrary.net.cache

/**
 * 缓存管理
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/15 15:12
 */
class CacheManager {
    companion object {
        val mInstance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            CacheManager()
        }
    }
}