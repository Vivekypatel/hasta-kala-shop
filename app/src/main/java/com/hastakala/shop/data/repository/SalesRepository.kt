package com.hastakala.shop.data.repository

import com.hastakala.shop.data.local.Sale
import com.hastakala.shop.data.local.SaleDao
import kotlinx.coroutines.flow.Flow

class SalesRepository(private val saleDao: SaleDao) {
    fun getAllSales(): Flow<List<Sale>> = saleDao.getAllSales()

    fun getSalesByDateRange(startDate: Long, endDate: Long): Flow<List<Sale>> {
        return saleDao.getSalesByDateRange(startDate, endDate)
    }

    suspend fun insertSale(sale: Sale) {
        saleDao.insertSale(sale)
    }
}
