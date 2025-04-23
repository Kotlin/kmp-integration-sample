package com.jetbrains.simplelogin.androidapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jetbrains.simplelogin.androidapp.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: (LoginResult) -> Unit
) {
    val loginFormState by viewModel.loginFormState.collectAsStateWithLifecycle()
    val loginResult by viewModel.loginResult.collectAsStateWithLifecycle()
    
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordFocusRequester = remember { FocusRequester() }
    
    // Handle login result
    LaunchedEffect(loginResult) {
        loginResult?.let { result ->
            if (result.success != null) {
                // Show welcome message
                onLoginSuccess(result)
            }
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = { 
                username = it
                viewModel.loginDataChanged(username, password)
            },
            label = { Text(stringResource(R.string.prompt_email)) },
            isError = loginFormState.usernameError != null,
            supportingText = {
                loginFormState.usernameError?.let {
                    Text(it)
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        
        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { 
                password = it
                viewModel.loginDataChanged(username, password)
            },
            label = { Text(stringResource(R.string.prompt_password)) },
            isError = loginFormState.passwordError != null,
            supportingText = {
                loginFormState.passwordError?.let {
                    Text(it)
                }
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { 
                    if (loginFormState.isDataValid) {
                        viewModel.login(username, password)
                    }
                }
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .focusRequester(passwordFocusRequester)
        )
        
        // Login button
        Button(
            onClick = { viewModel.login(username, password) },
            enabled = loginFormState.isDataValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(stringResource(R.string.action_sign_in))
        }
        
        // Loading indicator
        if (loginResult != null && loginResult?.success == null && loginResult?.error == null) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp)
            )
        }
        
        // Error message
        loginResult?.error?.let { errorId ->
            Text(
                text = stringResource(errorId),
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}