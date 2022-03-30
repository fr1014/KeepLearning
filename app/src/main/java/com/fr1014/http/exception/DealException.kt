package com.fr1014.http.exception

import retrofit2.HttpException

import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException

/**
 * Create by fanrui07
 * Date: 2022/3/29
 * Describe:
 */
object DealException {

    fun handlerException(throwable: Throwable): ResultException {
        val ex: ResultException
        when (throwable) {
            is ResultException -> {
                ex = throwable
            }
            is HttpException -> {
                ex = when (throwable.code()) {
                    ApiResultCode.UNAUTHORIZED,
                    ApiResultCode.FORBIDDEN,
                        //权限错误，需要实现
                    ApiResultCode.NOT_FOUND -> ResultException(
                        throwable.code().toString(),
                        "网络错误"
                    )
                    ApiResultCode.REQUEST_TIMEOUT,
                    ApiResultCode.GATEWAY_TIMEOUT -> ResultException(
                        throwable.code().toString(),
                        "网络连接超时"
                    )
                    ApiResultCode.INTERNAL_SERVER_ERROR,
                    ApiResultCode.BAD_GATEWAY,
                    ApiResultCode.SERVICE_UNAVAILABLE -> ResultException(
                        throwable.code().toString(),
                        "服务器错误"
                    )
                    else -> ResultException(throwable.code().toString(), "网络错误")
                }
            }
            is SocketException -> {
                ex = ResultException(
                    ApiResultCode.REQUEST_TIMEOUT.toString(),
                    "网络连接错误，请重试"
                )
            }
            is SocketTimeoutException -> {
                ex = ResultException(
                    ApiResultCode.REQUEST_TIMEOUT.toString(),
                    "网络连接超时"
                )
            }
            is SSLHandshakeException -> {
                ex = ResultException(
                    ApiResultCode.SSL_ERROR,
                    "证书验证失败"
                )
                return ex
            }
            is UnknownHostException -> {
                ex = ResultException(
                    ApiResultCode.UNKNOW_HOST,
                    "网络错误，请切换网络重试"
                )
                return ex
            }
            is UnknownServiceException -> {
                ex = ResultException(
                    ApiResultCode.UNKNOW_HOST,
                    "网络错误，请切换网络重试"
                )
            }
            is NumberFormatException -> {
                ex = ResultException(
                    ApiResultCode.UNKNOW_HOST,
                    "数字格式化异常"
                )
            }
            else -> {
                ex = ResultException(
                    ApiResultCode.UNKNOWN,
                    "未知错误"
                )
            }
        }
        return ex
    }
}