package com.example.ujkradar.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ujkradar.databinding.ItemDapurListBinding
import com.example.ujkradar.models.PesananModel


class DapurListAdapter(val onItemClickCallback: IOnItemClickCallback) : RecyclerView.Adapter<DapurListAdapter.DapurViewHolder>() {

    var listDapur = ArrayList<PesananModel>()

    fun delete(index: Int) {
        this.listDapur.removeAt(index)
    }

    class DapurViewHolder(val binding: ItemDapurListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DapurViewHolder {
        return DapurViewHolder(ItemDapurListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DapurViewHolder, position: Int) {
        val data = listDapur[position]
        holder.binding.tvNama.text = data.nama
        holder.binding.tvNomerMeja.text = "No Meja\n${data.nomerMeja}"
        holder.binding.tvHarga.text = data.harga
        holder.binding.tvWaktu.text = data.waktu

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(data, position)
        }
    }

    override fun getItemCount(): Int {
        return listDapur.size
    }

    interface IOnItemClickCallback {
        fun onItemClicked(data: PesananModel, position: Int)
    }
}