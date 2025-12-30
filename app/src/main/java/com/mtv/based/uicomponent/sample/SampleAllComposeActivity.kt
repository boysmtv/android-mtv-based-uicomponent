package com.mtv.based.uicomponent.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.core.component.badge.compose.AppBadge
import com.mtv.based.uicomponent.core.component.card.compose.AppCard
import com.mtv.based.uicomponent.core.component.checkbox.compose.AppCheckbox
import com.mtv.based.uicomponent.core.component.dialog.DialogState
import com.mtv.based.uicomponent.core.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.core.component.input.InputState
import com.mtv.based.uicomponent.core.component.input.compose.AppTextField
import com.mtv.based.uicomponent.core.component.loading.LoadingV1
import com.mtv.based.uicomponent.core.component.loading.LoadingV2
import com.mtv.based.uicomponent.core.component.sheet.BottomSheetState
import com.mtv.based.uicomponent.core.component.sheet.compose.AppBottomSheet
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class SampleAllComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    var email by remember { mutableStateOf("") }
                    var emailError by remember { mutableStateOf(false) }
                    var dialogState by remember { mutableStateOf<DialogState>(DialogState.Hidden) }
                    var bottomSheetState by remember { mutableStateOf<BottomSheetState>(BottomSheetState.Hidden) }
                    var cardChecked by remember { mutableStateOf(false) }

                    // Input
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

                    // Dialog trigger
                    Button(onClick = {
                        if (!email.isBlank()) {
                            dialogState = DialogState.Visible(
                                title = "Compose Dialog",
                                message = "Email: $email",
                                positiveText = "OK"
                            )
                        }
                    }) { Text("Submit") }

                    // Dialog
                    AppDialog(
                        state = dialogState,
                        onDismiss = { dialogState = DialogState.Hidden },
                        onConfirm = { dialogState = DialogState.Hidden }
                    )

                    // BottomSheet trigger
                    Button(onClick = {
                        bottomSheetState = BottomSheetState.Visible(
                            title = "Compose BottomSheet",
                            message = "Ini content dari Compose"
                        )
                    }) { Text("Show BottomSheet") }

                    // BottomSheet
                    AppBottomSheet(
                        state = bottomSheetState,
                        onDismiss = { bottomSheetState = BottomSheetState.Hidden }
                    )

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingV2()
                    }

                    // Card + Badge + Checkbox
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
}
