package com.example.ujkradar.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujkradar.databinding.ItemMenuListBinding
import com.example.ujkradar.models.ItemMenu
import com.example.ujkradar.models.MenuModel


class MenuListAdapter(val menuItem: MenuModel) : RecyclerView.Adapter<MenuListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: IOnItemClickCallback

    fun onItemClick(itemClick: IOnItemClickCallback) {
        this.onItemClickCallback = itemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemMenuListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = menuItem.itemMenu[position]

        holder.binding.nameJenis.text = data.nama
        Glide.with(holder.binding.root)
            .load(data.image)
            .fitCenter()
            .into(holder.binding.imgItem)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data)
        }
    }

    override fun getItemCount(): Int {
        return menuItem.itemMenu.size
    }

    // inner class dan interface
    class ListViewHolder(val binding : ItemMenuListBinding) : RecyclerView.ViewHolder(binding.root)

    interface IOnItemClickCallback {
        fun onItemClicked(item: ItemMenu)
    }
}