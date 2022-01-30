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
import com.polytech.applicationcinma.adapters.MyListAdapterPerson
import com.polytech.applicationcinma.adapters.MyListAdapterReal
import com.polytech.applicationcinma.adapters.PersonListener
import com.polytech.applicationcinma.databinding.FragmentPersonListBinding
import com.polytech.applicationcinma.viewmodel.PersonListViewModel
import com.polytech.applicationcinma.viewmodelfactory.PersonListViewModelFactory

class PersonListFragment : Fragment() {
    private lateinit var binding: FragmentPersonListBinding
    private lateinit var viewModel: PersonListViewModel
    private lateinit var viewModelFactory: PersonListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_list, container, false)
        val args = PersonListFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        viewModelFactory = PersonListViewModelFactory(application,token)
        viewModel = ViewModelProvider(this,viewModelFactory)[PersonListViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MyListAdapterPerson(PersonListener { pid ->
            Log.i("INFO -- Preset selected", "Person choosen : $pid")
            this.findNavController().navigate(
                PersonListFragmentDirections
                    .actionPersonListFragmentToPersonFragment(token,pid)
            )
        })


        binding.liPersonlist.adapter = adapter
        viewModel.persons.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.apply {
            tvTitle.text = getString(R.string.PersonList)
        }

        return binding.root
    }
}