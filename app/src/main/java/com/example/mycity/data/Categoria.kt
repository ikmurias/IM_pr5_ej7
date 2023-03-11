package com.example.mycity.data

import androidx.annotation.DrawableRes

data class Categoria(
    val id: Int,
    val nombre: String,
    val clase: ClaseCategoria,
    @DrawableRes val image: Int
)

enum class ClaseCategoria{
    CentroComercial,Parques
}