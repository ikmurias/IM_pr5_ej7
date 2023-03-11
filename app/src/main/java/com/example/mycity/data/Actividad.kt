package com.example.mycity.data

import androidx.annotation.DrawableRes

data class Actividad (
    val categoria: Categoria,
    val descripcion: String,
    val titulo: String,
    @DrawableRes val imagen: Int
)