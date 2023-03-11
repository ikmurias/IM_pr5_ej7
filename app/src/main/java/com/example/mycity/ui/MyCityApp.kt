package com.example.mycity.ui;

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycity.R

import com.example.mycity.data.Actividad;
import com.example.mycity.data.Categoria

enum class Vistas {
    movil, tablet
}

@Composable
fun CityApp(
    windowsSize: WindowWidthSizeClass
) {
    val viewModel: CityViewModel = viewModel()
    //val uiState by viewModel.uiState.collectAsState()
    val vista: Vistas
    when (windowsSize) {
        WindowWidthSizeClass.Expanded -> {
            vista = Vistas.tablet
        }
        WindowWidthSizeClass.Medium -> {
            vista = Vistas.tablet
        }
        WindowWidthSizeClass.Compact -> {
            vista = Vistas.movil
        }
        else -> {
            vista = Vistas.movil
        }
    }
    if (vista == Vistas.movil) {
        vistaMovil(viewModel = viewModel)
    } else {
        vistaTablet(viewModel = viewModel)
    }
}

@Composable
fun vistaMovil(
    viewModel: CityViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.pantallaActual == Pantalla.Categorias) {
        showCategorias(categorias = viewModel.getCategorias(),
            onClick = { viewModel.updateCategory(it) })
    } else if (uiState.pantallaActual == Pantalla.Actividades) {
        showActividades(
            categoria = uiState.currentCategory,
            actividades = viewModel.getActividades(),
            onTapBackButton = { viewModel.irACategorias() },
            onTapActivity = { viewModel.cambiarActividad(it) })
    } else if (uiState.pantallaActual == Pantalla.actividad) {
        showActividad(actividad = uiState.actividadActual)
    }
}

@Composable
fun vistaTablet(viewModel: CityViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    Row {
        showCategorias(
            categorias = viewModel.getCategorias(),
            onClick = { viewModel.updateCategory(it) })
        showActividades(
            categoria = uiState.currentCategory,
            actividades = viewModel.getActividades(),
            onTapBackButton = { },
            onTapActivity = {viewModel.cambiarActividad(it)},
            showbackButton = false
        )
        showActividad(actividad = uiState.actividadActual)
    }
}

@Composable
fun showCategorias(
    categorias: List<Categoria>,
    modifier: Modifier = Modifier,
    onClick: (Categoria) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(categorias, key = { cat -> cat.id }) { categoria ->
            Card(modifier.clickable { onClick(categoria) }) {
                Row {
                    Image(
                        painter = painterResource(categoria.image),
                        contentDescription = categoria.nombre
                    )
                }
            }
        }
    }
}

@Composable
fun showActividades(
    categoria: Categoria,
    actividades: List<Actividad>,
    onTapBackButton: () -> Unit,
    onTapActivity: (Actividad) -> Unit,
    modifier: Modifier = Modifier,
    showbackButton: Boolean = true
) {
    Column(modifier = modifier) {
        if (showbackButton) {
            Row {
                IconButton(onClick = onTapBackButton) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
                }
                Text(categoria.nombre)
            }
        }
        Image(
            painter = painterResource(id = categoria.image),
            contentDescription = categoria.nombre
        )
        LazyColumn() {
            items(actividades) { actividad ->
                Row(modifier = modifier.clickable { onTapActivity(actividad) }) {
                    Image(
                        painter = painterResource(actividad.imagen),
                        contentDescription = actividad.titulo
                    )
                    Text(text = actividad.titulo, style = MaterialTheme.typography.h1)
                    Text(text = actividad.descripcion)
                }
            }
        }
    }
}

@Composable
fun showActividad(
    actividad: Actividad,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = actividad.imagen),
            contentDescription = actividad.titulo
        )
        Text(text = actividad.titulo, style = MaterialTheme.typography.h1)
        Text(text = actividad.categoria.nombre)
    }
}
