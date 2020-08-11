package com.example.hyrouter.compiler

import javax.annotation.processing.Messager
import javax.tools.Diagnostic

/**
 *
 * @作者 zhaobing04
 *
 * @创建日期 2020/8/11 9:17
 */
class Logger( val mMsg: Messager) {

    private val PREFIX_OF_LOGGER = "HyRouter::"

    private fun print(kind: Diagnostic.Kind, info: CharSequence?) {
        if (!info.isNullOrBlank()) {
            mMsg.printMessage(kind, "$PREFIX_OF_LOGGER $info")
        }
    }

    fun info(info: CharSequence?) {
        print(Diagnostic.Kind.NOTE, "$info")
    }

    fun warning(waring: CharSequence?) {
        print(Diagnostic.Kind.WARNING, "$waring")
    }

    fun error(error: CharSequence?) {
        print(Diagnostic.Kind.ERROR, "There is an error [$error]")
    }

    fun error(error: Throwable) {
        print(Diagnostic.Kind.ERROR, "There is an error [${error.message}]\n ${formatStackTrace(error.stackTrace)}")
    }

    private fun formatStackTrace(stackTrace: Array<StackTraceElement>): String {
        val sb = StringBuilder()
        for (element in stackTrace) {
            sb.append("    at ").append(element.toString()).append("\n")
        }
        return sb.toString()
    }
}