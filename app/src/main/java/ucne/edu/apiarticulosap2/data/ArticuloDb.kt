package ucne.edu.apiarticulosap2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ucne.edu.apiarticulosap2.dao.DaoArticulos
import ucne.edu.apiarticulosap2.data.entity.Articulo

@Database(
    entities = [Articulo::class] ,
    version = 2,
    exportSchema = false
)
abstract class ArticuloDb: RoomDatabase(){
    abstract val daoArticulo : DaoArticulos

}