package com.hastakala.shop.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productCategory: String,
    val color: String,
    val amount: Double,
    val timestamp: Long
)
