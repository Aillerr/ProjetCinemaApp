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
import com.polytech.applicationcinma.databinding.FragmentActorBinding
import com.polytech.applicationcinma.viewmodel.ActorViewModel
import com.polytech.applicationcinma.viewmodel.FilmViewModel
import com.polytech.applicationcinma.viewmodelfactory.ActorViewModelFactory
import com.polytech.applicationcinma.viewmodelfactory.FilmViewModelFactory

class ActorFragment : Fragment() {

    private lateinit var binding: FragmentActorBinding
    private lateinit var viewModel: ActorViewModel
    private lateinit var viewModelFactory: ActorViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val args = ActorFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        val aid = args.aid
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_actor, container, false)
        binding.lifecycleOwner = this

        viewModelFactory = ActorViewModelFactory(application,token,aid)
        viewModel = ViewModelProvider(this,viewModelFactory)[ActorViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {

        }

        viewModel.apiOK.observe(viewLifecycleOwner, { res ->

        })



        return binding.root
    }
}