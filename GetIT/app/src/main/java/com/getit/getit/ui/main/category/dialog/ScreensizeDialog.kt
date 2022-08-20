package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogScreensizeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ScreensizeDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogScreensizeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogScreensizeBinding.inflate(inflater, container,false)

        binding.dialogScreensizeCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogScreensize13inchesBtn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogScreensize15inchesBtn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogScreensize17inchesBtn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogScreensizeEctBtn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}