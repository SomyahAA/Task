package com.example.task.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.task.data.api.TheProductsDBInterface
import com.example.task.data.valueObject.ProductDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


//we will call our api using Rxjava and the api will return the movie details
//and then we will assign the movie details into liveData
// here we will use rxjava thread

class ProductDetailsNetworkDataSource (private val apiService: TheProductsDBInterface,private val compositeDisposable: CompositeDisposable){
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedProductDetailsResponse = MutableLiveData<ProductDetail>()
    val downloadedProductDetailsResponse: LiveData<ProductDetail>
        get() = _downloadedProductDetailsResponse

    fun fetchProductDetails(productId:Int){
        _networkState.postValue(NetworkState.LOADING)

        try {//making network calls
            compositeDisposable.add(apiService.getProductDetails(productId)
                .subscribeOn(Schedulers.io())
                .subscribe(//here we download product data response
                    {
                        _downloadedProductDetailsResponse.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)
                    }, {
                        //this if there was an error
                        _networkState.postValue(NetworkState.ERROR)
                        it.message?.let { it1 -> Log.e("ProductDetailsDataSource", it1) }
                    }
                )
            )
        }
        catch (e: Exception){
            e.message?.let { Log.e("ProductDetailsDataSource", it) }
        }
    }

}