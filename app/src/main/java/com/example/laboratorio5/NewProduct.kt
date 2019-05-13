package com.example.laboratorio5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class NewProduct : AppCompatActivity() {

    lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)

        //Products info
        products = intent.getSerializableExtra("Products") as ArrayList<Product>

        val addProduct =findViewById<Button>(R.id.saveProduct)

        addProduct.setOnClickListener {

            val productName: String = findViewById<TextView>(R.id.productName).text.toString()
            val productCode: Int = findViewById<TextView>(R.id.productCode).text.toString().toInt()
            val newProduct = Product(productCode, productName)

            products.add(newProduct)

            Toast.makeText(this, "Se ha agregado con exito.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java).putExtra("Products", products)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }

    }
}
