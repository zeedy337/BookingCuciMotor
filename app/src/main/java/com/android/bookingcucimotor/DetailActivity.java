package com.android.bookingcucimotor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.bookingcucimotor.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private Booking dataBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.detail_page));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        dataBooking = getIntent().getParcelableExtra("key_booking");

        if (dataBooking != null) {
            if (dataBooking.getImg() != -1) {
                binding.ivDetailImg.setImageResource(dataBooking.getImg());
            } else {
                binding.ivDetailImg.setImageResource(R.drawable.baseline_image_24);
            }

            binding.tvDetailName.setText(dataBooking.getName());
            binding.tvDetailPrice.setText(dataBooking.getPrice());
            binding.tvDetailDescription.setText(dataBooking.getDescription());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
