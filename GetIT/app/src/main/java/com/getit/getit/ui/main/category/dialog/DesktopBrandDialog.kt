package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogDesktopBrandBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DesktopBrandDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogDesktopBrandBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDesktopBrandBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogBrandAppleBtn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogBrandSamsungElectronicsBtn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogBrandLgElectronicsBtn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogWattBrandLenovoBtn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(4)
            dialog?.dismiss()
        }
    }
}