package com.jetbrains.simplelogin.androidapp.ui.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jetbrains.simplelogin.androidapp.R
import com.jetbrains.simplelogin.shared.Greeting
import com.jetbrains.simplelogin.shared.data.LoginDataSource
import com.jetbrains.simplelogin.shared.data.LoginDataValidator
import com.jetbrains.simplelogin.shared.data.LoginRepository

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Login Activity", "Hello from shared module: " + (Greeting().greet()))

        setContent {
            MaterialTheme {
                Surface() {
                    LoginScreen(
                        viewModel = viewModel {
                            LoginViewModel(
                                loginRepository = LoginRepository(
                                    dataSource = LoginDataSource()
                                ),
                                dataValidator = LoginDataValidator()
                            )
                        },
                        onLoginSuccess = {
                            // Show welcome message
                            val successResult = it.success
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
