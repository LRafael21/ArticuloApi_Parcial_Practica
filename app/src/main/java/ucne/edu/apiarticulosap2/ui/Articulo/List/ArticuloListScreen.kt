package ucne.edu.apiarticulosap2.ui.Articulo.List

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ucne.edu.apiarticulosap2.data.remote.dto.ArticulosDTO


@Composable
fun ArticuloListScreen(
    onClickArticulos: () -> Unit,
    viewModel: ArticuloListViewModel = hiltViewModel(),
    onArticuloClicks: (Int) -> Unit,
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
        },

        content = { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()

            PrincipalScreen(
                articulos = uiState.articulos,
                onListClick = onArticuloClicks

            )
        }


    )
}

@Composable
fun PrincipalScreen(
    articulos: List<ArticulosDTO> = emptyList(),
    onListClick: (Int) -> Unit

) {

    Surface(
    ) {
        LazyColumn {
            items(articulos) { aux ->
                ArticuloRow(
                   articulo = aux,
                    onArticuloClick = onListClick
                )
            }
        }
    }
}

@Composable
fun ArticuloRow(
    articulo: ArticulosDTO, onArticuloClick: (Int) -> Unit

) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .clickable { onArticuloClick(articulo.ariticuloId) }
    ) {
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
                onClick = { onArticuloClick(articulo.ariticuloId) }) {
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



