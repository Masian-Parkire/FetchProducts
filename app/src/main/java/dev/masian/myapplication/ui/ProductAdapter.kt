package dev.masian.myapplication.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.masian.myapplication.databinding.ProductListBinding
import dev.masian.myapplication.model.Product

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]

        holder.binding.apply {
            tvtitle.text = currentProduct.userId
            tvprice.text = currentProduct.id.toString()
            tvdescription.text = currentProduct.description

            Picasso.get()
                .load(currentProduct.image)
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
