package com.example.mycity.data

import com.example.mycity.R

object DataClass {

    fun categorias(): List<Categoria>{
        return listOf<Categoria>(
            Categoria(0,
            nombre = "Centros Comerciales",
                clase = ClaseCategoria.CentroComercial,
                image = R.drawable.anec_blau_12
            )
        )
    }

    fun categoriaDefecto(): Categoria{
        return categorias().filter { it.clase == ClaseCategoria.Parques }.first()
    }

    fun actividadDefecto(): Actividad{
        return getActividades().filter { it.categoria == categoriaDefecto() }.first()
    }

    fun getActividades(): List<Actividad> {
        return listOf<Actividad>(
            Actividad(
                imagen = R.drawable.centro_comercial_berceo_6,
                descripcion = "",
                titulo = "",
                categoria = categorias().filter { it.clase == ClaseCategoria.CentroComercial }.first()
            )
        )
    }
}