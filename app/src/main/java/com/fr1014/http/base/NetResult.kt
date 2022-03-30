package com.fr1014.http.base

import com.fr1014.http.exception.ResultException

/**
 * Create by fanrui07
 * Date: 2022/3/29
 * Describe: 泛型协变：out
 */
sealed class NetResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : NetResult<T>()

    data class Error(val exception: ResultException) : NetResult<Nothing>()
}
