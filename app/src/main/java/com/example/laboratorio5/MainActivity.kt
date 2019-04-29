package com.example.laboratorio5

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return super.onCreateOptionsMenu(menu)

        val products = listOf(
            Product(1, "Cerveza"),
            Product(1, "Cerveza"),
            Product(1, "Cerveza"),
            Product(1, "Cerveza")
        )

        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        recyclerViewProducts.adapter = ProductsAdapter(products)

    }
}
