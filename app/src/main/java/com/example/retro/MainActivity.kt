package com.example.retro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retro.ui.theme.RetroTheme
import com.example.retro.viewmodels.ProductViewModel
import com.example.retro.views.AddProductView
import com.example.retro.views.EditProductView
import com.example.retro.views.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetroTheme { // Apply the custom theme here
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: ProductViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeView(
                viewModel,
                onNavigateToAdd = { navController.navigate("add") },
                onNavigateToEdit = { id -> navController.navigate("edit/$id") }
            )
        }
        composable("add") {
            AddProductView(viewModel, onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = "edit/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            EditProductView(productId, viewModel, onNavigateBack = { navController.popBackStack() })
        }
    }
}
