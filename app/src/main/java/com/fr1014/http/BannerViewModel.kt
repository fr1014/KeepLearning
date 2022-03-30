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
//            val result = callRequest{handleResponse(WanAndroidApi.bannerApi.getBanner()) }
            val result = callRequest { WanAndroidApi.bannerApi.getBanner() }
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
}