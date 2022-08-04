package com.getit.getit.ui.main.comparison

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentComparisonBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.search.SearchRVAdapter


class ComparisonFragment(): BaseFragment<FragmentComparisonBinding>(FragmentComparisonBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun initAfterBinding() {

    }

//    override fun onResume() {
//        super.onResume()
//        (activity as MainActivity).showActionBar() //지난번에 넣은 코드에서 이 부분만 추가
//        (activity as MainActivity).setActionBarTitle("비교 하기")
//        //(activity as MainActivity).supportActionBar?.setSubtitle("기기 종류를 선택하여 좋아요 목록을 확인해보세요")
//
//    }

    override fun onResume() {
        super.onResume()
        showActionBar() //지난번에 넣은 코드에서 이 부분만 추가
        setActionBarTitle("비교하기")
    }
}