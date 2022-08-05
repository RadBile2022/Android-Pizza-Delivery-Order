package com.example.ujkradar.activities


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujkradar.act.MainActivity
import com.example.ujkradar.adapters.PesananListAdapter
import com.example.ujkradar.databinding.ActivityPesananListBinding
import com.example.ujkradar.key.Key
import com.example.ujkradar.models.PesananModel
import com.example.ujkradar.utilities.MappingHelper
import com.example.ujkradar.utilities.PesananHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class PesananListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPesananListBinding
    private lateinit var adapterr: PesananListAdapter
    private lateinit var pesananHelper: PesananHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val getNoMeja = intent.getStringExtra(Key.KEY_NO_MEJA)!!

        binding.btnKirim.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnTambah.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra(Key.KEY_NO_MEJA, getNoMeja)
            startActivity(intent)
        }

        adapterr = PesananListAdapter(object : PesananListAdapter.IOnItemClickCallback {
            override fun onItemClicked(data: PesananModel, position: Int) {
                val dialog = AlertDialog.Builder(this@PesananListActivity)
                dialog.setTitle("Hapus")
                    .setMessage("Apakah anda yakin menghapus menu ini ?")
                    .setPositiveButton("YA") { _, _ ->
                        pesananHelper.open()
                        pesananHelper.deleteById(data.id.toString())
                        adapterr.delete(position)
                        showListPesanan()
                    }
                    .setNegativeButton("BATAL") { dialog, _ ->
                        dialog.cancel()
                    }
                    .create()
                    .show()
            }
        })
        showPesanan(getNoMeja)
    }

    fun showPesanan(noMeja: String) {
        lifecycleScope.launch {
            pesananHelper = PesananHelper.getInstance(applicationContext)
            pesananHelper.open()

            val defferedPesanan = async(Dispatchers.IO) {
                val cursor = pesananHelper.queryByNoMeja(noMeja)
                MappingHelper.mapCursorToArrayList(cursor)
            }

            val pesanan = defferedPesanan.await()
            if (pesanan.size > 0) {
                adapterr.listPesanan = pesanan
            } else {
                adapterr.listPesanan = arrayListOf()
            }
            pesananHelper.close()

            supportActionBar?.title = "Pesanan No Meja $noMeja"
            showListPesanan()
        }
    }

    fun showListPesanan() {
        binding.rvListpesanan.apply {
            adapter = adapterr
            layoutManager = LinearLayoutManager(this@PesananListActivity)
        }
        var total = 0
        for (item in adapterr.listPesanan) {
            total += item.harga.toInt()
        }
        binding.tvTotalharga.text = "Total harga $total"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}