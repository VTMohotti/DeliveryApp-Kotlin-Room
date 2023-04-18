package com.example.deliveryapp.repository

import androidx.lifecycle.LiveData
import com.example.deliveryapp.database.dao.DeliveryDao
import com.example.deliveryapp.database.models.Delivery

class DeliveryRepository(private val deliveryDao: DeliveryDao) {

    val readAllData : LiveData<List<Delivery>> = deliveryDao.readAllDelivery()

    suspend fun addDelivery(delivery: Delivery){
        deliveryDao.addDelivery(delivery)
    }

    suspend fun updateDelivery(delivery:Delivery){
        deliveryDao.updateDelivery(delivery)
    }

    suspend fun deleteDelivery(delivery: Delivery){
        deliveryDao.deleteDelivery(delivery)
    }

    suspend fun deleteAll(){
        deliveryDao.deleteAll()
    }

}