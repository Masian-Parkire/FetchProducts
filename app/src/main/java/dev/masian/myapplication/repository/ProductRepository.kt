package dev.masian.myapplication.repository

import dev.masian.myapplication.api.ApiClient
import dev.masian.myapplication.api.ApiInterface
import dev.masian.myapplication.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository {
    val apiClient= ApiClient.buildClient(ApiInterface::class.java)  //instantiating

    suspend fun getProducts(): Response<ProductResponse> {
        return withContext(Dispatchers.IO){
            apiClient.getProducts()
        }
    }
}