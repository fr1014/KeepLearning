package com.fr1014.http.exception

/**
 * Create by fanrui07
 * Date: 2022/3/29
 * Describe:
 */
class ResultException(var errCode: String?, var msg: String?) : Exception(msg)