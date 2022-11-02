package ucne.edu.apiarticulosap2.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticulosDTO(
    val ariticuloId: Int = 0,
    val descripcion: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val existencia: Double = 0.0,
)