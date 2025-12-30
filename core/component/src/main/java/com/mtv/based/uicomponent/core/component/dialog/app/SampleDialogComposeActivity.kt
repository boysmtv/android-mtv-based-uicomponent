package com.mtv.based.uicomponent.core.component.dialog.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.core.component.dialog.DialogState
import com.mtv.based.uicomponent.core.component.dialog.compose.AppDialog
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class SampleDialogComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.background
                ) {
                    DialogScreen()
                }
            }
        }
    }
}

@Composable
fun DialogScreen() {
    var dialogState by remember { mutableStateOf<DialogState>(DialogState.Hidden) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Dialog Compose
        AppDialog(
            state = dialogState,
            onDismiss = { dialogState = DialogState.Hidden },
            onConfirm = { dialogState = DialogState.Hidden }
        )

        Button(onClick = {
            dialogState = DialogState.Visible(
                title = "Warning",
                message = "Are you sure you want to continue?",
                positiveText = "Yes",
                negativeText = "No"
            )
        }) {
            Text("Show Dialog")
        }
    }
}
