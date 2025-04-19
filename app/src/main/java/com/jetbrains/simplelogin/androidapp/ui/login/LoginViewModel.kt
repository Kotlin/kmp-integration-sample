package com.jetbrains.simplelogin.androidapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.simplelogin.shared.data.LoginRepository
import com.jetbrains.simplelogin.shared.data.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.jetbrains.simplelogin.androidapp.R
import com.jetbrains.simplelogin.shared.data.LoginDataValidator

class LoginViewModel(private val loginRepository: LoginRepository, private val dataValidator: LoginDataValidator) : ViewModel() {

    private val _loginFormState = MutableStateFlow(LoginFormState(null, null))
    val loginFormState: StateFlow<LoginFormState> = _loginFormState.asStateFlow()

    private val _loginResult = MutableStateFlow<LoginResult?>(null)
    val loginResult: StateFlow<LoginResult?> = _loginResult.asStateFlow()

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch {
            val result = loginRepository.login(username, password)

            if (result is Result.Success) {
                _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        _loginFormState.value = LoginFormState(
            usernameError = (dataValidator.checkUsername(username) as? LoginDataValidator.Result.Error)?.message,
            passwordError = (dataValidator.checkPassword(password) as? LoginDataValidator.Result.Error)?.message
        )
    }
}
