package com.example.sipapp.ui.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sipapp.domain.UserModel
import com.example.sipapp.domain.UsersRepository
import com.example.sipapp.ui.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UsersRepository
): ViewModel() {
    private val _state = MutableStateFlow<ScreenState<List<UserModel>>>(ScreenState.Idle)
    val state: StateFlow<ScreenState<List<UserModel>>> = _state

    init {
        getUserList()
    }
    fun getUserList(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(ScreenState.Loading)
            try {
                val users = repository.getUsers()
                _state.emit(ScreenState.Success(users))
                Timber.d("test1 $users")
            } catch (e: Exception) {
                _state.emit(ScreenState.Error(e.message ?: "Unknown Error"))
            }
        }
    }
}