package com.polytech.applicationcinma.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.polytech.applicationcinma.R
import com.polytech.applicationcinma.databinding.FragmentRegisterBinding
import com.polytech.applicationcinma.viewmodel.RegisterViewModel
import com.polytech.applicationcinma.viewmodelfactory.RegisterViewModelFactory
import java.util.*

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var viewModelFactory: RegisterViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        viewModelFactory = RegisterViewModelFactory(application)
        viewModel = ViewModelProvider(this,viewModelFactory)[RegisterViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {
            tvTitle.text = getString(R.string.register)
            btValidate.text = getString(R.string.register)
            evLogin.hint = getString(R.string.evLogin)
            evPwd.hint = getString(R.string.evPwd)
            evPwdCheck.hint = getString(R.string.evPwdCheck)
        }


        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner, { token ->
            token?.let {
                this.findNavController().navigate(
                    RegisterFragmentDirections
                        .actionRegisterFragmentToHomeFragment(token))
                Log.i("Navigating to HOME", "Successful registering - uid : $token")
                viewModel.doneNavigating()
            }
        })

        viewModel.errorRegistering.observe(viewLifecycleOwner, { errorCode ->
            errorCode?.let {
                var message = ""
                when (errorCode) {
                    0L -> message = "API Error on registering"
                    7L -> message = "You must enter a password"
                    8L -> message = "You must enter a login"
                    9L -> message = "You must the confirmation of the password"
                }
                Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
            }
        })


        return binding.root
    }
}