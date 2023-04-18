package com.example.deliveryapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.deliveryapp.MainActivityViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.database.models.Delivery
import com.example.deliveryapp.databinding.FragmentUpdateBinding

import com.example.room.fragments.update.UpdateArgs

class Update : Fragment() {


    private lateinit var binding: FragmentUpdateBinding
    private val args by navArgs<UpdateArgs>()
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.updatelastname.setText(args.currentUser.lastName)
        binding.updatefirstname.setText(args.currentUser.firstName)
        binding.updateage.setText(args.currentUser.age.toString())

        binding.updatesenderName.setText(args.currentUser.senderName)
        binding.updatesenderContactNumber.setText(args.currentUser.senderContactNumber)
        binding.updatepickUpPostcode.setText(args.currentUser.pickUpPostcode)
        binding.updatepickUpDate.setText(args.currentUser.pickUpDate)
        binding.updatepickUpTime.setText(args.currentUser.pickUpTime)

        binding.updaterecipientName.setText(args.currentUser.recipientName)
        binding.updaterecipientContactNumber.setText(args.currentUser.recipientContactNumber)
        binding.updatedropOffPostcode.setText(args.currentUser.dropOffPostcode)
        binding.updatedropOffTime.setText(args.currentUser.dropOffTime)

        binding.updatepackageSize.setText(args.currentUser.packageSize)
        binding.updatepackageWeight.setText(args.currentUser.packageWeight)
        binding.updatequantity.setText(args.currentUser.quantity)

        binding.btnUpdate.setOnClickListener {
            updateDelivery()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    fun updateDelivery() {

        val fname = binding.updatefirstname.text.toString()
        val lname = binding.updatelastname.text.toString()
        val age = binding.updateage.text

        val senderName = binding.updatesenderName.text.toString()
        val senderContactNumber = binding.updatesenderContactNumber.text.toString()
        val pickUpPostcode = binding.updatepickUpPostcode.text.toString()
        val pickUpDate = binding.updatepickUpDate.text.toString()
        val pickUpTime = binding.updatepickUpTime.text.toString()

        val recipientName = binding.updaterecipientName.text.toString()
        val recipientContactNumber = binding.updaterecipientContactNumber.text.toString()
        val dropOffPostcode = binding.updatedropOffPostcode.text.toString()
        val dropOffTime = binding.updatedropOffTime.text.toString()

        val packageSize = binding.updatepackageSize.text.toString()
        val packageWeight = binding.updatepackageWeight.text.toString()
        val quantity = binding.updatequantity.text.toString()

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
            val update = Delivery(
                args.currentUser.id, fname, lname, Integer.parseInt(age.toString()), senderName,
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

            mainActivityViewModel.updateDelivery(update)
            val text = "Delivery successfully added"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()

            findNavController().navigate(R.id.action_update_to_listFragment2)

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

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteDelivery()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteDelivery() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete ${args.currentUser.firstName}")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            mainActivityViewModel.deleteDelivery(args.currentUser)
            val text = "Delivery successfully deleted ${args.currentUser.firstName}"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
            findNavController().navigate(R.id.action_update_to_listFragment2)
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            val text = "Deletion Operation cancelled"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(context, text, duration)
            toast.show()
        }

        builder.show()
    }
}