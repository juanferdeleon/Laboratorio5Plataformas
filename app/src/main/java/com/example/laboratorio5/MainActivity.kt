package com.example.laboratorio5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var products: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showProducts(products)

        addProductQR.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        /**
         * En caso se deslice el cardView a alguno de los lados eliminara el elemento
         */
        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                ProductAdapter(products).removeItem(viewHolder)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)

        itemTouchHelper.attachToRecyclerView(recyclerViewProducts)

    }

    /**
     * Metodo que muestra el menu superior
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Metodo que determina cada accion del menuOption
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId){

            R.id.nuevo_producto -> {
                val intent = Intent(this, NewProduct::class.java).putExtra("Products", products)
                startActivityForResult(intent, 1)
            }

            R.id.reiniciar_lista-> {
                products.clear()
                showProducts(products)
            }

            R.id.inventario_actual -> {

            }

        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Metodo que recive la lista de productos y los muestra en el Recycler View
     */
    private fun showProducts(products: ArrayList<Product>){

        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        recyclerViewProducts.adapter = ProductAdapter(products)

    }

    /**
     * Metodo que recive la informacion de la pestaña nuevo producto
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            products = data!!.getSerializableExtra("Products") as ArrayList<Product>
            showProducts(products)
        }
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    products.forEach {
                        if (it.codigo.toString().equals(result.contents.toString())){
                            it.cant += 1
                        }
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
            showProducts(products)
        }
    }

}
