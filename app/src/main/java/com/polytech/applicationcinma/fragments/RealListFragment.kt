package com.polytech.applicationcinma.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.adapters.RealListener
import com.polytech.applicationcinma.adapters.MyListAdapterActor
import com.polytech.applicationcinma.adapters.MyListAdapterReal
import com.polytech.applicationcinma.databinding.FragmentRealListBinding
import com.polytech.applicationcinma.viewmodel.RealListViewModel
import com.polytech.applicationcinma.viewmodelfactory.RealListViewModelFactory

class RealListFragment : Fragment() {
    private lateinit var binding: FragmentRealListBinding
    private lateinit var viewModel: RealListViewModel
    private lateinit var viewModelFactory: RealListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_real_list, container, false)
        val args = RealListFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        viewModelFactory = RealListViewModelFactory(application,token)
        viewModel = ViewModelProvider(this,viewModelFactory)[RealListViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MyListAdapterReal(RealListener { rid ->
            Log.i("INFO -- Preset selected", "Actor choosen : $rid")
            this.findNavController().navigate(
                RealListFragmentDirections
                    .actionRealListFragmentToRealFragment(token,rid)
            )
        })


        binding.liReallist.adapter = adapter
        viewModel.reals.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.apply {
            tvTitle.text = getString(R.string.RealList)
        }

        return binding.root
    }
}