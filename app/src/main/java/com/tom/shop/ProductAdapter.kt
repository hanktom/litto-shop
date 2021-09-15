package com.tom.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tom.shop.db.Product

class ProductAdapter(val products: List<Product>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.title.text = products[position].title
        holder.price.text = products[position].price.toString()
        holder.desc.text = products[position].description

    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.product_title)
    val price: TextView = itemView.findViewById(R.id.product_price)
    val desc: TextView = itemView.findViewById(R.id.product_desc)
}