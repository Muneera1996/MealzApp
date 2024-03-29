package com.example.mealzapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.ui.detail.MealDetailScreen
import com.example.mealzapp.ui.detail.MealsDetailViewModel
import com.example.mealzapp.ui.theme.MealzAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                mealsNavigation()
            }
        }
    }
}

@Composable
fun mealsNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "meals_list_screen") {
        composable(route = "meals_list_screen") {
            MealsCategoriesScreen(){mealId ->
                navController.navigate("meals_detail_screen/$mealId")
            }
        }
        composable(
            route = "meals_detail_screen/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val mealsDetailViewModel : MealsDetailViewModel = viewModel()
            MealDetailScreen(meal  = mealsDetailViewModel.mealDetailResponse.value)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealzAppTheme {
        mealsNavigation()
    }
}