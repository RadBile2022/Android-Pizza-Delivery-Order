package com.example.ujkradar.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PesananModel(
    val id: Int,
    val nomerMeja: String,
    val nama: String,
    val harga: String,
    val waktu: String
) : Parcelable
