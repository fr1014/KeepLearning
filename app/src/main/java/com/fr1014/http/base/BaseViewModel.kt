package com.fr1014.http.base

import androidx.lifecycle.ViewModel
import com.fr1014.extensions.log
import com.fr1014.http.exception.DealException
import com.fr1014.http.exception.ResultException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Create by fanrui07
 * Date: 2022/3/30
 * Describe:
 */
open class BaseViewModel : ViewModel() {

    suspend fun <T : Any> callRequest(
        call: suspend () -> BaseModel<T>,
        startRequestBlock: (() -> Unit)? = null,
        successBlock: (() -> Unit)? = null,
        errorBlock: (() -> Unit)? = null
    ): NetResult<T> {
        startRequestBlock?.let { it() }
        return try {
            withContext(Dispatchers.IO) {
                "callRequest dispatchers: ${Thread.currentThread().name}".log()
                val response = call()
                handleResponse(response, successBlock, errorBlock)
            }
        } catch (e: Exception) {
            //这里统一处理异常
            e.printStackTrace()
            NetResult.Error(DealException.handlerException(e))
        }
    }

    private fun <T : Any> handleResponse(
        response: BaseModel<T>,
        successBlock: (() -> Unit)? = null,
        errorBlock: (() -> Unit)? = null
    ): NetResult<T> {
        return if (response.errorCode == -1) {
            errorBlock?.let { it() }
            NetResult.Error(
                ResultException(
                    response.errorCode.toString(),
                    response.errorMsg
                )
            )
        } else {
            successBlock?.let { it() }
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