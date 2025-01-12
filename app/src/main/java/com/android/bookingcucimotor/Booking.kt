package com.android.bookingcucimotor

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Booking(
    val img: Int,
    val name: String,
    val price: String,
    val description: String,
) : Parcelable
