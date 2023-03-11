package com.example.mycity.ui

import com.example.mycity.data.Actividad
import com.example.mycity.data.Categoria

data class uiState(
    val currentCategory: Categoria,
    val pantallaActual: Pantalla,
    val actividadActual: Actividad
)
