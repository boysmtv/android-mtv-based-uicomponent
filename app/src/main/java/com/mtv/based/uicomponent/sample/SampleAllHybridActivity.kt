package com.mtv.based.uicomponent.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.component.badge.compose.AppBadge
import com.mtv.based.uicomponent.component.card.compose.AppCard
import com.mtv.based.uicomponent.component.checkbox.compose.AppCheckbox
import com.mtv.based.uicomponent.component.dialog.DialogState
import com.mtv.based.uicomponent.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.component.input.InputState
import com.mtv.based.uicomponent.component.input.compose.AppTextField
import com.mtv.based.uicomponent.databinding.ActivityAllSampleBinding
import com.mtv.based.uicomponent.sheet.BottomSheetState
import com.mtv.based.uicomponent.sheet.compose.AppBottomSheet
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class SampleAllHybridActivity : ComponentActivity() {

    private lateinit var binding: ActivityAllSampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllSampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.xmlDialog.hideDialog()
        binding.xmlBottomSheet.hideBottomSheet()

        binding.xmlInput.setOnClickListener {
            binding.xmlDialog.showDialog(
                title = "XML Dialog",
                message = "Email field clicked!"
            )
        }

        binding.xmlCard.setContent(title = "XML Card") {
            Text("Ini card XML content")
        }

        binding.xmlBadge.setText("XML Badge")
        binding.xmlCheckbox.setChecked(false) { checked ->
            println("Checkbox XML: $checked")
        }

        val composeView = ComposeView(this).apply {
            setContent {
                AppTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        var email by remember { mutableStateOf("") }
                        var emailError by remember { mutableStateOf(false) }
                        var dialogState by remember { mutableStateOf<DialogState>(DialogState.Hidden) }
                        var bottomSheetState by remember {
                            mutableStateOf<BottomSheetState>(
                                BottomSheetState.Hidden
                            )
                        }
                        var cardChecked by remember { mutableStateOf(false) }

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

                        Button(onClick = {
                            if (!email.isBlank()) {
                                dialogState = DialogState.Visible(
                                    title = "Compose Dialog",
                                    message = "Email: $email",
                                    positiveText = "OK"
                                )
                            }
                        }) { Text("Submit (Compose)") }

                        AppDialog(
                            state = dialogState,
                            onDismiss = { dialogState = DialogState.Hidden },
                            onConfirm = { dialogState = DialogState.Hidden }
                        )

                        Button(onClick = {
                            bottomSheetState = BottomSheetState.Visible(
                                title = "Compose BottomSheet",
                                message = "Ini content dari Compose"
                            )
                        }) { Text("Show Compose BottomSheet") }

                        AppBottomSheet(
                            state = bottomSheetState,
                            onDismiss = { bottomSheetState = BottomSheetState.Hidden }
                        )

                        AppCard(title = "Compose Card") {
                            AppCheckbox(
                                checked = cardChecked,
                                onCheckedChange = { cardChecked = it },
                                label = "Check me"
                            )
                            AppBadge(text = "New")
                        }
                    }
                }
            }
        }

        binding.rootHybrid.addView(composeView, 0)
    }
}
