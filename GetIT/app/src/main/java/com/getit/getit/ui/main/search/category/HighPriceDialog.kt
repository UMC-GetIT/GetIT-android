package com.getit.getit.ui.main.search.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogHighPriceBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HighPriceDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogHighPriceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogHighPriceBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogPrice100DownBtn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogPrice100To200Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogPrice200UpBtn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
    }
}