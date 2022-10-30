package ucne.edu.apiarticulosap2.ui.Navigation

sealed class ScreensController(
    val route: String
) {

    object ArticuloScreen : ScreensController("Articulo")
    object ArticuloListScreen : ScreensController("ArticuloList")
}