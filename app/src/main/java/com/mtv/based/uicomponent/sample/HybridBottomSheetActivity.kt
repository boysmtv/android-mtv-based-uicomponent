package com.mtv.based.uicomponent.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.mtv.based.uicomponent.databinding.ActivityHybridBottomSheetBinding
import com.mtv.based.uicomponent.sheet.BottomSheetState
import com.mtv.based.uicomponent.sheet.compose.AppBottomSheet
import com.mtv.based.uicomponent.theme.ui.compose.AppTheme

class HybridBottomSheetActivity : ComponentActivity() {

    private lateinit var binding: ActivityHybridBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHybridBottomSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Compose BottomSheet
        val composeView = ComposeView(this).apply {
            setContent {
                AppTheme {
                    var bottomSheetState by remember { mutableStateOf<BottomSheetState>(BottomSheetState.Hidden) }

                    Box(Modifier.fillMaxSize()) {
                        Button(onClick = {
                            bottomSheetState = BottomSheetState.Visible(
                                "Compose BottomSheet",
                                "Ini content dari Compose"
                            )
                        }, modifier = Modifier.padding(16.dp)) {
                            Text("Show Compose BottomSheet")
                        }

                        AppBottomSheet(
                            state = bottomSheetState,
                            onDismiss = { bottomSheetState = BottomSheetState.Hidden }
                        )
                    }
                }
            }
        }
        binding.root.addView(composeView, 0)

        // XML BottomSheet
        binding.xmlBottomSheet.hideBottomSheet()

        binding.btnShowXml.setOnClickListener {
            binding.xmlBottomSheet.showBottomSheet(
                title = "XML BottomSheet",
                message = "Ini content dari XML wrapper"
            )
        }
    }
}
