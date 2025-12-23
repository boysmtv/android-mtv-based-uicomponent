package com.mtv.based.uicomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.component.dialog.DialogState
import com.mtv.based.uicomponent.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.component.input.InputState
import com.mtv.based.uicomponent.component.input.compose.AppTextField
import com.mtv.based.uicomponent.databinding.ActivityHybridBinding
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class HybridComposeXmlActivity : ComponentActivity() {

    private lateinit var binding: ActivityHybridBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHybridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Compose container in XML
        val composeView = ComposeView(this).apply {
            setContent {
                AppTheme {
                    ComposeInputAndDialog()
                }
            }
        }

        binding.hybridRoot.addView(composeView, 0) // Tambahkan Compose di atas XML

        // XML input + dialog handling
        binding.xmlDialog.hideDialog()

        binding.xmlInput.setOnClickListener {
            // bisa trigger dialog XML
            binding.xmlDialog.showDialog(
                title = "Warning XML",
                message = "Dialog muncul dari InputTextView XML",
                positive = "OK",
                negative = "Cancel"
            )
        }
    }
}

@Composable
fun ComposeInputAndDialog() {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf<DialogState>(DialogState.Hidden) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Compose Input
        AppTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = it.isBlank()
            },
            label = "Email (Compose)",
            placeholder = "Masukkan email",
            state = if (emailError) InputState.Error("Email tidak boleh kosong") else InputState.Enabled
        )

        // Compose Submit Button
        Button(onClick = {
            if (!email.isBlank()) {
                dialogState = DialogState.Visible(
                    title = "Success",
                    message = "Email Compose: $email",
                    positiveText = "OK"
                )
            }
        }) {
            Text("Submit (Compose)")
        }

        // Compose Dialog
        AppDialog(
            state = dialogState,
            onDismiss = { dialogState = DialogState.Hidden },
            onConfirm = { dialogState = DialogState.Hidden }
        )
    }
}
