package com.ruvik.common

import java.text.SimpleDateFormat
import java.util.Locale

object DataTime {
    fun getDataTimeKey(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyMMddHHmmss", Locale.getDefault())
        return sdf.format(currentTimeMillis)
    }
}