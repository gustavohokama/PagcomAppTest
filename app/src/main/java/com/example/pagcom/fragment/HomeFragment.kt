package com.example.pagcom.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pagcom.R
import com.example.pagcom.databinding.FragmentHomeBinding
import com.example.pagcom.viewmodel.MainViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
    }

}