package dev.masian.myapplication.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.masian.myapplication.model.Product

class `ProductAdapter.kt`(private val productList: List<Product>, mainActivity: MainActivity) : RecyclerView.Adapter<`ProductAdapter.kt`.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]

        holder.binding.apply {
            tvtitle.text = currentProduct.title
            tvprice.text = currentProduct.price.toString()
            tvdescription.text = currentProduct.description
            tvrates.text=currentProduct.rating.toString()

            Picasso
                .get()
                .load(currentProduct.thumbnail)
                .resize(250, 250)
                .centerCrop()
                .into(ivavatar)
        }

        holder.binding.cvProduct.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetailsActivity::class.java)
            intent.putExtra("PRODUCT_ID", currentProduct.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(var binding: ProductListBinding) : RecyclerView.ViewHolder(binding.root)
}