package com.martian.kotlin.enum

/**
 * Create by：Martian
 * on 2021/9/1
 */
enum class Terminal( var code: Int,var msg: String) {
    PHONE(0,"手机"),
    PC(1,"PC"),
    TV(2,"TV"),
    RECODER(3,"执法仪"),
    UAV(4,"无人机"),
    CAMERA(5,"摄像头"),
}