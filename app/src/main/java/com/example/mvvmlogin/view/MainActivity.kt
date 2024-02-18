package com.example.mvvmlogin.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mvvmlogin.ui.theme.MvvmLoginTheme
import com.example.mvvmlogin.view.home.HomeScreen
import com.example.mvvmlogin.view.homedetail.HomeDetailScreen
import com.example.mvvmlogin.view.login.LoginScreen
import com.example.mvvmlogin.view.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    enum class Screens() {
        Login,
        Home,
        HomeDetails,
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
//    val viewModel = hiltViewModel<LoginViewModel>()
    NavHost(
        navController = navController,
        startDestination = "main",
//        modifier = Modifier.padding(innerPadding)
    ) {
        navigation(startDestination = MainActivity.Screens.Login.name,route= "main")
        {
            composable(route = MainActivity.Screens.Login.name) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("main")
                }
                val parentViewModel = hiltViewModel<LoginViewModel>(parentEntry)
                LoginScreen(viewModel= parentViewModel, onLoginSuccess = {
                    navController.navigate(MainActivity.Screens.Home.name)
                })
            }
            composable(route = MainActivity.Screens.Home.name) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("main")
                }
                val parentViewModel = hiltViewModel<LoginViewModel>(parentEntry)
                HomeScreen(viewModel= parentViewModel, onMedicineCardClick = {
                    navController.navigate(MainActivity.Screens.HomeDetails.name+"/${it.id}")
                })
            }
            composable(route = MainActivity.Screens.HomeDetails.name+"/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType })) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry("main")
                }
                val parentViewModel = hiltViewModel<LoginViewModel>(parentEntry)
                HomeDetailScreen(viewModel= parentViewModel, backStackEntry.arguments?.getString("userId"))
            }
        }

    }
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvvmLoginTheme {
        Greeting("Android")
    }
}