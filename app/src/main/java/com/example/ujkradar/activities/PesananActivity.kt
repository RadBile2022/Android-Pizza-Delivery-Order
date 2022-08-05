package com.example.ujkradar.activities


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ujkradar.R
import com.example.ujkradar.databinding.ActivityPesananBinding
import com.example.ujkradar.key.Key


class PesananActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPesananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDashboard = intent.getStringExtra(Key.KEY_DASHBOARD)
        supportActionBar?.title = getDashboard
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnLanjut.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_lanjut -> {
                val noMeja = binding.etNomerMeja.text.toString()
                if (noMeja.isNotEmpty()) {
                    val intent = Intent(this, PesananListActivity::class.java)
                    intent.putExtra(Key.KEY_NO_MEJA, noMeja)
                    startActivity(intent)
                } else {
                    binding.etNomerMeja.error = "Tidak Boleh Kosong"
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}