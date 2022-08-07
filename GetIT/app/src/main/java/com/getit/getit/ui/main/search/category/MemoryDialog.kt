package com.getit.getit.ui.main.search.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogMemoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MemoryDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogMemoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogMemoryBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogGb128Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogGb256Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogGb512Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.dialogEctBtn.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}