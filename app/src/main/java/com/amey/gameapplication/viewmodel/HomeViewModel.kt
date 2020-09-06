package com.amey.gameapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amey.gameapplication.model.ArmorModel
import com.amey.gameapplication.repository.RemoteDataSource

class HomeViewModel : ViewModel() {

    lateinit var armorList: LiveData<List<ArmorModel>>
    var remoteDataSource: RemoteDataSource? = null

    init {
        remoteDataSource = RemoteDataSource.newInstance()
        getArmors()
    }

    private fun getArmors() {
        armorList = remoteDataSource!!.getArmors()
    }
    // TODO: Implement the ViewModel
}