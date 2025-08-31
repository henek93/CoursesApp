package com.example.authorization.presentation

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authorization.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AuthorizationViewModel"

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var emailTouched = false
    private var passwordTouched = false
    private var formSubmitted = false

    fun signIn() {
        Log.d(TAG, "signIn() called")
        formSubmitted = true
        if (validateForm()) {
            Log.d(TAG, "Форма валидна, начинаем вход")
            viewModelScope.launch {
                try {
                    _isLoading.value = true
                    Log.d(TAG, "isLoading = true, вызов authRepository.signIn()")
                    authRepository.signIn()
                    Log.d(TAG, "Успешный вход")
                } catch (e: Exception) {
                    Log.e(TAG, "Sign in error", e)
                } finally {
                    _isLoading.value = false
                    Log.d(TAG, "isLoading = false")
                }
            }
        } else {
            Log.d(TAG, "Форма невалидна")
        }
    }

    fun onEmailChange(value: String) {
        Log.d(TAG, "onEmailChange: $value")


        _email.value = value

        if (!emailTouched && value.isNotBlank()) {
            emailTouched = true
            Log.d(TAG, "Email помечен как touched")
        }
        if (emailTouched || formSubmitted) validateEmail()
    }

    fun onPasswordChange(value: String) {
        Log.d(TAG, "onPasswordChange: $value")
        _password.value = value

        if (!passwordTouched && value.isNotBlank()) {
            passwordTouched = true
            Log.d(TAG, "Password помечен как touched")
        }

        if (passwordTouched || formSubmitted) validatePassword()
    }

    fun onEmailFocusChanged(focused: Boolean) {
        Log.d(TAG, "onEmailFocusChanged: focused=$focused")
        if (!focused && _email.value.isNotBlank() && !emailTouched) {
            emailTouched = true
            Log.d(TAG, "Email потерял фокус → validateEmail()")
            validateEmail()
        }
    }

    fun onPasswordFocusChanged(focused: Boolean) {
        Log.d(TAG, "onPasswordFocusChanged: focused=$focused")
        if (!focused && _password.value.isNotBlank() && !passwordTouched) {
            passwordTouched = true
            Log.d(TAG, "Password потерял фокус → validatePassword()")
            validatePassword()
        }
    }

    private fun validateEmail() {
        Log.d(TAG, "validateEmail() value='${_email.value}'")

        if (!emailTouched && !formSubmitted) {
            Log.d(TAG, "Email не тронут и форма не отправлялась — пропускаем валидацию")
            _emailError.value = null
            return
        }

        val emailValid = Patterns.EMAIL_ADDRESS.matcher(_email.value).matches()
        _emailError.value = when {
            _email.value.isBlank() -> {
                Log.d(TAG, "Ошибка email: пустое поле")
                "Заполните Email"
            }

            !emailValid -> {
                Log.d(TAG, "Ошибка email: некорректный формат")
                "Некорректный email"
            }

            else -> {
                Log.d(TAG, "Email валиден")
                null
            }
        }
    }

    private fun validatePassword() {
        Log.d(TAG, "validatePassword() value='${_password.value}'")

        if (!passwordTouched && !formSubmitted) {
            Log.d(TAG, "Password не тронут и форма не отправлялась — пропускаем валидацию")
            _passwordError.value = null
            return
        }

        _passwordError.value = if (_password.value.isBlank()) {
            Log.d(TAG, "Ошибка password: пустое поле")
            "Введите пароль"
        } else {
            Log.d(TAG, "Пароль валиден")
            null
        }
    }

    private fun validateForm(): Boolean {
        Log.d(TAG, "validateForm()")
        validateEmail()
        validatePassword()
        val isValid = _emailError.value == null && _passwordError.value == null
        Log.d(TAG, "Результат validateForm(): $isValid")
        return isValid
    }

    fun clearErrors() {
        _emailError.value = null
        _passwordError.value = null
    }
}