package com.android.bookingcucimotor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.bookingcucimotor.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final ArrayList<Booking> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", "Pengguna");
        binding.tvWelcome.setText("Selamat Datang, " + name);

        binding.rvList.setHasFixedSize(true);
        list.addAll(getListBooking());
        showRecyclerList();
    }

    private ArrayList<Booking> getListBooking() {
        TypedArray bookingImage = getResources().obtainTypedArray(R.array.data_img_booking);
        String[] bookingName = getResources().getStringArray(R.array.data_name);
        String[] bookingPrice = getResources().getStringArray(R.array.data_price);
        String[] bookingDescription = getResources().getStringArray(R.array.data_description);

        ArrayList<Booking> listBooking = new ArrayList<>();
        for (int i = 0; i < bookingName.length; i++) {
            Booking booking = new Booking(
                    bookingImage.getResourceId(i, -1),
                    bookingName[i],
                    bookingPrice[i],
                    bookingDescription[i]
            );
            listBooking.add(booking);
        }
        bookingImage.recycle(); // Jangan lupa recycle TypedArray
        return listBooking;
    }

    private void showRecyclerList() {
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        ListBookingAdapter listBookingAdapter = new ListBookingAdapter(list);
        binding.rvList.setAdapter(listBookingAdapter);

        listBookingAdapter.setOnItemClickCallback(data -> openDetailActivity(data));
    }

    private void openDetailActivity(Booking data) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("key_booking", data);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about_page) {
            new Thread(() -> {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }).start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
