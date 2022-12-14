package com.example.task.data.repo

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}
//we created this so we don't have to create our instance separately

class NetworkState (val status: Status,val msg:String){

    //we used companion object because we want it to be static
    companion object {

        val LOADED : NetworkState = NetworkState(Status.SUCCESS,"Success")
        val LOADING :NetworkState = NetworkState(Status.RUNNING,"Running")
        val ERROR :  NetworkState = NetworkState(Status.FAILED,"Something went wrong")

    }
}

// this class to show the NetworkState