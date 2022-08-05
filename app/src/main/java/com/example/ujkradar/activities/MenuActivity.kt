package com.example.ujkradar.activities


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ujkradar.R
import com.example.ujkradar.data.DataMenu
import com.example.ujkradar.databinding.ActivityMenuBinding
import com.example.ujkradar.key.Key
import com.example.ujkradar.models.MenuModel


class MenuActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMenuBinding
    private var getNoMejaPesanan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getDashboard = intent.getStringExtra(Key.KEY_DASHBOARD)
        supportActionBar?.title = getDashboard
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getNoMejaPesanan = intent.getStringExtra(Key.KEY_NO_MEJA)

        binding.btnMakanan.setOnClickListener(this)
        binding.btnMinuman.setOnClickListener(this)
        binding.btnDessert.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_makanan -> {
                val intent = Intent(this@MenuActivity, MenuListActivity::class.java)
                intent.putExtra(Key.KEY_KATEGORI, MenuModel("Makanan", DataMenu.listMenuMakanan))
                intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                startActivity(intent)
            }
            R.id.btn_minuman -> {
                val intent = Intent(this@MenuActivity, MenuListActivity::class.java)
                intent.putExtra(Key.KEY_KATEGORI, MenuModel("Minuman", DataMenu.listMenuMinuman))
                intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                startActivity(intent)
            }
            R.id.btn_dessert -> {
                val intent = Intent(this@MenuActivity, MenuListActivity::class.java)
                intent.putExtra(Key.KEY_KATEGORI, MenuModel("Dessert", DataMenu.listMenuDessert))
                intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                startActivity(intent)
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