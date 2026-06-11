package shibin.kmp.product_catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import shibin.kmp.product_catalog.presentation.navigation.AppNavGraph
import shibin.kmp.product_catalog.ui.theme.ProductCatalogTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductCatalogTheme {
                AppNavGraph()
            }
        }
    }
}
