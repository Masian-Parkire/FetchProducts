package dev.masian.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // Import viewModelScope
import dev.masian.myapplication.model.Product
import dev.masian.myapplication.repository.ProductRepository
import kotlinx.coroutines.launch // Import launch from kotlinx.coroutines

class ProductViewModel: ViewModel() {
    private val productsRepo = ProductRepository()


    private val _productsLiveData = MutableLiveData<List<Product>>()
    val productsLiveData: LiveData<List<Product>> = _productsLiveData

    private val _errLiveData = MutableLiveData<String>()
    val errLiveData: LiveData<String> = _errLiveData

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = productsRepo.getProducts()
                if (response.isSuccessful) {
                    _productsLiveData.postValue(response.body()?.products)
                } else {
                    _errLiveData.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                _errLiveData.postValue("An error occurred: ${e.message}")
            }
        }
    }
}
