package com.example.sipapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.sipapp.databinding.FragmentUserBinding
import com.example.sipapp.ui.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UserFragment: Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel:UserViewModel by viewModels()
    private val args: UserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser(args.userId)
        Timber.d("${args.userId}")

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state is ScreenState.Success) {
                    val userInfo = state.data
                    if (userInfo != null) {
                        binding.userNameTextView.text = userInfo.name
                        binding.userNicknameTextView.text = userInfo.username
                        binding.userEmailTextView.text = userInfo.email
                        binding.userPhoneTextView.text = userInfo.phone
                        binding.userWebsiteTextView.text = userInfo.website
                        binding.userAddressTextView.text = "" +
                            "${userInfo.address?.street}, " +
                            "${userInfo.address?.suite}, " +
                            "${userInfo.address?.city}, " +
                            "${userInfo.address?.zipcode}"
                        binding.userCompanyTextView.text = "" +
                            "${userInfo.company?.name}, " +
                            "${userInfo.company?.catchPhrase}"
                    }
                }
            }
        }
    }
}