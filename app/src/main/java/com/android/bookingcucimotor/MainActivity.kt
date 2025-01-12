package com.android.bookingcucimotor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.bookingcucimotor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Booking>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("UserPref", Context.MODE_PRIVATE)
        val name = sharedPref.getString("name", "Pengguna")
        binding.tvWelcome.text = "Selamat Datang, $name"

        binding.rvList.setHasFixedSize(true)
        list.addAll(getListBooking())
        showRecyclerList()
    }

    private fun getListBooking(): ArrayList<Booking> {
        val bookingImage = resources.obtainTypedArray(R.array.data_img_booking)
        val bookingName = resources.getStringArray(R.array.data_name)
        val bookingPrice = resources.getStringArray(R.array.data_price)
        val bookingDescription = resources.getStringArray(R.array.data_description)

        val listBooking = ArrayList<Booking>()
        for (i in bookingName.indices) {
            val booking = Booking(bookingImage.getResourceId(i, -1), bookingName[i], bookingPrice[i], bookingDescription[i])
            listBooking.add(booking)
        }
        return listBooking
    }

    private fun showRecyclerList() {
        binding.rvList.layoutManager = LinearLayoutManager(this)
        val listBookingAdapter = ListBookingAdapter(list)
        binding.rvList.adapter = listBookingAdapter

        listBookingAdapter.setOnItemClickCallback(object : ListBookingAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Booking) {
                openDetailActivity(data)
            }
        })
    }

    private fun openDetailActivity(data: Booking) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("key_booking", data)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                Thread {
                    val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)
                }.start()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}