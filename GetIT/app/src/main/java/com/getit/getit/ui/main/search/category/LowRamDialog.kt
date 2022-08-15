package com.getit.getit.ui.main.search.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogLowRamBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LowRamDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogLowRamBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogLowRamBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogGb4Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogGb6Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogGb8Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}