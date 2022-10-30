package ucne.edu.apiarticulosap2.ui.Articulo.List

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ucne.edu.apiarticulosap2.data.entity.Articulo
import ucne.edu.apiarticulosap2.repository.ArticuloRepository
import javax.inject.Inject

data class ArticuloListUiState(
    val articulos: List<Articulo> = emptyList()
)
@HiltViewModel
class ArticuloListViewModel @Inject constructor(
    private val repository: ArticuloRepository,
) : ViewModel() {
    var uiState by mutableStateOf(ArticuloListUiState())
        private set

    init {
        viewModelScope.launch {
            repository.getList().collect() {
                uiState = uiState.copy(articulos = it)
            }
        }
    }

    fun DeleteArticulo(articulo: Articulo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(articulo)
        }
    }
}