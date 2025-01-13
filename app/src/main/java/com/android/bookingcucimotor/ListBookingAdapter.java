package com.android.bookingcucimotor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListBookingAdapter extends RecyclerView.Adapter<ListBookingAdapter.ListViewHolder> {

    private final ArrayList<Booking> listBooking;
    private OnItemClickCallback onItemClickCallback;

    public ListBookingAdapter(ArrayList<Booking> listBooking) {
        this.listBooking = listBooking;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPhoto;
        final TextView tvName;
        final TextView tvPrice;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Booking booking = listBooking.get(position);

        holder.imgPhoto.setImageResource(booking.getImg());
        holder.tvName.setText(booking.getName());
        holder.tvPrice.setText(booking.getPrice());

        holder.itemView.setOnClickListener(view -> {
            Intent intentDetail = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intentDetail.putExtra("key_booking", listBooking.get(holder.getAdapterPosition()));
            holder.itemView.getContext().startActivity(intentDetail);
        });

        holder.itemView.setOnClickListener(view ->
                onItemClickCallback.onItemClicked(listBooking.get(holder.getAdapterPosition()))
        );
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Booking data);
    }
}
