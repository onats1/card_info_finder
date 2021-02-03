package com.onats.cardinfofinder.business.util

import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import java.lang.Exception

inline fun <reified T> ResponseBody.errorModel(): T? {
    return try {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(T::class.java)
        adapter.fromJson(this.string())
    } catch (e: Exception) {
        null
    }
}