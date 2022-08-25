package com.getit.getit.ui.main.comparison

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.getit.getit.R
import com.getit.getit.databinding.ActivityComparisonAfterBinding
import com.getit.getit.databinding.FragmentComparisonBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.MainActivity
import com.getit.getit.ui.main.comparison.data.ProductImageName
import com.getit.getit.ui.main.comparison.server.ComparisonLikeResult
import com.getit.getit.ui.main.comparison.server.ComparisonLikeService

data class ProductIdImageDrawable(
    var image: Drawable?,
    var id : String
    )

class ComparisonFragment : BaseFragment<FragmentComparisonBinding>(FragmentComparisonBinding::inflate), ComparisonView {
    private var selectedProducts = arrayListOf<ProductIdImageDrawable>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        (activity as MainActivity).setLogoVisible(View.INVISIBLE);
        spinnerSetting()
        binding.comparisonButton.setOnClickListener {
            getComparisonResult();
        }
        return binding.root
    }

    private fun getComparisonResult(){
        startActivity(Intent(this.activity, CompareAfterActivity::class.java))
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

        binding.comparisonProductKindSp.adapter = comparisonProductKindSPAdapter
        binding.comparisonProductKindSp.setSelection(comparisonProductKindSPAdapter.count)
        binding.comparisonProductKindSp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener,
                AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    var spinnerText = (p0?.getChildAt(0)) as TextView
                    spinnerText.textSize = 13f
                    spinnerText.setTextColor(getColor(requireContext(), R.color.normal))

                    if(binding.comparisonProductKindSp.selectedItem.toString() != "기기 종류")
                        attachLikeServer(binding.comparisonProductKindSp.selectedItem.toString())
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                }

            }
    }

    override fun initAfterBinding() {
        setLikedProducts(arrayListOf())
        binding.likeNoTv.text = "기기 종류를 선택해 주세요."

        binding.comparisonSelect1Tv.setOnClickListener {
            var id = binding.comparisonSelect1Tv.text
            for(s in selectedProducts){
                if(s.id == id){
                    selectedProducts.remove(s)
                    break
                }
            }
            setSelectedProducts()
        }

        binding.comparisonSelect2Tv.setOnClickListener {
            var id = binding.comparisonSelect2Tv.text
            for(s in selectedProducts){
                if(s.id == id){
                    selectedProducts.remove(s)
                    break
                }
            }
            setSelectedProducts()
        }

        binding.comparisonButton.setOnClickListener {
            startActivity(Intent(this.activity, CompareAfterActivity::class.java)
                .putExtra("product1", selectedProducts[0].id)
                .putExtra("product2", selectedProducts[1].id)
                .putExtra("kind", binding.comparisonProductKindSp.selectedItem.toString()))
        }
    }

    private fun attachLikeServer(kind : String) {
        var comparisonService = ComparisonLikeService()
        comparisonService.setComparisonView(this)
        comparisonService.loadLikedProductsByKind(kind)
    }

    private fun setLikedProducts(likedProducts : ArrayList<ProductImageName>) {

        val likeProductsRVAdapter = LikeProductsRVAdapter(likedProducts, this.requireActivity(), this)
        binding.likeProductsRv.adapter = likeProductsRVAdapter

        val gridLayoutManager = GridLayoutManager(this.context, 3)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.likeProductsRv.layoutManager = gridLayoutManager

        if(likedProducts.size == 0){
            binding.likeNoTv.text = "좋아요한 상품이 없습니다."
        }
        else{
            binding.likeNoTv.text = ""
        }
    }

    override fun onResume() {
        super.onResume()
        showActionBar()
        setActionBarTitle("비교하기")
    }

    override fun loadLikedProducts(code: Int, result: ComparisonLikeResult){
        var likedProducts = ArrayList<ProductImageName>()

        for(r in result.products){
            likedProducts.add(ProductImageName(r.imageUrl, r.name, r.productId))
        }

        setLikedProducts(likedProducts)
    }

    fun addSelectProducts(image : Drawable, id:String){
        var tempIdImageSet = ProductIdImageDrawable(image, id)
        if(selectedProducts.size >= 2){
            showToast("더 이상 추가할 수 없습니다.")
        }
        else if(!selectedProducts.contains(tempIdImageSet)){
            selectedProducts.add(tempIdImageSet)
        }
        setSelectedProducts()
    }

    private fun setSelectedProducts(){
        when(selectedProducts.size){
            0 -> {
                setToInit1(false,null)
                setToInit2(false, null)
                binding.comparisonButton.background = getDrawable(requireContext(),R.drawable.style_button_off)
                binding.comparisonButton.setTextColor(getColor(this.requireContext(),R.color.subText))
                binding.comparisonButton.setCompoundDrawablesWithIntrinsicBounds(getDrawable(requireContext(),R.drawable.ic_baseline_compare_arrows_off),null,null, null)
                binding.comparisonButton.isEnabled = false
            }
            1->{
                setToInit1(true,selectedProducts[0])
                setToInit2(false, null)
                binding.comparisonButton.background = getDrawable(requireContext(),R.drawable.style_button_off)
                binding.comparisonButton.setTextColor(getColor(this.requireContext(),R.color.subText))
                binding.comparisonButton.setCompoundDrawablesWithIntrinsicBounds(getDrawable(requireContext(),R.drawable.ic_baseline_compare_arrows_off),null,null, null)
                binding.comparisonButton.isEnabled = false
            }
            2->{

                setToInit1(true,selectedProducts[0])
                setToInit2(true, selectedProducts[1])
                binding.comparisonButton.setTextColor(getColor(this.requireContext(),R.color.primary))
                binding.comparisonButton.background = getDrawable(requireContext(),R.drawable.style_button)
                binding.comparisonButton.setCompoundDrawablesWithIntrinsicBounds(getDrawable(requireContext(),R.drawable.ic_baseline_compare_arrows_24),null,null, null)
                binding.comparisonButton.isEnabled = true
            }
        }
    }

    private fun setToInit1(selected : Boolean, idImageSet : ProductIdImageDrawable?){
        when (selected){
            false -> {
                binding.comparisonSelect1Tv.text = "선택1"
                binding.comparisonSelect1Tv.setTextColor(getColor(requireContext(), R.color.outline))
                binding.comparisonSelect1Tv.background = getDrawable(requireContext(),R.drawable.style_dash_outline)
            }
            true ->{
                if (idImageSet != null) {
                    binding.comparisonSelect1Tv.text = idImageSet.id
                    binding.comparisonSelect1Tv.background = idImageSet.image
                }
                binding.comparisonSelect1Tv.setTextColor(getColor(requireContext(), R.color.transparent))
            }
        }
    }
    private fun setToInit2(selected : Boolean, idImageSet : ProductIdImageDrawable?){
        when (selected){
            false -> {

                binding.comparisonSelect2Tv.text = "선택2"
                binding.comparisonSelect2Tv.setTextColor(getColor(requireContext(), R.color.outline))
                binding.comparisonSelect2Tv.background = getDrawable(requireContext(),R.drawable.style_dash_outline)
            }
            true ->{
                if (idImageSet != null) {
                    binding.comparisonSelect2Tv.text = idImageSet.id
                    binding.comparisonSelect2Tv.background = idImageSet.image
                }
                binding.comparisonSelect2Tv.setTextColor(getColor(requireContext(), R.color.transparent))
            }
        }
    }


}