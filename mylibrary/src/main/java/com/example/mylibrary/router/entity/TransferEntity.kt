package com.example.mylibrary.router.entity

import java.io.Serializable

/**
 * 协议实体
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:42
 */
class TransferEntity : Serializable {
    var scheme: String? = null
    var platform: String? = null
    var type: String? = null
    var business: String? = null
    var key: String? = null
    var params: String? = null
}