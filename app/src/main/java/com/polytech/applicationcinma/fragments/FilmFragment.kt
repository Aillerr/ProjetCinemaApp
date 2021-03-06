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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.Headers
import com.bumptech.glide.request.RequestOptions
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.adapters.*
import com.polytech.applicationcinma.databinding.FragmentFilmBinding
import com.polytech.applicationcinma.viewmodel.FilmViewModel
import com.polytech.applicationcinma.viewmodelfactory.FilmViewModelFactory

class FilmFragment : Fragment() {

    private lateinit var binding: FragmentFilmBinding
    private lateinit var viewModel: FilmViewModel
    private lateinit var viewModelFactory: FilmViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        val args = FilmFragmentArgs.fromBundle(requireArguments())
        val token = args.token
        val fid = args.fid
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_film, container, false)
        binding.lifecycleOwner = this

        viewModelFactory = FilmViewModelFactory(application,token,fid)
        viewModel = ViewModelProvider(this,viewModelFactory)[FilmViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {
            tvLabelReal.text = getString(R.string.tvLabelReal)
            tvLabelbudget.text = getString(R.string.tvLabelbudget)
            tvLabelcat.text = getString(R.string.tvLabelcat)
            tvLabeldateSortie.text = getString(R.string.tvLabeldateSortie)
            tvLabelduree.text = getString(R.string.tvLabelduree)
            tvLabelrecette.text = getString(R.string.tvLabelrecette)
            tvListlabel.text = getString(R.string.persolabel)
        }


        viewModel.apiOK.observe(viewLifecycleOwner, { res ->
            res?.let {
                val glideUrl = GlideUrl(
                    viewModel.film.value?.Image,
                    Headers { mutableMapOf("Authorization" to "Bearer $token") }
                )
                Glide.with(this)
                    .load(glideUrl)
                    .into(binding.ivImgFilm)
            }
        })

        val adapter = MyListAdapterPerson(PersonListener { aid ->
            Log.i("INFO -- Preset selected", "Perso choosen : $aid")
            this.findNavController().navigate(
                FilmFragmentDirections
                    .actionFilmFragmentToPersonFragment(token,aid)
            )
            //Toast.makeText(this.context, "Going to perso $aid", Toast.LENGTH_SHORT).show()
        })


        binding.liPersonslist.adapter = adapter
        viewModel.personnages.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.tvReal.setOnClickListener {
             this.findNavController().navigate(
                FilmFragmentDirections
                    .actionFilmFragmentToRealFragment(token,viewModel.rid)
            )
            //Toast.makeText(this.context, "Going to real ${binding.tvReal.text}", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}