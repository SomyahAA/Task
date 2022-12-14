package com.example.task.productDetails

import androidx.lifecycle.LiveData
import com.example.task.data.api.TheProductsDBInterface
import com.example.task.data.repo.NetworkState
import com.example.task.data.repo.ProductDetailsNetworkDataSource
import com.example.task.data.valueObject.ProductDetail
import io.reactivex.disposables.CompositeDisposable

class ProductDetailsRepository (private val apiService: TheProductsDBInterface){
    private lateinit var productDetailsNetworkDataSource :ProductDetailsNetworkDataSource

    fun fetchProductDetails(compositeDisposable: CompositeDisposable, movieId:Int): LiveData<ProductDetail> {

        productDetailsNetworkDataSource =ProductDetailsNetworkDataSource(apiService,compositeDisposable)
        productDetailsNetworkDataSource.fetchProductDetails(movieId)

        return productDetailsNetworkDataSource.downloadedProductDetailsResponse
    }

    // catch the data in local  storage can be done here
    fun getProductDetailsNetworkState() : LiveData<NetworkState> {
        return productDetailsNetworkDataSource.networkState
    }
}