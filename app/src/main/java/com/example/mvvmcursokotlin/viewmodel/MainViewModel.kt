package com.example.mvvmcursokotlin.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmcursokotlin.Usuario
import com.example.mvvmcursokotlin.domain.network.Repo

class MainViewModel: ViewModel() {

    private val repo = Repo()

    //fun fetchUserData(context:Context):LiveData<MutableList<Usuario>> {
    fun fetchUserData():LiveData<MutableList<Usuario>> {
        val mutableData = MutableLiveData<MutableList<Usuario>>()
        repo.getUserData().observeForever { userList ->
            mutableData.value = userList
        }
        return mutableData
    }

}