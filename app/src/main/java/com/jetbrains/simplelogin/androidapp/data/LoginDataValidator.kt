package com.jetbrains.simplelogin.androidapp.data

import android.util.Patterns

class LoginDataValidator {

    sealed class Result {
        object Success : Result()
        class Error(val message: String): Result()
    }

    fun checkUsername(username: String): Result {
        return if (username.contains('@')) {
            if (isEmailValid(username)) Result.Success else Result.Error("Email is not valid")
        } else {
            if (username.isNotBlank()) Result.Success else Result.Error("Username is blank")
        }
    }

    fun checkPassword(password: String): Result {
        return if (password.length > 5) Result.Success else Result.Error("Password must be >5 characters")
    }

    private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}