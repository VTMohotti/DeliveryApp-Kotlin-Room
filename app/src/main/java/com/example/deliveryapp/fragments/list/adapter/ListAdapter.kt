package com.example.deliveryapp.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deliveryapp.database.models.Delivery
import com.example.deliveryapp.databinding.CustomRowBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var deliveryList = emptyList<Delivery>()
    var onItemClick: ((Delivery) -> Unit)? = null

    inner class ViewHolder(private val itemViewa: CustomRowBinding) :
        RecyclerView.ViewHolder(itemViewa.root) {

        fun bind(delivery: Delivery) {

//            itemViewa.textView2.text = user.id.toString()
//            itemViewa.fname.text = user.firstName
//           itemViewa.lname.text = user.lastName
//            itemViewa.ageShow.text = user.age.toString()

            itemViewa.lname.text = delivery.senderName
//            itemViewa.senderContactNumber.text = user.firstName
//            itemViewa.pickUpPostcode.text = user.firstName
//            itemViewa.pickUpDate.text = user.firstName
            itemViewa.txtDeliveredDate.text = delivery.pickUpTime

            itemViewa.fname.text = delivery.recipientName
//            itemViewa.recipientContactNumber.text = user.firstName
//            itemViewa.dropOffPostcode.text = user.firstName
//            itemViewa.dropOffTime.text = user.firstName
//
//            itemViewa.packageSize.text = user.firstName
//            itemViewa.packageWeight.text = user.firstName
//            itemViewa.quantity.text = user.firstName

            itemViewa.row.setOnClickListener {

                onItemClick?.invoke(delivery)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val delivery = deliveryList[position]
        holder.bind(delivery)
    }

    override fun getItemCount(): Int = deliveryList.size

    fun setData(delivery: List<Delivery>) {
        this.deliveryList = delivery
        notifyDataSetChanged()
    }


}