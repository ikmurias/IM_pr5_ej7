package com.example.mycity.ui;

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        showActividad(actividad = uiState.actividadActual, onTapBackButton = {viewModel.irACategorias()})
    }
}

@Composable
fun vistaTablet(viewModel: CityViewModel, modifier: Modifier =Modifier) {
    val uiState by viewModel.uiState.collectAsState()
    Row {
        showCategorias(
            categorias = viewModel.getCategorias(),
            onClick = { viewModel.updateCategory(it) },
        modifier = modifier.weight(3f))
        showActividades(
            categoria = uiState.currentCategory,
            actividades = viewModel.getActividades(),
            onTapBackButton = { },
            onTapActivity = {viewModel.cambiarActividad(it)},
            showbackButton = false,
            modifier = modifier.weight(3f)
        )
        showActividad(actividad = uiState.actividadActual, onTapBackButton = {}, modifier = modifier.weight(3f))
    }
}

@Composable
fun showCategorias(
    categorias: List<Categoria>,
    modifier: Modifier = Modifier,
    onClick: (Categoria) -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Categorias", style = MaterialTheme.typography.h2)
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
            items(categorias) { categoria ->
                Card(modifier.clickable { onClick(categoria) }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(categoria.image),
                            contentDescription = categoria.nombre,
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Fit
                        )
                        Text(text = categoria.nombre, style = MaterialTheme.typography.h6)
                    }
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
        Spacer(modifier = Modifier.height(20.dp))
        if (showbackButton) {
            Row {
                IconButton(onClick = onTapBackButton) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
                }
                Text(categoria.nombre)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 2.dp)) {
            Image(
                painter = painterResource(id = categoria.image),
                contentDescription = categoria.nombre,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text("Actividades: ${categoria.nombre}")
        }
        Divider(thickness = 2.dp)
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(actividades) { actividad ->
                Row(modifier = modifier.clickable { onTapActivity(actividad) },
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(actividad.imagen),
                        contentDescription = actividad.titulo,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = actividad.titulo, style = MaterialTheme.typography.h6)
                }
            }
        }
    }
}

@Composable
fun showActividad(
    actividad: Actividad,
    modifier: Modifier = Modifier,
    onTapBackButton: () -> Unit
) {
    BackHandler {
        onTapBackButton()
    }
    Spacer(modifier = Modifier.height(5.dp))
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = actividad.imagen),
            contentDescription = actividad.titulo
        )
        Text(text = actividad.titulo, style = MaterialTheme.typography.h4)
        Text(text = actividad.categoria.nombre)
        Text(text = actividad.descripcion)
    }
}
