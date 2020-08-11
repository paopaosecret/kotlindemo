package com.example.hyrouter.compiler

/**
 * 使用注解处理器 生成路由mapping类的包名
 */
const val PACKAGE = "com.example.hyrouter.mapping"

/**
 * 使用注解处理器 生成路由mapping类的类名
 */
const val ROUTER_MAPPING_CLASS_NAME = "HyRouterManager"

/**
 * 生成类中的初始化方法
 */
const val METHOD_INIT = "initMapping"

/**
 * 生成类中成员变量名称
 */
const val MEMBER_NATIVE_PAGE_MAPPING = "nativePageMapping"
const val MEMBER_FLUTTER_PAGE_MAPPING = "flutterPagerMapping"
const val MEMBER_WEB_PAGE_MAPPING = "webPageMapping"
const val MEMBER_NATIVE_FUNCTION_MAPPING = "nativeFunctionMapping"
const val MEMBER_ALL_MAPPING = "allMapping"

const val MODULE_NAME = "moduleName"

/**
 * 生成类中的警告信息
 */
val WARNINGS = """   ***************************************************
                  |   * HYRouter自动生成, 请不要编辑 *
                  |   ***************************************************
                     |""".trimMargin()



