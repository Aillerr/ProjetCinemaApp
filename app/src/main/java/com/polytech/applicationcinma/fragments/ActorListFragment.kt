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
import com.polytech.applicationcinma.adapters.ActorListener
import com.polytech.applicationcinma.adapters.MyListAdapterActor
import com.polytech.applicationcinma.databinding.FragmentActorListBinding
import com.polytech.applicationcinma.viewmodel.ActorListViewModel
import com.polytech.applicationcinma.viewmodelfactory.ActorListViewModelFactory

class ActorListFragment : Fragment() {
    private lateinit var binding: FragmentActorListBinding
    private lateinit var viewModel: ActorListViewModel
    private lateinit var viewModelFactory: ActorListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_actor_list, container, false)
        val args = ActorListFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        viewModelFactory = ActorListViewModelFactory(application,token)
        viewModel = ViewModelProvider(this,viewModelFactory)[ActorListViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MyListAdapterActor(ActorListener { fid ->
            Log.i("INFO -- Preset selected", "Actor choosen : $fid")
            this.findNavController().navigate(
                ActorListFragmentDirections
                    .actionActorListFragmentToActorFragment(token,fid)
            )
        })


        binding.liActorlist.adapter = adapter
        viewModel.actors.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.apply {
            tvTitle.text = getString(R.string.ActorList)
        }

        return binding.root
    }
}