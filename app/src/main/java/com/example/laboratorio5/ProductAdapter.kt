package com.example.laboratorio5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_layout.view.*

class ProductAdapter (val products: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        )

    }

    override fun getItemCount() = products.size

    fun removeItem(viewHolder: RecyclerView.ViewHolder){
        products.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]

        holder.view.nombre_producto.text = product.nombre
        holder.view.cantidad.text = product.cant.toString()
        holder.view.plus_button.setOnClickListener(
            View.OnClickListener {
                product.cant += 1
                holder.view.cantidad.text = product.cant.toString()
                println(product.cant)
                println("Llego aqui")
            })
        holder.view.less_button.setOnClickListener(
            View.OnClickListener {
                if (product.cant > 0){
                    product.cant -= 1
                    holder.view.cantidad.text = product.cant.toString()
                    println(product.cant)
                    println("Llego aqui")
                }
            })
    }

    class ProductViewHolder (val view: View): RecyclerView.ViewHolder(view)

}
