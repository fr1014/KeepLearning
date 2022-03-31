package com.fr1014.http

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fr1014.extensions.log
import com.fr1014.http.base.BaseViewModel
import com.fr1014.http.base.NetResult
import com.fr1014.http.model.Banner
import kotlinx.coroutines.launch

/**
 * Create by fanrui07
 * Date: 2022/3/28
 * Describe:
 */
class BannerViewModel : BaseViewModel() {

    val bannerLiveData by lazy {
        MutableLiveData<List<Banner>>()
    }

    fun getBanner() {
        viewModelScope.launch {
            "getBanner() dispatchers: ${Thread.currentThread().name}".log()
            val result = callRequest(
                call = { WanAndroidApi.bannerApi.getBanner() },
                successBlock = { "请求成功".log() },
                errorBlock = {"请求失败".log()}
            )
            if (result is NetResult.Success) {
                bannerLiveData.postValue(result.data)
            } else if (result is NetResult.Error) {
                Log.d(
                    "hello",
                    "getBanner Error: ${result.exception.errCode} ${result.exception.msg}"
                )
            }
        }
    }

//    async和await
//    async可以支持并发，也可以支持串联通信，此时一般都跟await一起使用
//    GlobalScope.launch {
//        val result1 = GlobalScope.async {
//            getResult1()
//        }
//        val result2 = GlobalScope.async {
//            getResult2()
//        }
//        val result = result1.await() + result2.await()
//        Log.e(TAG,"result = $result")
//    }

}