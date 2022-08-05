package com.example.ujkradar.activities


import android.content.ContentValues
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.ujkradar.databinding.ActivityMenuListDetailBinding
import com.example.ujkradar.key.Key
import com.example.ujkradar.models.ItemMenu
import com.example.ujkradar.utilities.DatabaseContract
import com.example.ujkradar.utilities.PesananHelper
import java.util.*


class MenuListDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuListDetailBinding
    private lateinit var pesananHelper: PesananHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pesananHelper = PesananHelper.getInstance(applicationContext)
        pesananHelper.open()

        val getNoMejaPesanan = intent.getStringExtra(Key.KEY_NO_MEJA)
        val getKategori = intent.getStringExtra(Key.KEY_KATEGORI)!!
        val getData = intent.getParcelableExtra<ItemMenu>(Key.KEY_DETAIL_KATEGORI)!!

        supportActionBar?.title = getKategori
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tvKategori.text = getKategori
        binding.tvNama.text = getData.nama
        binding.tvDeskripsi.text = getData.deskripsi
        binding.tvHarga.text = "HARGA : ${getData.harga}"
        Glide.with(this)
            .load(getData.image)
            .fitCenter()
            .into(binding.imgKategori)
        binding.btnPesan.setOnClickListener {
            showDialog(getData, getNoMejaPesanan)
        }

    }

    fun showDialog(menuItem: ItemMenu, noMeja: String?) {
        val title = if (noMeja != null) "Yakin ingin memesan ini?" else "Anda belum memilih No Meja makanan"
        val messege = if (noMeja != null) "No meja : $noMeja \nMenu : ${menuItem.nama} \nHarga : ${menuItem.harga}" else "Pilih No Meja dulu ?"

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(title)
            .setMessage(messege)
            .setPositiveButton("Ya") {_, _ ->
                if (noMeja != null) {
                    val values = ContentValues()
                    values.put(DatabaseContract.NoteColumn.NOMER_MEJA, noMeja)
                    values.put(DatabaseContract.NoteColumn.NAMA, menuItem.nama)
                    values.put(DatabaseContract.NoteColumn.HARGA, menuItem.harga)
                    values.put(DatabaseContract.NoteColumn.WAKTU, getCurrentDate())

                    val result = pesananHelper.insert(values)
                    if (result > 0) {
                        val intent = Intent(this, PesananListActivity::class.java)
                        intent.putExtra(Key.KEY_NO_MEJA, noMeja)
                        startActivity(intent)
                    }
                } else {
                    val intent = Intent(this, PesananActivity::class.java)
                    intent.putExtra(Key.KEY_DASHBOARD, "Pilih No Meja")
                    startActivity(intent)
                }
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}