package com.getit.getit.ui.main.comparison

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.getit.getit.R
import com.getit.getit.databinding.FragmentComparisonBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.comparison.data.ProductImageName


class ComparisonFragment : BaseFragment<FragmentComparisonBinding>(FragmentComparisonBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        spinnerSetting()
        return binding.root
    }

    private fun spinnerSetting() {
        val items = resources.getStringArray(R.array.productKind)

        val comparisonProductKindSPAdapter = object : ArrayAdapter<String>(
            this.requireContext(),
            R.layout.support_simple_spinner_dropdown_item
        ) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val v = super.getView(position, convertView, parent)

                if (position == count) {
                    (v.findViewById<View>(binding.comparisonProductKindSp.id) as? TextView)?.text ?: ""
                    (v.findViewById<View>(binding.comparisonProductKindSp.id) as? TextView)?.hint ?: getItem(count)
                }

                return v
            }

            override fun getCount(): Int {
                return super.getCount() - 1
            }

        }

        comparisonProductKindSPAdapter.addAll(items.toMutableList())
        comparisonProductKindSPAdapter.setDropDownViewResource(R.layout.item_dropdown)
        comparisonProductKindSPAdapter.add("기기 종류")

        //val comparisonProductKindSPAdapter = this.context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, items) }
        binding.comparisonProductKindSp.adapter = comparisonProductKindSPAdapter
        binding.comparisonProductKindSp.setSelection(comparisonProductKindSPAdapter.count)
        binding.comparisonProductKindSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    var spinnerText = (p0?.getChildAt(0)) as TextView
                    spinnerText.textSize = 13f
                    spinnerText.setTextColor(getColor(requireContext(), R.color.normal))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                }

            }
    }

    override fun initAfterBinding() {
        var likedProducts = ArrayList<ProductImageName>()
        // 더미데이터
        likedProducts.apply {
            add(ProductImageName(R.drawable.samsong_labtop_img, "삼성전자 2021 갤럭시북 프로 360 13.3 + S펜" ))
            add(ProductImageName(R.drawable.lg_labtop_img, "LG전자 2022 그램 16" ))
            add(ProductImageName(R.drawable.samsong_pro_labtop_img, "삼성전자 2021 갤럭시 북 프로 360" ))
            add(ProductImageName(R.drawable.lg_gram_labtop_img, "2022 LG 그램 360 터치스크린 노트북" ))
            add(ProductImageName(R.drawable.apple_labtop_img, "Apple 2021 맥북프로 14"))
            add(ProductImageName(R.drawable.legion_labtop_img, "레노버 Legion i7"))
            add(ProductImageName(R.drawable.apple_labtop2_img, "Apple 2021 맥북프로 14"))
            add(ProductImageName(R.drawable.samsong2_labtop_img,"풀스펙 NT371B5J 램16G SSD512G 윈도우10"))
        }

        val likeProductsRVAdapter = LikeProductsRVAdapter(likedProducts)
        binding.likeProductsRv.adapter = likeProductsRVAdapter

        val gridLayoutManager = GridLayoutManager(this.context, 3)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.likeProductsRv.layoutManager = gridLayoutManager
    }

    override fun onResume() {
        super.onResume()
        showActionBar() //지난번에 넣은 코드에서 이 부분만 추가
        setActionBarTitle("비교하기")
    }
}