package com.example.pagcom.fragment

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pagcom.R
import com.example.pagcom.databinding.FragmentCompaniesBinding
import com.example.pagcom.databinding.FragmentRegistrationBinding
import com.example.pagcom.util.CpfCnpjMaks
import com.example.pagcom.util.unmask
import com.example.pagcom.viewmodel.MainViewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRegistrationBinding.bind(view)
        binding.viewModel = viewModel

        maskCPFOnEdt(binding)
        masTELOnEdt(binding)
        btnSave(binding)
    }

    private fun masTELOnEdt(binding: FragmentRegistrationBinding) {
        binding.edtTel.addTextChangedListener(PhoneNumberFormattingTextWatcher("BR"))
    }

    private fun maskCPFOnEdt(binding: FragmentRegistrationBinding) {
        val cpfCnpjMaks = CpfCnpjMaks
        binding.edtCpf.addTextChangedListener(cpfCnpjMaks.insert(binding.edtCpf))
    }

    private fun btnSave(binding: FragmentRegistrationBinding) {
        binding.button2.setOnClickListener {
            this.viewModel.regisName = binding.edtName.text.toString()
            this.viewModel.regisTel = binding.edtTel.text.toString().unmask()
            this.viewModel.regisCPF = binding.edtCpf.text.toString().unmask()
            this.viewModel.save {
                binding.edtName.text.clear()
                binding.edtName.requestFocus()
                binding.edtTel.text.clear()
                binding.edtCpf.text.clear()
            }
        }
    }
}