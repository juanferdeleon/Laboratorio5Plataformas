package com.example.laboratorio5

import java.io.Serializable

data class Product (val codigo: Int,
                    val nombre: String,
                    var cant: Int = 0): Serializable