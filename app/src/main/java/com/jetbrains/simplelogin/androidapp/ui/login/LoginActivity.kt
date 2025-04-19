package com.jetbrains.simplelogin.androidapp.ui.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.simplelogin.androidapp.R
import com.jetbrains.simplelogin.shared.Greeting

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Login Activity", "Hello from shared module: " + (Greeting().greet()))

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        setContent {
            MaterialTheme {
                Surface() {
                    LoginScreen(
                        viewModel = loginViewModel,
                        onLoginSuccess = {
                            // Show welcome message
                            val successResult = loginViewModel.loginResult.value?.success
                            successResult?.let {
                                val welcome = getString(R.string.welcome)
                                Toast.makeText(
                                    applicationContext,
                                    "$welcome ${it.displayName}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            // Complete the login process
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    )
                }
            }
        }
    }
}
