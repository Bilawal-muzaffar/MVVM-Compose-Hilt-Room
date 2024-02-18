package com.example.mvvmlogin.view.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmlogin.data.Repository
import com.example.mvvmlogin.data.model.Drug
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel   @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _userGreeting = MutableStateFlow("")
    val userGreeting: StateFlow<String> get() = _userGreeting

    private val _medicines = MutableStateFlow<List<Drug>>(emptyList())
    val medicines: StateFlow<List<Drug>> get() = _medicines

    init {
        // Fetch medicines on ViewModel initialization
        viewModelScope.launch {
            fetchMedicines()
        }
    }

    fun setUserGreeting(username: String) {
        _userGreeting.value = username
    }

    private suspend fun fetchMedicines() {
        try {
            val medicinesList = repository.getMedicines()
            _medicines.value = medicinesList
        } catch (e: Exception) {
            // Handle error
            Log.e("error", e.message.toString())
        }
    }

    suspend fun getMedicinesDetail(id:String) : Drug
    {
       return repository.getMedicineDetail(id)
    }
}