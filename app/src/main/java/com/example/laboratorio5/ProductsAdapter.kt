package com.example.laboratorio5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_layout.view.*

class ProductsAdapter (val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        )

    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]

        holder.view.nombre_producto.text = product.nombre
        holder.view.cantidad.text = product.cant.toString()

    }

    class ProductViewHolder (val view: View): RecyclerView.ViewHolder(view)

}
