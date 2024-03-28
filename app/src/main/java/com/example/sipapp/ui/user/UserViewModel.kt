package com.example.sipapp.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sipapp.data.local.UserEntity
import com.example.sipapp.domain.UserModel
import com.example.sipapp.domain.UsersRepository
import com.example.sipapp.ui.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UsersRepository
): ViewModel() {
    private val _state = MutableStateFlow<ScreenState<UserModel>>(ScreenState.Idle)
    val state: StateFlow<ScreenState<UserModel>> = _state
    val user = MutableLiveData<UserModel>(null)

    fun getUser(userId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(ScreenState.Loading)
            try {
                val user = repository.getUser(userId)
                if(user != null)
                    _state.emit(ScreenState.Success(user))
                else
                    _state.emit(ScreenState.Error("User not found"))
            } catch (e: Exception) {
                _state.emit(ScreenState.Error(e.message ?: "Unknown Error"))
            }
        }
    }
}