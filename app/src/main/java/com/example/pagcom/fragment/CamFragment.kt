package com.example.pagcom.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.pagcom.R
import com.example.pagcom.databinding.FragmentCamBinding
import com.example.pagcom.databinding.FragmentCompaniesBinding
import com.example.pagcom.viewmodel.MainViewModel

class CamFragment: Fragment(R.layout.fragment_cam) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCamBinding.bind(view)
        binding.viewModel = viewModel

        viewModel.bitmap.observe(requireActivity(), Observer { bitmap ->
            if(bitmap != null){
                binding.imgPicture.setImageBitmap(bitmap)
            }
        })
    }
}