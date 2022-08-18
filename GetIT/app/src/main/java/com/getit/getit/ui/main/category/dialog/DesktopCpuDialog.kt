package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogDesktopCpuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DesktopCpuDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDesktopCpuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDesktopCpuBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogCpuI3Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogCpuI5Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogCpuI7Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogCpuI9Btn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
        binding.dialogCpuRyzen5Btn.setOnClickListener {
            itemClick(4)
            dialog?.dismiss()
        }
        binding.dialogCpuRyzen7Btn.setOnClickListener {
            itemClick(5)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(6)
            dialog?.dismiss()
        }
    }
}