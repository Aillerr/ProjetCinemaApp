package com.polytech.applicationcinma.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.adapters.FilmsListener
import com.polytech.applicationcinma.adapters.FilmsPersoListener
import com.polytech.applicationcinma.adapters.MyListAdapterFilms
import com.polytech.applicationcinma.adapters.MyListAdapterPersoFilms
import com.polytech.applicationcinma.databinding.FragmentFilmListBinding
import com.polytech.applicationcinma.viewmodel.FilmListViewModel
import com.polytech.applicationcinma.viewmodelfactory.FilmListViewModelFactory

class FilmListFragment : Fragment() {
    private lateinit var binding: FragmentFilmListBinding
    private lateinit var viewModel: FilmListViewModel
    private lateinit var viewModelFactory: FilmListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_film_list, container, false)
        val args = FilmListFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        viewModelFactory = FilmListViewModelFactory(application,token)
        viewModel = ViewModelProvider(this,viewModelFactory)[FilmListViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MyListAdapterPersoFilms(FilmsPersoListener { fid ->
            Log.i("INFO -- Preset selected", "Film choosen : $fid")
            this.findNavController().navigate(
                FilmListFragmentDirections
                    .actionFilmListFragmentToFilmFragment(token,fid)
            )
        })


        binding.liFilmslist.adapter = adapter
        viewModel.films.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.apply {
            tvTitle.text = getString(R.string.filmList)
        }

        return binding.root
    }
}