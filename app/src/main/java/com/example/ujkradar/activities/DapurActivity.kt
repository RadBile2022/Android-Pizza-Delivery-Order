package com.example.ujkradar.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ujkradar.adapters.DapurListAdapter
import com.example.ujkradar.databinding.ActivityDapurBinding
import com.example.ujkradar.models.PesananModel
import com.example.ujkradar.utilities.MappingHelper
import com.example.ujkradar.utilities.PesananHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class DapurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDapurBinding
    private lateinit var pesananHelper: PesananHelper
    private lateinit var adapterr: DapurListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Dapur"

        adapterr = DapurListAdapter(object : DapurListAdapter.IOnItemClickCallback {
            override fun onItemClicked(data: PesananModel, position: Int) {
                showDialog(data, position)
            }
        })
        showListDapur()
    }

    fun showListDapur() {
        lifecycleScope.launch {
            pesananHelper = PesananHelper.getInstance(applicationContext)
            pesananHelper.open()

            val defferedPesanan = async(Dispatchers.IO) {
                val cursor = pesananHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }

            val dataDapur = defferedPesanan.await()
            if (dataDapur.size >0) {
                adapterr.listDapur = dataDapur
            } else {
                adapterr.listDapur = arrayListOf()
            }
            pesananHelper.close()
            showRvDapur()
        }
    }

    fun showRvDapur() {
        binding.idrvDapur.apply {
            adapter = adapterr
            layoutManager = LinearLayoutManager(this@DapurActivity)
        }
    }

    fun showDialog(data: PesananModel, position: Int) {
        val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Confirmasi")
                .setMessage("Apakah menu sudah siap ?")
                .setPositiveButton("Sudah") { _, _ ->
                    pesananHelper.open()
                    pesananHelper.deleteById(data.id.toString())
                    adapterr.delete(position)
                    showRvDapur()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.cancel()
                }
                .create()
                .show()
    }
}