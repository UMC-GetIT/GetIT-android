package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogLowPriceBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LowPriceDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogLowPriceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLowPriceBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogPrice1DownBtn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogPrice1To5Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogPrice5To10Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogPrice10UpBtn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}