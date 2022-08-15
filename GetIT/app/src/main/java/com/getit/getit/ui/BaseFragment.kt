package com.getit.getit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.utils.Inflate


abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //_binding = inflate.invoke(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initAfterBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun initAfterBinding()

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun setActionBarTitle(title: String?) {
        (activity as MainActivity).supportActionBar?.title = title
    }

    // 툴바 쓴다
    fun showActionBar(){
        (activity as MainActivity).supportActionBar?.show()
    }

    // 툴바 안쓴다
    fun hideActionBar() {
        (activity as MainActivity).supportActionBar?.hide()
    }

}