package com.amey.gameapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.amey.gameapplication.model.ArmorModel
import com.amey.gameapplication.repository.RemoteDataSource


class HomeViewModel : ViewModel(), RemoteDataSource.ResultCallback {

    var armorList = MutableLiveData<List<ArmorModel>>()
    var nameQueryLiveData = MutableLiveData<String?>()
    var remoteDataSource: RemoteDataSource? = null

    init {
        remoteDataSource = RemoteDataSource.newInstance()
        getArmors()
    }

    private fun getArmors() {
        armorList = remoteDataSource!!.getArmors(this) as MutableLiveData<List<ArmorModel>>

    }

    fun getArmorsWithNameLiveData(): LiveData<List<ArmorModel>>? {
        return Transformations.switchMap(nameQueryLiveData) { name: String? ->
            getArmorsWithNameLiveData(name)

        }
    }

    fun getAllArmorsLiveData(): LiveData<List<ArmorModel>> {
        return armorList
    }


    private fun getArmorsWithNameLiveData(name: String?): LiveData<List<ArmorModel>> {
        val armorupdatedlist = mutableListOf<ArmorModel>()
        val armorUpdatedLiveData: MutableLiveData<List<ArmorModel>> by lazy {
            MutableLiveData<List<ArmorModel>>()
        }

        for (row in armorList.value!!) {
            if (row.name.toLowerCase().contains(name!!.toLowerCase())) {
                armorupdatedlist.add(row)
            }
        }
        armorUpdatedLiveData.value = armorupdatedlist
        return armorUpdatedLiveData
    }

    fun setNameQuery(name: String) {
        this.nameQueryLiveData.value = name
    }

    override fun onSuccess() {
        setNameQuery("")
    }


}