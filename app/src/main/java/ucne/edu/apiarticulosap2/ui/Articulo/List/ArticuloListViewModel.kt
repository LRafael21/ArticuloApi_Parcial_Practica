package ucne.edu.apiarticulosap2.ui.Articulo.List

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import ucne.edu.apiarticulosap2.data.entity.Articulo
import ucne.edu.apiarticulosap2.data.remote.dto.ArticulosDTO
import ucne.edu.apiarticulosap2.repository.ArticuloRepository
import java.io.IOException
import javax.inject.Inject

data class ArticuloListUiState(
    val articulos: List<ArticulosDTO> = emptyList()
)
@HiltViewModel
class ArticuloListViewModel @Inject constructor(
    private val repository: ArticuloRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticuloListUiState())
    val uiState : StateFlow<ArticuloListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.getAndUpdate {
                try {
                    it.copy(articulos = repository.getArticulos())
                }catch (ioe: IOException){
                    it.copy(articulos = emptyList())
                }
            }
        }
    }
    fun DeleteArticulo(articulo: Articulo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(articulo)
        }
    }
}