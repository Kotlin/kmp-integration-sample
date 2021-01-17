package com.jetbrains.simplelogin.shared.data

import com.jetbrains.simplelogin.shared.data.model.LoggedInUser
import com.jetbrains.simplelogin.shared.randomUUID

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(randomUUID(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(RuntimeException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}