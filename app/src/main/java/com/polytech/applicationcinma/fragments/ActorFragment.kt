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
import com.polytech.applicationcinma.adapters.ActorListener
import com.polytech.applicationcinma.adapters.MyListAdapterActor
import com.polytech.applicationcinma.adapters.MyListAdapterPerson
import com.polytech.applicationcinma.adapters.PersonListener
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
            tvLabeldeces.text = getString(R.string.tvLabeldeces)
            tvLabelnaiss.text = getString(R.string.tvLabelnaiss)
            tvListlabel.text = getString(R.string.tvListLabel)
        }

        viewModel.apiOK.observe(viewLifecycleOwner, { res ->
            res?.let {
                val glideUrl = GlideUrl(
                    viewModel.actor.value?.image,
                    Headers { mutableMapOf("Authorization" to "Bearer $token") }
                )
                Glide.with(this)
                    .load(glideUrl)
                    .into(binding.ivImgAct)
            }
        })

        val adapter = MyListAdapterPerson(PersonListener { pid ->
            Log.i("INFO -- Preset selected", "Perso choosen : $pid")
            this.findNavController().navigate(
                ActorFragmentDirections
                    .actionActorFragmentToPersonFragment(token,pid)
            )
            //Toast.makeText(this.context, "Going to perso $pid", Toast.LENGTH_SHORT).show()
        })

        binding.liPersonslist.adapter = adapter
        viewModel.personnages.observe(viewLifecycleOwner,  {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}