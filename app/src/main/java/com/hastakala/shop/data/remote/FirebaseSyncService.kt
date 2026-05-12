package com.hastakala.shop.data.remote

import com.hastakala.shop.data.local.Sale
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseSyncService(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun syncSale(sale: Sale) {
        firestore.collection("sales")
            .add(
                mapOf(
                    "productCategory" to sale.productCategory,
                    "color" to sale.color,
                    "amount" to sale.amount,
                    "timestamp" to sale.timestamp
                )
            )
            .await()
    }
}
