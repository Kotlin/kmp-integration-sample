package com.jetbrains.simplelogin.shared.data

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
        return when  {
            password.length < 5 -> Result.Error("Password must be >5 characters")
            password.toLowerCase() == "password" -> Result.Error("Password shouldn't be \"password\"")
            else -> Result.Success
        }
    }

    private fun isEmailValid(email: String) = emailRegex.matches(email)

    companion object {
        private val emailRegex =
            ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+").toRegex()
    }
}