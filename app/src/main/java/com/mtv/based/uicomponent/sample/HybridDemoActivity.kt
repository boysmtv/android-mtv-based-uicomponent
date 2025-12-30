package com.mtv.based.uicomponent.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.core.component.badge.compose.AppBadge
import com.mtv.based.uicomponent.core.component.card.compose.AppCard
import com.mtv.based.uicomponent.core.component.checkbox.compose.AppCheckbox
import com.mtv.based.uicomponent.core.component.dialog.DialogState
import com.mtv.based.uicomponent.core.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.core.component.input.InputState
import com.mtv.based.uicomponent.core.component.input.compose.AppTextField
import com.mtv.based.uicomponent.core.component.sheet.BottomSheetState
import com.mtv.based.uicomponent.core.component.sheet.compose.AppBottomSheet
import com.mtv.based.uicomponent.databinding.ActivityHybridDemoBinding
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class HybridDemoActivity : ComponentActivity() {

    private lateinit var binding: ActivityHybridDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHybridDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ------------------------------
        // XML wrapper components
        // ------------------------------
        binding.xmlDialog.hideDialog()
        binding.xmlBottomSheet.hideBottomSheet()

        binding.xmlInput.setOnClickListener {
            binding.xmlDialog.showDialog(
                title = "XML Dialog",
                message = "Email field clicked!"
            )
        }

        binding.xmlCard.setContent(title = "XML Card") {
            Text("Ini content XML Card")
        }

        binding.xmlBadge.setText("XML Badge")
        binding.xmlCheckbox.setChecked(false) { checked ->
            println("Checkbox XML: $checked")
        }

        // ------------------------------
        // Compose container
        // ------------------------------
        val composeView = ComposeView(this).apply {
            setContent {
                AppTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
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

                        // Compose Dialog trigger
                        Button(onClick = {
                            if (email.isNotBlank()) {
                                dialogState = DialogState.Visible(
                                    title = "Compose Dialog",
                                    message = "Email: $email",
                                    positiveText = "OK"
                                )
                            }
                        }) { Text("Submit Compose") }

                        AppDialog(
                            state = dialogState,
                            onDismiss = { dialogState = DialogState.Hidden },
                            onConfirm = { dialogState = DialogState.Hidden }
                        )

                        // Compose BottomSheet trigger
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

                        // Compose Card + Badge + Checkbox
                        AppCard(title = "Compose Card") {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
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
        }

        // Add ComposeView below XML root
        binding.rootHybrid.addView(composeView, 0)
    }
}
