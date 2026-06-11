package shibin.kmp.product_catalog.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.delay
import shibin.kmp.product_catalog.presentation.detail.ProductDetailScreen
import shibin.kmp.product_catalog.presentation.home.HomeScreen
import shibin.kmp.product_catalog.presentation.login.LoginScreen
import shibin.kmp.product_catalog.presentation.splash.SplashScreen
import shibin.kmp.product_catalog.presentation.splash.SplashViewModel

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {

            val viewModel: SplashViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()

            LaunchedEffect(state) {
                if (!state.isLoading) {
                    delay(400)
                    if (state.isLoggedIn) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            }
            SplashScreen()
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }

                })
        }

        composable(Screen.Home.route) {
            HomeScreen(onViewProduct = { product ->
                navController.navigate(Screen.Detail.createRoute(product.id))
            }, onLogout = {
                navController.navigate(Screen.Login.route) { popUpTo(0) }
            })
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType })
        ) {
            ProductDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}