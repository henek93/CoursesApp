package com.example.authorization.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthForm(
    modifier: Modifier = Modifier,
    emailValue: String,
    passwordValue: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    emailError: String?,
    passwordError: String?,
    onEmailFocusChange: (Boolean) -> Unit,
    onPasswordFocusChange: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        AuthTextField(
            modifier = Modifier.padding(vertical = 8.dp),
            value = emailValue,
            onValueChange = onEmailChange,
            label = "Email",
            placeholder = "example@gmail.com",
            errorText = emailError,
            onFocusChanged = onEmailFocusChange
        )

        AuthTextField(
            modifier = Modifier.padding(vertical = 8.dp),
            value = passwordValue,
            onValueChange = onPasswordChange,
            label = "Пароль",
            placeholder = "Введите пароль",
            isPassword = true,
            errorText = passwordError,
            onFocusChanged = onPasswordFocusChange
        )
    }
}