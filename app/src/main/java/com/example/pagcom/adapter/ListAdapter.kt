package com.example.pagcom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagcom.R
import com.example.pagcom.web.model.CompaniesResponse
import kotlinx.android.synthetic.main.item_list_companies.view.*


class ListAdapter(private val data: List<CompaniesResponse>):
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_companies, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(companies:CompaniesResponse){
            itemView.txt_cod_companie.text = companies.cd_acao_rdz
            itemView.txt_name_companie.text = companies.nm_empresa
            itemView.txt_value_companie.text = companies.pctl_ctra
        }
    }

}

