package com.example.ujkradar.act


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ujkradar.R
import com.example.ujkradar.activities.DapurActivity
import com.example.ujkradar.activities.MenuActivity
import com.example.ujkradar.activities.PesananActivity
import com.example.ujkradar.databinding.ActivityMainBinding
import com.example.ujkradar.key.Key


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Dashboard"

        binding.btnMenu.setOnClickListener(this)
        binding.btnPesanan.setOnClickListener(this)
        binding.btnDapur.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_menu -> {
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra(Key.KEY_DASHBOARD, "Menu")
                startActivity(intent)
            }
            R.id.btn_pesanan -> {
                val intent = Intent(this, PesananActivity::class.java)
                intent.putExtra(Key.KEY_DASHBOARD, "Pesanan")
                startActivity(intent)
            }
            R.id.btn_dapur -> {
                startActivity(Intent(this, DapurActivity::class.java))
            }
        }
    }
}