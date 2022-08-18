package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogTabletScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TabletScreenDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogTabletScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogTabletScreenBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogInch11Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogInch13Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogInch13Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}