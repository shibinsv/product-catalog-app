package shibin.kmp.product_catalog.presentation.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Login : Screen("login")

    object Home : Screen("home")

    object Detail : Screen("detail/{id}") {

        fun createRoute(id: String): String {
            return "detail/$id"
        }
    }
}