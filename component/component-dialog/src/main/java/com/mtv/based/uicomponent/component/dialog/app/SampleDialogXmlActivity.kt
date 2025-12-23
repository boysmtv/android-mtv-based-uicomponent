package com.mtv.based.uicomponent.component.dialog.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mtv.based.uicomponent.component.dialog.databinding.ActivitySampleDialogXmlBinding

class SampleDialogXmlActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleDialogXmlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleDialogXmlBinding.inflate(layoutInflater)
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
