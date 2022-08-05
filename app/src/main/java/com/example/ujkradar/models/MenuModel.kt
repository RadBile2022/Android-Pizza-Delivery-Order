package com.example.ujkradar.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuModel(
    val kategoriMenu: String,
    val itemMenu: List<ItemMenu>
) : Parcelable

@Parcelize
data class ItemMenu(
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    val image: Int,
    ) : Parcelable