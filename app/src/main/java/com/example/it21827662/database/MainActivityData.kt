package com.example.it21827662.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityData:ViewModel() {

    private val _data = MutableLiveData<List<Patient>>()

    val data:LiveData<List<Patient>> = _data

    fun setData(data: List<Patient>){
        _data.value = data
    }
}