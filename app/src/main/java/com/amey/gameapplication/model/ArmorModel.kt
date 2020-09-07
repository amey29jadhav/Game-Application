package com.amey.gameapplication.model

data class ArmorModel(

    val name: String,
    val rank: String,
    val type: String,
    val defense: DefenceModel,
    val slots: List<SlotsModel>

)

data class DefenceModel(
    val base: Int
)

data class SlotsModel(
    val rank: Int
)
