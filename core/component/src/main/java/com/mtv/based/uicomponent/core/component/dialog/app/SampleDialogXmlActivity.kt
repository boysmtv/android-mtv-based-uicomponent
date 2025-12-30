package com.mtv.based.uicomponent.core.component.dialog.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mtv.based.uicomponent.core.component.databinding.SampleAppDialogXmlBinding

class SampleDialogXmlActivity : AppCompatActivity() {

    private lateinit var binding: SampleAppDialogXmlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SampleAppDialogXmlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowDialog.setOnClickListener {
            binding.dialogView.showDialog(
                title = "Warning",
                message = "Are you sure you want to continue?",
                positive = "Yes",
                negative = "No"
            )
        }
    }
}
