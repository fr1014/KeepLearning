package com.fr1014.http

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Create by fanrui07
 * Date: 2022/3/29
 * Describe:
 */
class HttpErrorCallAdapterFactory : CallAdapter.Factory() {

    companion object {
        fun create() = HttpErrorCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return null
    }
}