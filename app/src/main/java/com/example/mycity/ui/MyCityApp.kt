package com.example.mycity.ui;

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable;
import androidx.compose.ui.res.painterResource
import com.example.mycity.R

import com.example.mycity.data.Actividad;
import com.example.mycity.data.Categoria


fun CityApp(){

}

@Composable
fun showCategorias(
    categorias: List<Categoria>
){
    LazyColumn {
        items(categorias, key = {cat -> cat.id}) {
            categoria ->
            Card{
                Row() {
                    Image(painter = painterResource(categoria.image), contentDescription = categoria.nombre)
                }
            }
        }
    }
}

@Composable
fun showActividades(categoria: Categoria,
                    actividades: List<Actividad>,
    onTapBackButton: () -> Unit){
    Column {
        Row{
            IconButton(onClick = onTapBackButton) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Atrás")
            }
            Text(categoria.nombre)
        }
        Image(painter = painterResource(id = categoria.image), contentDescription = categoria.nombre)
        LazyColumn(){
            items(actividades){
                actividad ->
                Row {
                   Image(painter = painterResource(actividad.imagen), contentDescription = actividad.titulo)
                   Text(text = actividad.titulo, style = MaterialTheme.typography.h1)
                    Text(text = actividad.descripcion)
                }
            }
        }
    }
}
