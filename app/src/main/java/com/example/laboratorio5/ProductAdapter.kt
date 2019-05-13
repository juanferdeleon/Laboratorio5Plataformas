package com.example.laboratorio5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_layout.view.*

/**
 * Clase Adapter para RecyclerView
 * @author Juan Fernando De Leon
 */
class ProductAdapter (val products: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    /**
     * Inflates the cardview
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        )

    }

    /**
     * Gets the item count
     */
    override fun getItemCount() = products.size

    /**
     * Removes item from recyclerView
     */
    fun removeItem(viewHolder: RecyclerView.ViewHolder){
        products.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
    }

    /**
     * Binds recyclerView
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]

        holder.view.nombre_producto.text = product.nombre
        holder.view.cantidad.text = product.cant.toString()

        /**
         * Agrega un elemento al objeto
         */
        holder.view.plus_button.setOnClickListener(
            View.OnClickListener {
                product.cant += 1
                holder.view.cantidad.text = product.cant.toString()
            })

        /**
         * Elimina un elemento al objeto
         */
        holder.view.less_button.setOnClickListener(
            View.OnClickListener {
                if (product.cant > 0){
                    product.cant -= 1
                    holder.view.cantidad.text = product.cant.toString()
                }
            })
    }

    class ProductViewHolder (val view: View): RecyclerView.ViewHolder(view)

}
