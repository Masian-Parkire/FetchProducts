package dev.masian.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import dev.masian.myapplication.api.ApiClient
import dev.masian.myapplication.api.ApiInterface
import dev.masian.myapplication.databinding.ProductListBinding
import dev.masian.myapplication.model.Product
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductDetailsActivity : AppCompatActivity() {
    lateinit var binding: ProductListBinding
    lateinit var apiInterface: ApiInterface
    var productId = -1
    var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiInterface = ApiClient.buildClient(ApiInterface::class.java)

        val bundle = intent.extras
        if (bundle != null) {
            productId = bundle.getInt("PRODUCT_ID", -1)
        }

        fetchProductsDetails()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchProductsDetails(){
        GlobalScope.launch(Dispatchers.Main){
            val response = apiInterface.getProductsById(productId)

            if (response.isSuccessful){
                product = response.body()
                displayProductDetails()
            }
            else{
                Toast.makeText(this@ProductDetailsActivity, "Error, cannot fetch product details", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun displayProductDetails(){
        product?.let {
            binding.tvtitle.text = it.userId
            binding.tvprice.text = it.id.toString()
            binding.tvdescription.text = it.description

            Picasso.get()
                .load(it.image)
                .resize(250, 250)
                .centerCrop()
                .into(binding.ivavatar)
        }
    }
}
