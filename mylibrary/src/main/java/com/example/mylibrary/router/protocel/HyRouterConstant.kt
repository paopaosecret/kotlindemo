package com.example.mylibrary.router.protocel

/**
 * 定义协议常量
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/8/4 13:55
 */
object HyRouterConstant {
    /**
     * 协议例子
     * merchant://native/page/shangjiatong/setting?id=2
     */
    /**
     * Uri scheme： merchant -》 现只支持的scheme
     */
    const val HYROUTER_SCHEME = "merchant"

    /**
     * Uri host: Flutter-> 代表目标页flutter
     */
    const val HYROUTER_HOST_FLUTTER = "flutter"

    /**
     * Uri host:  web -> 代表目标页为web
     */
    const val HYROUTER_HOST_WEB = "web"

    /**
     * Uri host： native -> 代表目标页为native
     */
    const val HYROUTER_HOST_NATIVE = "native"

    /**
     * Uri frist Path-> page:代表页面跳转
     */
    const val HYROUTER_NATIVE_TYPE_PAGE = "page"

    /**
     * Uri frist path -> function:代表方法调用
     */
    const val HYROUTER_NATIVE_TYPE_FUNCTION = "function"

    /**
     * Uri second path -> shangjiatong:代表业务方法
     */
    const val HYROUTER_NATIVE_BUSINESS_SHANGJIATONG = "shangjiatong"
}
