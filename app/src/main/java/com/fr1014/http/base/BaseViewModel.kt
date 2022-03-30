package com.fr1014.http.base

import androidx.lifecycle.ViewModel
import com.fr1014.http.exception.DealException
import com.fr1014.http.exception.ResultException

/**
 * Create by fanrui07
 * Date: 2022/3/30
 * Describe:
 */
open class BaseViewModel : ViewModel() {

    suspend fun <T : Any> callRequest(
        call: suspend () -> BaseModel<T>
    ): NetResult<T> {
        return try {
            handleResponse(call())
        } catch (e: Exception) {
            //这里统一处理异常
            e.printStackTrace()
            NetResult.Error(DealException.handlerException(e))
        }
    }

    private fun <T : Any> handleResponse(
        response: BaseModel<T>
    ): NetResult<T> {
        return if (response.errorCode == -1) {
            NetResult.Error(
                ResultException(
                    response.errorCode.toString(),
                    response.errorMsg
                )
            )
        } else {
            NetResult.Success(response.data)
        }
    }

//    suspend fun <T : Any> callRequest(
//        call: suspend () -> NetResult<T>
//    ): NetResult<T> {
//        return try {
//            call()
//        } catch (e: Exception) {
//            //这里统一处理异常
//            e.printStackTrace()
//            NetResult.Error(DealException.handlerException(e))
//        }
//    }
//
//    suspend fun <T : Any> handleResponse(
//        response: BaseModel<T>,
//        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
//        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
//    ): NetResult<T> {
//        return coroutineScope {
//            if (response.errorCode == -1) {
//                errorBlock?.let { it() }
//                NetResult.Error(
//                    ResultException(
//                        response.errorCode.toString(),
//                        response.errorMsg
//                    )
//                )
//            } else {
//                successBlock?.let { it() }
//                NetResult.Success(response.data)
//            }
//        }
//    }

}