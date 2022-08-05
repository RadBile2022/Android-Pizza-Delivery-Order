package com.example.ujkradar.activities


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ujkradar.adapters.MenuListAdapter
import com.example.ujkradar.databinding.ActivityMenuListBinding
import com.example.ujkradar.key.Key
import com.example.ujkradar.models.ItemMenu
import com.example.ujkradar.models.MenuModel


class MenuListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuListBinding
    private var getNoMejaPesanan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getData = intent.getParcelableExtra<MenuModel>(Key.KEY_KATEGORI) as MenuModel
        supportActionBar?.title = getData.kategoriMenu
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getNoMejaPesanan = intent.getStringExtra(Key.KEY_NO_MEJA)

        reference(getData)
    }

    fun reference(menuKategori: MenuModel) {
        val menu = MenuModel(menuKategori.kategoriMenu, menuKategori.itemMenu)

        binding.rvListMenu.apply {
            val adapterRv = MenuListAdapter(menu)
            adapter = adapterRv
            adapterRv.onItemClick(object : MenuListAdapter.IOnItemClickCallback {
                override fun onItemClicked(item: ItemMenu) {
                    val intent = Intent(this@MenuListActivity, MenuListDetailActivity::class.java)
                    intent.putExtra(Key.KEY_DETAIL_KATEGORI, item)
                    intent.putExtra(Key.KEY_KATEGORI, menuKategori.kategoriMenu)
                    intent.putExtra(Key.KEY_NO_MEJA, getNoMejaPesanan)
                    startActivity(intent)
                }
            })
            layoutManager = GridLayoutManager(this@MenuListActivity, 2)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}