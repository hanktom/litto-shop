package com.tom.shop.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    val price: Float,
    val description: String,
    @ColumnInfo(name = "image_url")
    val image: String
)