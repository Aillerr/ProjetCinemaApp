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
import com.polytech.applicationcinma.databinding.FragmentLoginBinding
import com.polytech.applicationcinma.viewmodel.LoginViewModel
import com.polytech.applicationcinma.viewmodelfactory.LoginViewModelFactory


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.lifecycleOwner = this

        viewModelFactory = LoginViewModelFactory(application)
        viewModel = ViewModelProvider(this,viewModelFactory)[LoginViewModel::class.java]

        binding.viewModel = viewModel

        binding.apply {
            evLogin.hint = getString(R.string.evLogin)
            evPwd.hint = getString(R.string.evPwd)
            btValidate.text = getString(R.string.login)
            tvCreateacc.text = getString(R.string.register)
        }

        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner, { token ->
            token?.let {
                this.findNavController().navigate(
                    LoginFragmentDirections
                        .actionLoginFragmentToHomeFragment(token))
                Log.i("Navigating to HOME", "Successful login - uid : $token")
                viewModel.doneNavigating()
            }
        })

        binding.tvCreateacc.setOnClickListener {
            this.findNavController().navigate(
                LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment()
            )
        }

        viewModel.errorLogin.observe(viewLifecycleOwner, { errorCode ->
            errorCode?.let {
                var message = ""
                if (errorCode) {
                    message = "User not recognized"
                }
                Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }


}