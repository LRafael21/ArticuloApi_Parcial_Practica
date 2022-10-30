package ucne.edu.apiarticulosap2.repository

import ucne.edu.apiarticulosap2.data.ArticuloDb
import ucne.edu.apiarticulosap2.data.entity.Articulo
import javax.inject.Inject

class ArticuloRepository @Inject constructor(
    val db: ArticuloDb
) {


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