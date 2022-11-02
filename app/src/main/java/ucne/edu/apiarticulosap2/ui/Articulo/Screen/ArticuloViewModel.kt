package ucne.edu.apiarticulosap2.ui.Articulo.Screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ucne.edu.apiarticulosap2.data.entity.Articulo
import ucne.edu.apiarticulosap2.data.remote.dto.ArticulosDTO
import ucne.edu.apiarticulosap2.repository.ArticuloRepository
import javax.inject.Inject

data class ArticuloUiState(
    val id: Int = 0,
    val descripcion : String = "",
    val marca : String = "",
    val existencia : String = ""
)

@HiltViewModel
class ArticuloViewModel @Inject constructor(
    private val repository : ArticuloRepository
) : ViewModel() {
    var uiState by mutableStateOf(ArticuloUiState())
        private set



    fun Guardar(articulo: ArticulosDTO){
        viewModelScope.launch {
            repository.createArticulo(articulo)
        }
    }

    fun findById(id:Int) {
        viewModelScope.launch {
            repository.getArticuloById(id)?.let {
                uiState = uiState.copy(
                   id = it.articuloId,
                    descripcion =  it.descripcion,
                    marca = it.marca,
                    existencia = it.existencia.toString()
                )
            }
        }
    }

    fun setDescripcion(newValue: String) {
        uiState = uiState.copy(descripcion = newValue)
    }

    fun setMarca(it: String) {
        uiState = uiState.copy(marca = it)
    }

    fun setExistencia(it: String) {
        uiState = uiState.copy(existencia = it)
    }

}