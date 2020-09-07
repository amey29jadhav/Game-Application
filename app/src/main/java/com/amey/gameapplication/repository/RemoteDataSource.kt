package com.amey.gameapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amey.gameapplication.model.ArmorModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        private var remoteDataSource: RemoteDataSource? = null
        fun newInstance(): RemoteDataSource? {
            if (remoteDataSource == null) {
                remoteDataSource = RemoteDataSource()
            }
            return remoteDataSource
        }
    }

    interface ResultCallback {
        fun onSuccess()
    }

    fun getArmors(callback: ResultCallback): LiveData<List<ArmorModel>> {


        val armorsLiveData: MutableLiveData<List<ArmorModel>> by lazy {
            MutableLiveData<List<ArmorModel>>()
        }


        val armorService = RetrofitClientInstance.buildService(GameService::class.java);
        val armors = armorService.getArmors()
        armors?.enqueue(object : Callback<List<ArmorModel>> {

            override fun onResponse(
                call: Call<List<ArmorModel>>,
                response: Response<List<ArmorModel>>
            ) {
                armorsLiveData.value = response.body()
                callback.onSuccess()
            }

            override fun onFailure(call: Call<List<ArmorModel>>, t: Throwable) {
                println(t)
            }
        })

        return armorsLiveData

    }

}


