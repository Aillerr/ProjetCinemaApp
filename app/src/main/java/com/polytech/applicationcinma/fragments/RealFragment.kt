package com.polytech.applicationcinma.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.databinding.FragmentPersonBinding
import com.polytech.applicationcinma.databinding.FragmentRealBinding
import com.polytech.applicationcinma.viewmodel.PersonViewModel
import com.polytech.applicationcinma.viewmodel.RealViewModel
import com.polytech.applicationcinma.viewmodelfactory.PersonViewModelFactory
import com.polytech.applicationcinma.viewmodelfactory.RealViewModelFactory


class RealFragment : Fragment() {
    private lateinit var binding: FragmentRealBinding
    private lateinit var viewModel: RealViewModel
    private lateinit var viewModelFactory: RealViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val args = RealFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        val rid = args.rid
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_real, container, false)
        binding.lifecycleOwner = this

        viewModelFactory = RealViewModelFactory(application,token,rid)
        viewModel = ViewModelProvider(this,viewModelFactory)[RealViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {

        }

        viewModel.apiOK.observe(viewLifecycleOwner, { res ->

        })



        return binding.root
    }
}