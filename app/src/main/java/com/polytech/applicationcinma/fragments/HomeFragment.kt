package com.polytech.applicationcinma.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.databinding.FragmentHomeBinding
import com.polytech.applicationcinma.viewmodel.HomeViewModel
import com.polytech.applicationcinma.viewmodelfactory.HomeViewModelFactory


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val args = HomeFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        viewModelFactory = HomeViewModelFactory(application,token)
        viewModel = ViewModelProvider(this,viewModelFactory)[HomeViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {
            btFilms.text = getString(R.string.btFilms)
            btPerson.text = getString(R.string.btPerson)
            btReal.text = getString(R.string.btReal)
            btActor.text = getString(R.string.btActor)
        }

        binding.btFilms.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFilmListFragment(token)
            )
        }

        binding.btReal.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToRealListFragment(token)
            )
        }

        binding.btActor.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToActorListFragment(token)
            )
        }

        binding.btPerson.setOnClickListener {
            this.findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToPersonListFragment(token)
            )
        }


        return binding.root
    }
}