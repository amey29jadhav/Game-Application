package com.amey.gameapplication.repository

import com.amey.gameapplication.model.ArmorModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.*

interface GameService {

    @GET("armor/")
    fun getArmors(): Call<List<ArmorModel>>

}