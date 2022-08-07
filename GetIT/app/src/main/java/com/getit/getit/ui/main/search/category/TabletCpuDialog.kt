package com.getit.getit.ui.main.search.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogTabletCpuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TabletCpuDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogTabletCpuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogTabletCpuBinding.inflate(inflater, container,false)

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
        binding.dialogCpuM1Btn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(4)
            dialog?.dismiss()
        }
    }
}