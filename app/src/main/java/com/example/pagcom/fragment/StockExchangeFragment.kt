package com.example.pagcom.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagcom.R
import com.example.pagcom.adapter.ListAdapter
import com.example.pagcom.databinding.FragmentCompaniesBinding
import com.example.pagcom.viewmodel.MainViewModel

class StockExchangeFragment : Fragment(R.layout.fragment_companies) {

    private lateinit var adapter: ListAdapter
    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCompaniesBinding.bind(view)
        binding.viewModel = viewModel
        binding.txtMessage.visibility = View.INVISIBLE

        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("Processando.... Aguarde!")
        builder.create()
        val dialog = builder.show()

        viewModel.listCompanies {
            it.let {
                dialog.dismiss()
                adapter = ListAdapter(it!!)
                binding.recyclerCompanies.adapter = adapter
                binding.recyclerCompanies.layoutManager = LinearLayoutManager(requireContext())
            }
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0 != ""){
                   if(adapter.search(p0!!.toString())){
                       binding.txtMessage.visibility = View.VISIBLE
                   } else {
                       binding.txtMessage.visibility = View.INVISIBLE
                   }
                }else{
                    adapter.clearSearch()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }


}