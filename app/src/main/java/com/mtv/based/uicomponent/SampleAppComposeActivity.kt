package com.mtv.based.uicomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.component.dialog.DialogState
import com.mtv.based.uicomponent.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.component.input.InputState
import com.mtv.based.uicomponent.component.input.compose.AppTextField
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class SampleAppComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.background
                ) {
                    SampleScreen()
                }
            }
        }
    }
}

@Composable
fun SampleScreen() {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf<DialogState>(DialogState.Hidden) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Input Field
        AppTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isBlank()
            },
            label = "Email",
            placeholder = "Masukkan email",
            state = if (emailError) InputState.Error("Email tidak boleh kosong") else InputState.Enabled
        )

        // Submit Button
        Button(
            onClick = {
                if (email.isBlank()) {
                    emailError = true
                } else {
                    dialogState = DialogState.Visible(
                        title = "Success",
                        message = "Email terisi: $email",
                        positiveText = "OK"
                    )
                }
            }
        ) {
            Text("Submit")
        }

        // Dialog
        AppDialog(
            state = dialogState,
            onDismiss = { dialogState = DialogState.Hidden },
            onConfirm = { dialogState = DialogState.Hidden }
        )
    }
}
