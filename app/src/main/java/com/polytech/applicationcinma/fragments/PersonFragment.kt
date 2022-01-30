package com.polytech.applicationcinma.fragments

import android.os.Bundle
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
            tvLabelactor.text = getString(R.string.tvLabelactor)
            tvLabelfilm.text = getString(R.string.tvLabelfilm)
        }

        viewModel.apiOK.observe(viewLifecycleOwner, { res ->
            //No image
        })

        binding.tvActor.setOnClickListener {
             this.findNavController().navigate(
                PersonFragmentDirections.actionPersonFragmentToActorFragment(token,viewModel.aid)
            )
            //Toast.makeText(this.context, "Going to actor ${binding.tvActor.text}", Toast.LENGTH_SHORT).show()
        }

        binding.tvFilm.setOnClickListener {
            this.findNavController().navigate(
                PersonFragmentDirections.actionPersonFragmentToFilmFragment(token,viewModel.fid)
            )
            //Toast.makeText(this.context, "Going to film ${binding.tvFilm.text}", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}