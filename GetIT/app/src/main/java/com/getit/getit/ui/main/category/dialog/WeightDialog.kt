package com.getit.getit.ui.main.category.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogWeightBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class WeightDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogWeightBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogWeightBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogWeight1Btn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogWeight2Btn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogWeight3Btn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
    }
}