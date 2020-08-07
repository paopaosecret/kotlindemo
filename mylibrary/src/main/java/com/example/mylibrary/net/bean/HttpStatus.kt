package com.example.mylibrary.net.bean

enum class HttpStatus(val code: Int, val message: String) {
    SUCCESS(200, "success"),
    NOT_FOUND(400, "no found");
}