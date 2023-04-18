package com.example.deliveryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.database.data.DeliveryDatabase
import com.example.deliveryapp.database.models.Delivery
import com.example.deliveryapp.repository.DeliveryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application): AndroidViewModel(application) {

    private val repo : DeliveryRepository
    val readAllData :LiveData<List<Delivery>>
    init {
        val deliveryDao = DeliveryDatabase.getDatabase(application).deliveryDao()
        repo = DeliveryRepository(deliveryDao)
        readAllData = repo.readAllData
    }


    fun addDelivery(delivery:Delivery){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addDelivery(delivery)
        }
    }

    fun updateDelivery(delivery:Delivery){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateDelivery(delivery)
        }
    }

    fun deleteDelivery(delivery : Delivery){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteDelivery(delivery)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll()
        }
    }

}