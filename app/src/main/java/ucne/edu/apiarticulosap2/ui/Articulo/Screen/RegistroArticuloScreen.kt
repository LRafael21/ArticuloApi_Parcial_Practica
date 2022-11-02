package ucne.edu.apiarticulosap2.ui.Articulo.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ucne.edu.apiarticulosap2.data.remote.dto.ArticulosDTO


@Composable
fun ArticuloScreen(
    id: Int = 0,
    onSaveBack: () -> Unit,
    viewModel: ArticuloViewModel = hiltViewModel()
) {

    remember(id) {
        viewModel.findById(id)
        0
    }
    var NoExist: String? = null
    var NoMarca: String? = null
    var NoDescripcion: String? = null

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TopAppBar(
                title = { Text(text = "Registro de Articulos") },
                modifier = Modifier.padding(0.dp),
                actions = {}
            )


            OutlinedTextField(
                value = viewModel.uiState.descripcion,

                label = { Text("Descripcion") },

                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.setDescripcion(it)
                    NoDescripcion = DescripcionError(viewModel.uiState.descripcion)
                },
                isError = NoDescripcion != null
            )

            if (NoDescripcion != null) Text(text = NoDescripcion!!, color = Color.Red)


            OutlinedTextField(
                value = viewModel.uiState.marca,
                label = { Text("Marca") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.setMarca(it)
                    NoMarca = MarcaError(viewModel.uiState.marca)
                },
                isError = NoMarca != null
            )
           if (NoMarca != null) Text(text = NoMarca!!, color = Color.Red)



            OutlinedTextField(
                value = viewModel.uiState.existencia,
                label = { Text("Existencia") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.setExistencia(it)
                    NoExist =
                        ExistenciaError((viewModel.uiState.existencia.toDoubleOrNull()) ?: -1.0)
                },
                isError = NoExist != null
            )
           if (NoExist != null) Text(text = NoExist!!, color = Color.Red)


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                ,
                onClick = {
                    if (NoDescripcion == null
                        && NoMarca == null
                        && NoExist == null
                    )
                    viewModel.Guardar(
                        ArticulosDTO(
                            descripcion = viewModel.uiState.descripcion,
                            marca = viewModel.uiState.marca,
                            existencia = viewModel.uiState.existencia.toDouble()
                        )
                    )
                    onSaveBack()

                }) {
                Text(text = "Guardame")
            }

        }
    }

private fun DescripcionError(descripcion: String): String? {
    return if (descripcion.isBlank() || descripcion.length < 2) "*Ingrese una descripción valida*"
    else null
}

private fun MarcaError(marca: String): String? {
    return if (marca.isBlank()) "*Ingrese una marca valida*"
    else null
}

private fun ExistenciaError(existencia: Double): String? {
    return if (existencia <= 0) "*Ingrese una existencia valida*"
    else null
}