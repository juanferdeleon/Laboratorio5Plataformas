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

        //Save Button
        addProduct.setOnClickListener {

            var exists: Boolean = false

            val productName: String = findViewById<TextView>(R.id.productName).text.toString()
            val productCode: String = findViewById<TextView>(R.id.productCode).text.toString()

            if (productName == "" && productCode == ""){
                Toast.makeText(this, "ERROR: Texto vacio", Toast.LENGTH_SHORT).show()
            } else {
                products.forEach {
                    if (it.codigo == productCode.toInt()){
                        Toast.makeText(this, "ERROR: Codigo ya existe", Toast.LENGTH_SHORT).show()
                        exists = true
                    }
                }

                if (!exists){
                    val newProduct = Product(productCode.toInt(), productName)
                    products.add(newProduct)
                    Toast.makeText(this, "Se ha agregado con exito.", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java).putExtra("Products", products)
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }

        }

    }
}
