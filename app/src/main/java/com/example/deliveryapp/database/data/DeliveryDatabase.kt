package com.example.deliveryapp.database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliveryapp.database.dao.DeliveryDao
import com.example.deliveryapp.database.models.Delivery

@Database(entities = [Delivery::class], version = 1, exportSchema = false)
abstract class DeliveryDatabase : RoomDatabase() {

    abstract fun deliveryDao(): DeliveryDao

    companion object{
        @Volatile
        private var INSTANCE:DeliveryDatabase ?=null;

        fun getDatabase(context: Context) : DeliveryDatabase{
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeliveryDatabase::class.java,
                    "delivery_databases"
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }

}