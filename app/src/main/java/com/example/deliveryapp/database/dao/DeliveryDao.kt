package com.example.deliveryapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.deliveryapp.database.models.Delivery

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDelivery(delivery: Delivery)

    @Query("SELECT * FROM delivery_table ORDER BY id ASC")
    fun readAllDelivery():LiveData<List<Delivery>>

    @Update
    suspend fun updateDelivery(delivery : Delivery)

    @Delete
    suspend fun deleteDelivery(delivery : Delivery)

    @Query("DELETE FROM delivery_table")
    fun deleteAll()

}