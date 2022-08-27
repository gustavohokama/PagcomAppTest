package com.example.pagcom.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pagcom.R
import com.example.pagcom.databinding.FragmentCompaniesBinding
import com.example.pagcom.databinding.FragmentRegistrationBinding
import com.example.pagcom.util.CpfCnpjMaks
import com.example.pagcom.viewmodel.MainViewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
        binding.viewModel = viewModel
        binding.button2.setOnClickListener {
            this.viewModel.regisName = binding.edtName.text.toString()
            this.viewModel.regisTel = binding.edtTel.text.toString()
            this.viewModel.regisCPF = binding.edtCpf.text.toString()
            this.viewModel.save()
        }
    }

}