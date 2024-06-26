package com.choi.part3_ch08.util

import java.text.SimpleDateFormat
import java.util.Date

object DateUtil {
    val yyyy_MM_dd=SimpleDateFormat("yyyy.MM.dd")
    val dbDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val serverDateFormat=SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")

    fun convertPrintDateString(date: Date? = Date()): String = yyyy_MM_dd.format(date)
}