package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogLaptopRamBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LaptopRamDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogLaptopRamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLaptopRamBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogRamGb8Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogRamGb16Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogRamEctBtn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
    }
}