package com.mtv.based.uicomponent.core.component.input.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.core.component.input.InputState
import com.mtv.based.uicomponent.core.component.input.compose.AppTextField
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme
import androidx.activity.compose.setContent

class SampleInputActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.background
                ) {
                    InputScreen()
                }
            }
        }
    }
}

@Composable
fun InputScreen() {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        AppTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isBlank()
            },
            label = "Email",
            state = if (emailError) InputState.Error("Email tidak boleh kosong") else InputState.Enabled
        )

        Button(
            onClick = { emailError = email.isBlank() },
            enabled = !email.isBlank()
        ) {
            Text("Submit")
        }
    }
}
