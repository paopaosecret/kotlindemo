package com.example.mylibrary.router.parser

import android.net.Uri
import android.text.TextUtils
import com.example.mylibrary.router.entity.TransferEntity
import com.example.mylibrary.router.protocel.HyRouterConstant

/**
 *
 * 协议实体的解析、验证
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/7/30 10:42
 */
object TransferEntityParse {
    private fun checkEntityLegal(entity: TransferEntity?): Boolean {
        if (entity == null) {
            return false
        }
        return if (!TextUtils.isEmpty(entity.scheme)
            && HyRouterConstant.HYROUTER_SCHEME.equals(entity.scheme)
            && !TextUtils.isEmpty(entity.platform)
            && (HyRouterConstant.HYROUTER_HOST_FLUTTER.equals(entity.platform) || HyRouterConstant.HYROUTER_HOST_WEB.equals(entity.platform) || HyRouterConstant.HYROUTER_HOST_NATIVE.equals(entity.platform))
            && !TextUtils.isEmpty(entity.type)
            && (HyRouterConstant.HYROUTER_NATIVE_TYPE_PAGE.equals(entity.type) || HyRouterConstant.HYROUTER_NATIVE_TYPE_FUNCTION.equals(entity.type))
            && !TextUtils.isEmpty(entity.business)
            && HyRouterConstant.HYROUTER_NATIVE_BUSINESS_SHANGJIATONG.equals(entity.business)
        ) {
            true
        } else {
            false
        }
    }

    fun parseEntity(uri: Uri?): TransferEntity? {
        return if (uri == null) {
            null
        } else {
            val entity = TransferEntity()
            entity.scheme = uri.scheme
            entity.platform = uri.host
            if (TextUtils.isEmpty(uri.path)) {
                return null
            }
            val pathArray =
                uri.path!!.split("/".toRegex()).toTypedArray()
            if (pathArray.size == 4) {
                entity.type = pathArray[1]
                entity.business = pathArray[2]
                entity.key = pathArray[3]
            } else {
                return null
            }
            val params = uri.getQueryParameter("params")
            entity.params = params
            if (checkEntityLegal(entity)) {
                entity
            } else null
        }
    }
}