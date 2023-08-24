package dev.masian.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import dev.masian.myapplication.databinding.ActivityMainBinding
import dev.masian.myapplication.viewmodel.ProductViewModel


class MainActivity : AppCompatActivity() {
    val productsViewModel: ProductViewModel by viewModels()
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        productsViewModel.fetchProducts()

        productsViewModel.errLiveData.observe(this, Observer { err->
            Toast.makeText(baseContext, err, Toast.LENGTH_LONG).show()
        })

        productsViewModel.productsLiveData.observe(this, Observer { productsList->
            binding.rvProduct.layoutManager = GridLayoutManager(this@MainActivity,
                2)
            binding.rvProduct.adapter = ProductAdapter(productsList)
        })
    }
}






