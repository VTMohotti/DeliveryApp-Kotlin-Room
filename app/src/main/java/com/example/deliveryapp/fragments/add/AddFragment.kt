package com.example.deliveryapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.deliveryapp.MainActivityViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.database.models.Delivery
import com.example.deliveryapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertIntoDatabase()
        }

        return binding.root
    }

    private fun insertIntoDatabase() {

        val fname = binding.firstname.text.toString()
        val lname = binding.lastname.text.toString()
        val age = binding.age.text

        val senderName = binding.edSenderName.text.toString()
        val senderContactNumber = binding.edContactNumber.text.toString()
        val pickUpPostcode = binding.edPickUpPostcode.text.toString()
        val pickUpDate = binding.edPickUpDate.text.toString()
        val pickUpTime = binding.edPickUpTime.text.toString()

        val recipientName = binding.edRecipientName.text.toString()
        val recipientContactNumber = binding.edRecipientContactNumber.text.toString()
        val dropOffPostcode = binding.edDropOffPostcode.text.toString()
        val dropOffTime = binding.edDropOffTime.text.toString()

        val packageSize = binding.edPackageSize.text.toString()
        val packageWeight = binding.edPackageWeight.text.toString()
        val quantity = binding.edQuantity.text.toString()

        if (validateInput(
                fname, lname, age, senderName,
                senderContactNumber,
                pickUpPostcode,
                pickUpDate,
                pickUpTime,
                recipientName,
                recipientContactNumber,
                dropOffPostcode,
                dropOffTime,
                packageSize,
                packageWeight,
                quantity
            )
        ) {
            val delivery = Delivery(
                0,
                fname,
                lname,
                Integer.parseInt(age.toString()),
                senderName,
                senderContactNumber,
                pickUpPostcode,
                pickUpDate,
                pickUpTime,
                recipientName,
                recipientContactNumber,
                dropOffPostcode,
                dropOffTime,
                packageSize,
                packageWeight,
                quantity
            )

            mainActivityViewModel.addDelivery(delivery)

            val text = "Delivery successfully added"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else {

            val text = "Please fill required fields"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }
    }

    private fun validateInput(
        fname: String, lname: String, age: Editable?, senderName: String,
        senderContactNumber: String,
        pickUpPostcode: String,
        pickUpDate: String,
        pickUpTime: String,
        recipientName: String,
        recipientContactNumber: String,
        dropOffPostcode: String,
        dropOffTime: String,
        packageSize: String,
        packageWeight: String,
        quantity: String
    ): Boolean {

        return if (age != null) {
            !(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && age.isEmpty()
                    && TextUtils.isEmpty(senderName)
                    && TextUtils.isEmpty(senderContactNumber)
                    && TextUtils.isEmpty(pickUpPostcode)
                    && TextUtils.isEmpty(pickUpDate)
                    && TextUtils.isEmpty(pickUpTime)

                    && TextUtils.isEmpty(recipientName)
                    && TextUtils.isEmpty(recipientContactNumber)
                    && TextUtils.isEmpty(dropOffPostcode)
                    && TextUtils.isEmpty(dropOffTime)

                    && TextUtils.isEmpty(packageSize)
                    && TextUtils.isEmpty(packageWeight)
                    && TextUtils.isEmpty(quantity))
        } else {
            false
        }
    }


}