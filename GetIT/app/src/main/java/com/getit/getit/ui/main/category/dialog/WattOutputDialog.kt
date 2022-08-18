package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogWattOutputBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WattOutputDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogWattOutputBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWattOutputBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogWatt05To15Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogWatt16To30Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogWatt31To50Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogWatt51To100Btn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
        binding.dialogWatt100UpBtn.setOnClickListener {
            itemClick(4)
            dialog?.dismiss()
        }
    }
}