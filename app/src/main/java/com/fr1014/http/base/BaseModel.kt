package com.fr1014.http.base

/**
 * Create by fanrui07
 * Date: 2022/3/29
 * Describe:
 */
data class BaseModel<out T>(val errorCode: Int, val errorMsg: String, val data: T)