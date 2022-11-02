package ucne.edu.apiarticulosap2.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ucne.edu.apiarticulosap2.data.ArticuloDb
import ucne.edu.apiarticulosap2.data.remote.ArticulosApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesDataBase(@ApplicationContext context: Context): ArticuloDb {
        return Room.databaseBuilder(
            context,
            ArticuloDb::class.java,
            "Articulo.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideSagApi(moshi: Moshi): ArticulosApi {
        return Retrofit.Builder()
            .baseUrl("https://gestioninventario.azurewebsites.net/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ArticulosApi::class.java)
    }


}