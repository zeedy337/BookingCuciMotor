package com.android.bookingcucimotor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListBookingAdapter(private val listBooking: ArrayList<Booking>): RecyclerView.Adapter<ListBookingAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (imgResource, name, price) = listBooking[position]
        holder.imgPhoto.setImageResource(imgResource)
        holder.tvName.text = name
        holder.tvPrice.text = price

        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_booking", listBooking[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listBooking[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listBooking.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Booking)
    }
}