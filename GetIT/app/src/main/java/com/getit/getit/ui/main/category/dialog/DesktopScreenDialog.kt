package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogDesktopScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DesktopScreenDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDesktopScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDesktopScreenBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogInch22Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogInch24Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogInch27Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogInch29Btn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
        binding.dialogInch32Btn.setOnClickListener {
            itemClick(4)
            dialog?.dismiss()
        }
        binding.dialogInch34Btn.setOnClickListener {
            itemClick(5)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(6)
            dialog?.dismiss()
        }
    }
}