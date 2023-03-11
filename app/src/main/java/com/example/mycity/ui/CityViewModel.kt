package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.Actividad
import com.example.mycity.data.Categoria
import com.example.mycity.data.DataClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(
        uiState(
            currentCategory = DataClass.categoriaDefecto(),
            actividadActual = DataClass.actividadDefecto(),
            pantallaActual = DataClass.pantallaInicial()
        )
    )

    val uiState: StateFlow<uiState> = _uiState

    fun getActividades(): List<Actividad>{
        return DataClass.getActividades().filter {
            it.categoria == _uiState.value.currentCategory
        }
    }

    fun getCategorias(): List<Categoria>{
        return DataClass.categorias()
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

    fun irACategorias() {
        _uiState.update {
            it.copy(pantallaActual = Pantalla.Categorias)
        }
    }

}