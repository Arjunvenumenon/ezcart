package com.example.ezcart.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import com.example.ezcart.databinding.DialogCommonBinding


class CommonDialog(
    context: Context,
    private val message: String,
    private val cancelable: Boolean = false,
    private val action: (Boolean) -> Unit
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DialogCommonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window? = window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        setCancelable(false)

        binding.message.text = message

        binding.yes.setOnClickListener {
            dismiss()
            action(true)
        }

        binding.no.setOnClickListener {
            dismiss()
            action(false)
        }
    }
}