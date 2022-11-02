package ucne.edu.apiarticulosap2.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ucne.edu.apiarticulosap2.data.ArticuloDb
import ucne.edu.apiarticulosap2.data.entity.Articulo
import ucne.edu.apiarticulosap2.data.remote.ArticulosApi
import ucne.edu.apiarticulosap2.data.remote.dto.ArticulosDTO
import javax.inject.Inject

class ArticuloRepository @Inject constructor(
    val db: ArticuloDb,
    private val api: ArticulosApi
) {

    suspend fun getArticulos(): List<ArticulosDTO> {
        return withContext(Dispatchers.IO){
            val resonse = api.GetArticulos()
            resonse.body()?: emptyList()
        }
    }

    suspend fun createArticulo(articulo: ArticulosDTO): ArticulosDTO? {
        return withContext(Dispatchers.IO){
            val response = api.CreateArticulo(articulo)
            response.body()

        }
    }


    suspend fun insert(articulo: Articulo)
    {
        db.daoArticulo.insert(articulo)

    }


    suspend fun delete(articulo: Articulo)
    {
        db.daoArticulo.delete(articulo)

    }


    fun getList() = db.daoArticulo.getList()



    suspend fun getArticuloById(id : Int)  = db.daoArticulo.getArticuloById(id)
}