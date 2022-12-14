package com.example.task.data.api

import com.example.task.data.valueObject.ProductDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheProductsDBInterface {
    @GET("products/{id}")

    //i used single observable
    fun getProductDetails(@Path("")id:Int) : Single<ProductDetail>

}