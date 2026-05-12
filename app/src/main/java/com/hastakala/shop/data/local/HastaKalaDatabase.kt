package com.hastakala.shop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Sale::class],
    version = 1,
    exportSchema = false
)
abstract class HastaKalaDatabase : RoomDatabase() {
    abstract fun saleDao(): SaleDao

    companion object {
        @Volatile
        private var INSTANCE: HastaKalaDatabase? = null

        fun getDatabase(context: Context): HastaKalaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HastaKalaDatabase::class.java,
                    "hastakala_sales_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
