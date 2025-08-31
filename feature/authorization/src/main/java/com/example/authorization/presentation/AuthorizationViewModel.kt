package com.example.authorization.presentation

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
        formSubmitted = true
        if (validateForm()) {
            viewModelScope.launch {
                _isLoading.value = true
                try {
                    authRepository.signIn()
                } catch (e: Exception) {

                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

    fun onEmailChange(value: String) {
        _email.value = value
        _emailError.value = null
    }

    fun onPasswordChange(value: String) {
        _password.value = value
        _passwordError.value = null
    }

    fun onEmailFocusChanged(focused: Boolean) {
        if (!focused && _email.value.isNotBlank() && !emailTouched) {
            emailTouched = true
            validateEmail()
            return
        }
        validateEmail()
    }

    fun onPasswordFocusChanged(focused: Boolean) {
        if (!focused && _password.value.isNotBlank() && !passwordTouched) {
            passwordTouched = true
            validatePassword()
            return
        }
        validatePassword()
    }

    private fun validateEmail() {
        if (!emailTouched && !formSubmitted) {
            _emailError.value = null
            return
        }
        _emailError.value = when {
            _email.value.isBlank() -> "Заполните Email"
            !Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() -> "Некорректный email"
            else -> null
        }
    }

    private fun validatePassword() {
        if (!passwordTouched && !formSubmitted) {
            _passwordError.value = null
            return
        }
        _passwordError.value = if (_password.value.isBlank()) "Введите пароль" else null
    }

    fun getEnabledValidate(): Boolean{
        return _emailError.value == null && _passwordError.value == null && !_email.value.isEmpty() && !_password.value.isEmpty()

    }

    private fun validateForm(): Boolean {
        validateEmail()
        validatePassword()
        return _emailError.value == null && _passwordError.value == null && !_email.value.isEmpty() && !_password.value.isEmpty()
    }
}