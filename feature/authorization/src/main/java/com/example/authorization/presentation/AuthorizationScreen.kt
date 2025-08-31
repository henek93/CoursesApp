import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.authorization.presentation.AuthorizationViewModel
import com.example.authorization.presentation.components.AuthForm
import com.example.authorization.presentation.components.ButtonsSocialNetwork
import com.example.authorization.presentation.components.HintText
import com.example.ui.components.AppButton

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationViewModel = hiltViewModel<AuthorizationViewModel>()
) {

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val emailError = viewModel.emailError.collectAsState()
    val passwordError = viewModel.passwordError.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                "Вход",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            AuthForm(
                emailValue = email.value,
                onEmailChange = { viewModel.onEmailChange(it.trim()) },
                onEmailFocusChange = { viewModel.onEmailFocusChanged(it) },
                emailError = emailError.value,
                passwordValue = password.value,
                onPasswordChange = { viewModel.onPasswordChange(it.trim()) },
                onPasswordFocusChange = { viewModel.onPasswordFocusChanged(it) },
                passwordError = passwordError.value,
            )

            AppButton(
                onClick = { viewModel.signIn() },
                enabled = viewModel.validateForm(),
                color = MaterialTheme.colorScheme.primary
            ) {
                if (isLoading.value) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text("Вход", color = Color.White, style = MaterialTheme.typography.bodyMedium)
                }
            }


            HintText(modifier = Modifier.padding(vertical = 16.dp))


            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.secondaryContainer
            )

            ButtonsSocialNetwork(
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

