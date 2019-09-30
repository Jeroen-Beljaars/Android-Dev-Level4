package com.example.shoppinglist.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product (
    @ColumnInfo
    var name: String,

    @ColumnInfo
    var quantity: Int,

    @PrimaryKey
    @ColumnInfo
    var id: Long? = null
)