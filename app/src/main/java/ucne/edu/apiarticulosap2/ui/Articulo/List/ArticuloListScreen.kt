package ucne.edu.apiarticulosap2.ui.Articulo.List

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ucne.edu.apiarticulosap2.data.entity.Articulo
import javax.inject.Inject

@Composable
fun ArticuloListScreen(
    onClickArticulos: () -> Unit,
    viewModel: ArticuloListViewModel = hiltViewModel(),
    onListClick: (Int) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Articulos") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickArticulos) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        val lista = viewModel.uiState.articulos
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            lista.forEach{
                item{
                    ArticuloRow(articulo = it, onArticuloClick = onListClick)
                }
            }
        }
    }
}

@Composable
fun ArticuloRow(articulo: Articulo, onArticuloClick: (Int)->Unit) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .clickable{onArticuloClick(articulo.articuloId)}
    ){
        Row() {
            Text(
                text = "Descripción: ",
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = " ${articulo.descripcion} "
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row() {
            Text(
                text = "Marca: ",
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = " ${articulo.marca} "
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row() {
            Text(
                text = "Existencia: ",
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = " ${articulo.existencia} "
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {


            IconButton(
                modifier = Modifier.padding(0.dp),
                onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Clear, contentDescription = "Delete Article",
                    tint = Color.Red,

                    )
            }

            IconButton(
                onClick = {onArticuloClick(articulo.articuloId) }) {
                Icon(
                    imageVector = Icons.Default.Edit, contentDescription = "Edit Article",
                    tint = Color.Blue,

                    )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            color = Color.Gray
        )
    }
}



