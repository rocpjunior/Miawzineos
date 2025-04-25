package com.rocpjunior.miawzineos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rocpjunior.miawzineos.adapter.MiawAdapter.MiawViewHolder
import com.rocpjunior.miawzineos.databinding.ItemImgGatoBinding
import com.squareup.picasso.Picasso

class MiawAdapter: Adapter<MiawViewHolder>() {

    private var listaImagens = emptyList<String>()

    fun adicionarLista(lista: List<String>){
        listaImagens = lista
        notifyDataSetChanged()
    }

    inner class MiawViewHolder(val binding: ItemImgGatoBinding) : ViewHolder(binding.root){

    fun bind(url: String){

        Picasso.get()
            .load(url)
            .into(binding.imItem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MiawViewHolder {

        val layoutinflate = LayoutInflater.from(parent.context)
        val itemView = ItemImgGatoBinding.inflate(layoutinflate, parent, false)
        return MiawViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: MiawViewHolder,
        position: Int
    ) {
        val url = listaImagens[position]
        holder.bind(url)
    }

    override fun getItemCount(): Int {
       return listaImagens.size
    }

}