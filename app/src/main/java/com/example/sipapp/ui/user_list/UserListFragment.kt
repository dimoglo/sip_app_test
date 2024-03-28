package com.example.sipapp.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sipapp.databinding.FragmentUserListBinding
import com.example.sipapp.ui.main.MainFragmentDirections
import com.example.sipapp.ui.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class UserListFragment: Fragment() {

    companion object {
        const val TAB_NAME = "Пользователи"
        const val TAB_POSITION = 0
    }

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usersAdapter = UserListAdapter(listOf()){
            findNavController().navigate(MainFragmentDirections.toUserFragment(it))
        }
        binding.recyclerView.apply {
            adapter = usersAdapter
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launch {
            viewModel.state.collect {state ->
                if(state is ScreenState.Success) {
                    usersAdapter.users = state.data!!
                    usersAdapter.notifyDataSetChanged()
                    Timber.d("test5 ${state.data}")
                }
            }
        }
    }
}