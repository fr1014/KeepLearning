package com.fr1014.http

import com.fr1014.http.base.BaseModel
import com.fr1014.http.model.Banner
import retrofit2.http.GET

/**
 * Create by fanrui07
 * Date: 2022/3/28
 * Describe:
 */
interface IBannerApi {

    @GET("banner/json")
    suspend fun getBanner() : BaseModel<List<Banner>>
}