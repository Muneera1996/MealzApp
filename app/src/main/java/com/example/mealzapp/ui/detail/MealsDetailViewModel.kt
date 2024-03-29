package com.example.mealzapp.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.MealResponse

class MealsDetailViewModel(private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val repository: MealsRepository = MealsRepository.getInstance()

    val mealDetailResponse  = mutableStateOf<MealResponse?>(null)

    init {
        val categoryId = savedStateHandle.get<String>("meal_category_id")?:""
        mealDetailResponse.value = repository.getMeal(categoryId)
    }
}