package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Actividad
import com.example.mycity.data.Categoria
import com.example.mycity.data.DataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class viewModel : ViewModel(){
    private val _uiState = MutableStateFlow(
        uiState(
            currentCategory = DataClass.categoriaDefecto()
        )
    )

    var uiState: StateFlow<uiState> = _uiState

    fun getActividades(): List<Actividad>{
        return DataClass.getActividades().filter {
            it.categoria == _uiState.value.currentCategory
        }
    }


    fun cambiarActividad(actividad: Actividad){
        _uiState.update {
            it.copy(
                actividadActual = actividad,
                pantallaActual = Pantalla.actividad
            )
        }
    }

    fun updateCategory(categoria: Categoria){
        _uiState.update {
            it.copy(currentCategory = categoria,
            pantallaActual = Pantalla.actividad)
        }
    }

}