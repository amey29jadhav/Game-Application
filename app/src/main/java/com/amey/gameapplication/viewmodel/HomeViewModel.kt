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
    var showProgressBar = MutableLiveData<Boolean>()

    init {
        remoteDataSource = RemoteDataSource.newInstance()
        getArmors()
        showProgressBar.value = true
    }

    /***
     * Method to get all armors list
     */
    private fun getArmors() {
        armorList = remoteDataSource!!.getArmors(this) as MutableLiveData<List<ArmorModel>>

    }

    /***
     *  Method to filter List with search string using Transformation.switchMap
     */
    fun getArmorsWithNameLiveData(): LiveData<List<ArmorModel>>? {
        return Transformations.switchMap(nameQueryLiveData) { name: String? ->
            getArmorsWithNameLiveData(name)

        }
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
        showProgressBar.value = false
        return armorUpdatedLiveData
    }

    /***
     *  Method to set search string to MutuableLiveData String
     */
    fun setNameQuery(name: String) {
        this.nameQueryLiveData.value = name
    }

    override fun onSuccess() {
        setNameQuery("")
    }


}