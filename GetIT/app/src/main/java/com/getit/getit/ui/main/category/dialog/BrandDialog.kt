package com.getit.getit.ui.main.category.dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.getit.getit.databinding.DialogBrandBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BrandDialog(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {
    private lateinit var binding: DialogBrandBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogBrandBinding.inflate(inflater, container,false)

        binding.dialogCloseIb.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dialogBrandLgBtn.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.dialogBrandAppleBtn.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.dialogBrandSamsungBtn.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
    }
}