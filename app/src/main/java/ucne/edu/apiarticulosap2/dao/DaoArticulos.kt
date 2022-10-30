package ucne.edu.apiarticulosap2.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ucne.edu.apiarticulosap2.data.entity.Articulo

@Dao
interface DaoArticulos {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articulo: Articulo)

    @Delete
    suspend fun delete(articulo: Articulo)

    @Query("SELECT * FROM ARTICULOS")
    fun getList(): Flow<List<Articulo>>

    @Query("SELECT * FROM ARTICULOS WHERE articuloId = :id")
    suspend fun getArticuloById(id : Int) : Articulo

}