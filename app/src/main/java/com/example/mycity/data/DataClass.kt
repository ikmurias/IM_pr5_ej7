package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.ui.Pantalla

object DataClass {

    fun categorias(): List<Categoria>{
        return listOf<Categoria>(
            Categoria(
            nombre = "Centros Comerciales",
                clase = ClaseCategoria.CentroComercial,
                image = R.drawable.anec_blau_12
            ),
            Categoria(
                nombre = "Parques",
                clase = ClaseCategoria.Parques,
                image = R.drawable.parques
            )
        )
    }

    fun categoriaDefecto(): Categoria{
        return categorias().filter { it.clase == ClaseCategoria.Parques }.first()
    }

    fun actividadDefecto(): Actividad{
        return getActividades().filter { it.categoria == categoriaDefecto() }.first()
    }

    fun pantallaInicial(): Pantalla{
        return Pantalla.Categorias
    }

    fun getActividades(): List<Actividad> {
        return listOf<Actividad>(
            Actividad(
                imagen = R.drawable.centro_comercial_berceo_6,
                descripcion = "",
                titulo = "Centro comercial",
                categoria = categorias().filter { it.clase == ClaseCategoria.CentroComercial }.first()
            ),
            Actividad(
                imagen = R.drawable.ribera,
                descripcion = "Un parque precioso",
                titulo = "Parque la ribera",
                categoria = categorias().filter { it.clase == ClaseCategoria.Parques }.first()
            )
        )
    }
}