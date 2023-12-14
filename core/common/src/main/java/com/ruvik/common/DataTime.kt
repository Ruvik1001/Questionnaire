package com.ruvik.common

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Utility class for working with date and time.
 */
object DataTime {

    /**
     * Generates a unique key based on the current date and time.
     *
     * @return A formatted string representing the current date and time key.
     */
    fun getDataTimeKey(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val sdf = SimpleDateFormat("yyMMddHHmmss", Locale.getDefault())
        return sdf.format(currentTimeMillis)
    }
}