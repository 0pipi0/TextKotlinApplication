package com.martian.code

import java.text.SimpleDateFormat
import java.util.*

/**
 * Create by：Martian
 * on 2022/5/24
 */

val dateFormat = SimpleDateFormat("HH:mm:ss:SSS")

val now = {
    dateFormat.format(Date(System.currentTimeMillis()))
}

public fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")