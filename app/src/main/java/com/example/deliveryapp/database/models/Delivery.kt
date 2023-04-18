package com.example.deliveryapp.database.models

import android.os.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "delivery_table")
data class Delivery(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,

    val senderName: String,
    val senderContactNumber: String,
    val pickUpPostcode: String,
    var pickUpDate: String,
    var pickUpTime: String,

    val recipientName: String,
    val recipientContactNumber: String,
    val dropOffPostcode: String,
    val dropOffTime: String,

    val packageSize: String,
    val packageWeight: String,
    val quantity: String,
) : Parcelable