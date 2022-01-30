package com.polytech.applicationcinma.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.databinding.FragmentFilmBinding
import com.polytech.applicationcinma.databinding.FragmentPersonBinding
import com.polytech.applicationcinma.viewmodel.FilmViewModel
import com.polytech.applicationcinma.viewmodel.PersonViewModel
import com.polytech.applicationcinma.viewmodelfactory.FilmViewModelFactory
import com.polytech.applicationcinma.viewmodelfactory.PersonViewModelFactory

class PersonFragment : Fragment() {
    private lateinit var binding: FragmentPersonBinding
    private lateinit var viewModel: PersonViewModel
    private lateinit var viewModelFactory: PersonViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val args = PersonFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        val pid = args.pid
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person, container, false)
        binding.lifecycleOwner = this

        viewModelFactory = PersonViewModelFactory(application,token,pid)
        viewModel = ViewModelProvider(this,viewModelFactory)[PersonViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {

        }

        viewModel.apiOK.observe(viewLifecycleOwner, { res ->

        })



        return binding.root
    }
}