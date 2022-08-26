package com.example.pagcom.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagcom.R
import com.example.pagcom.adapter.ListAdapter
import com.example.pagcom.databinding.FragmentCompaniesBinding
import com.example.pagcom.viewmodel.MainViewModel

class StockExchangeFragment : Fragment(R.layout.fragment_companies) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCompaniesBinding.bind(view)

        viewModel.listCompanies {
            it.let {
                binding.recyclerCompanies.adapter = ListAdapter(it!!)
                binding.recyclerCompanies.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}