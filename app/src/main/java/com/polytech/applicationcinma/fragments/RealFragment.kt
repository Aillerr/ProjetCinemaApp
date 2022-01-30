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
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.adapters.*
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
            tvListlabel.text = getString(R.string.labelfilmreal)
        }

        viewModel.apiOK.observe(viewLifecycleOwner, { res ->
            res?.let {
                val glideUrl = GlideUrl(
                    viewModel.real.value?.Image,
                    Headers { mutableMapOf("Authorization" to "Bearer $token") }
                )
                Glide.with(this)
                    .load(glideUrl)
                    .into(binding.ivImgRea)
            }
        })

        val adapter = MyListAdapterPersoFilms(FilmsPersoListener { fid ->
            Log.i("INFO -- Preset selected", "Perso choosen : $fid")
            this.findNavController().navigate(
                RealFragmentDirections
                    .actionRealFragmentToFilmFragment(token,fid)
            )
            //Toast.makeText(this.context, "Going to film $fid", Toast.LENGTH_SHORT).show()
        })


        binding.liFilmslist.adapter = adapter
        viewModel.films.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })




        return binding.root
    }
}