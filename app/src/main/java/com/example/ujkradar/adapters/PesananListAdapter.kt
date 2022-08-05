package com.example.ujkradar.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ujkradar.databinding.ItemPesananListBinding
import com.example.ujkradar.models.PesananModel


class PesananListAdapter(private val onItemClickCallback: IOnItemClickCallback) :RecyclerView.Adapter<PesananListAdapter.ListViewHolder>() {
    var listPesanan = ArrayList<PesananModel>()

    fun delete(index: Int) {
        this.listPesanan.removeAt(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemPesananListBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listPesanan[position]
        holder.binding.idtvNama.text = data.nama
        holder.binding.idtvHarga.text = data.harga

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data, position)
        }
    }

    override fun getItemCount(): Int {
        return listPesanan.size
    }


    class ListViewHolder(val binding: ItemPesananListBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface IOnItemClickCallback {
        fun onItemClicked(data: PesananModel, position: Int)
    }
}