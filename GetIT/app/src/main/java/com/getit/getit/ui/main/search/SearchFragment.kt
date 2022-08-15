package com.getit.getit.ui.main.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.R
import com.getit.getit.data.Category
import com.getit.getit.databinding.FragmentSearchBinding
import com.getit.getit.ui.BaseFragment
import com.getit.getit.ui.main.search.category.*
import com.google.gson.Gson

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate), SearchView {
    private lateinit var searchRVAdatpter: SearchRVAdapter

    override fun initAfterBinding() {
        Log.d("LOG","initAfterBinding 호출")
        onLaptopBtn()

        // 제품 카테고리 선택
         binding.laptopCardView.setOnClickListener {
             offAllCategoryBtn() // 모든 버튼 초기화 및 비활성화
             onLaptopBtn()
             offAllDetailCategoryBtn()  // 다른 제품 세부 카테고리 초기화 기능
         }
        binding.phoneCardView.setOnClickListener {
            offAllCategoryBtn()
            onPhoneBtn()
            offAllDetailCategoryBtn()
        }
        binding.tabletCardView.setOnClickListener {
            offAllCategoryBtn()
            onTabletBtn()
            offAllDetailCategoryBtn()
        }
        binding.speakerCardView.setOnClickListener {
            offAllCategoryBtn()
            onSpeakerBtn()
            offAllDetailCategoryBtn()
        }
        binding.desktopCardView.setOnClickListener {
            offAllCategoryBtn()
            onDesktopBtn()
            offAllDetailCategoryBtn()
        }

        // 노트북 카테고리
        binding.searchDetailCategoryLaptopScreensizeBtn.setOnClickListener {
            selectScreensize()
        }
        binding.searchDetailCategoryLaptopPriceBtn.setOnClickListener {
            selectHighPrice("laptop")
        }
        binding.searchDetailCategoryLaptopBrandBtn.setOnClickListener {
            selectBrand()
        }
        binding.searchDetailCategoryLaptopCpuBtn.setOnClickListener {
            selectLaptopCpu()
        }
        binding.searchDetailCategoryLaptopRamBtn.setOnClickListener {
            selectLaptopRam()
        }
        binding.searchDetailCategoryLaptopWeightBtn.setOnClickListener {
            selectWeight()
        }

        // 휴대폰 카테고리
        binding.searchDetailCategoryPhonePriceBtn.setOnClickListener {
            selectHighPrice("phone")
        }
        binding.searchDetailCategoryPhoneProtocolBtn.setOnClickListener {
            selectProtocol()
        }
        binding.searchDetailCategoryPhoneInternalStorageBtn.setOnClickListener {
            selectMemory("phone")
        }
        binding.searchDetailCategoryPhoneRamBtn.setOnClickListener {
            selectLowRam("phone")
        }
        binding.searchDetailCategoryPhoneBrandBtn.setOnClickListener {
            selectFewBrand("phone")
        }

        // 태블릿 카테고리
        binding.searchDetailTabletCategoryPriceBtn.setOnClickListener {
            selectHighPrice("tablet")
        }
        binding.searchDetailTabletCategoryStorageCapacityBtn.setOnClickListener {
            selectMemory("tablet")
        }
        binding.searchDetailTabletCategoryRamBtn.setOnClickListener {
            selectLowRam("tablet")
        }
        binding.searchDetailTabletCategoryBrandBtn.setOnClickListener {
            selectFewBrand("tablet")
        }
        binding.searchDetailTabletCategoryCpuBtn.setOnClickListener {
            selectTabletCpu()
        }
        binding.searchDetailTabletCategoryScreensizeBtn.setOnClickListener {
            selectTabletScreen()
        }

        // 스피커 카테고리
        binding.searchDetailCategorySpeakerPriceBtn.setOnClickListener {
            selectLowPrice()
        }
        binding.searchDetailCategorySpeakerBrandBtn.setOnClickListener {
            selectSpeakerBrand()
        }
        binding.searchDetailCategorySpeakerWattOutputBtn.setOnClickListener {
            selectWattOutput()
        }

        // 데스크탑 카테고리
        binding.searchDetailCategoryDesktopPriceBtn.setOnClickListener {
            selectHighPrice("desktop")
        }
        binding.searchDetailCategoryDesktopBrandBtn.setOnClickListener {
            selectDesktopBrand()
        }
        binding.searchDetailCategoryDesktopScreensizeBtn.setOnClickListener {
            selectDesktopScreen()
        }
        binding.searchDetailCategoryDesktopCpuBtn.setOnClickListener {
            selectDesktopCpu()
        }
        binding.searchDetailCategoryDesktopRamBtn.setOnClickListener {
            selectDesktopRam()
        }
    }

    override fun onStart() {
        Log.d("LOG","onStart 호출")
        super.onStart()
        Log.d("LOG","onStart super 호출")
        getCategory()
        Log.d("LOG","getCategory 호출")
    }

    private fun initRecyclerView(result: List<CategoryResult>) {
        Log.d("LOG","initRecyclerView 호출")
        searchRVAdatpter = SearchRVAdapter(requireContext(), result)
        Log.d("LOG","searchRVAdatpter 할당 <- 여기서부터 실행 x")
        binding.searchProductRv.adapter = searchRVAdatpter
        Log.d("LOG","searchRVAdatpter 연결")

        // 상품 클릭
//        searchRVAdatpter.setProductClickListener(object: SearchRVAdapter.ProductClickListener{
//            override fun onItemClick(products: Products) {
//                changeProductActivity(products)
//            }
//        })
    }

    private fun getCategory() {
        val categoryService = CategoryService()
        categoryService.setSearchView(this)
        categoryService.getCategory(Category(getString(R.string.laptop), getString(R.string.string_null)))
    }

    private fun onDetailCategoryButton(btn: Button) {
        btn.setTextColor(getColor(requireContext(), R.color.primary))
        btn.setBackgroundResource(R.drawable.style_button_on)
        btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_blue, 0)
    }

    private fun offDetailCategoryButton(btn: Button) {
        btn.setTextColor(getColor(requireContext(), R.color.normal))
        btn.setBackgroundResource(R.drawable.style_detailed_category_button)
        btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down_black, 0)
    }

    private fun offAllDetailCategoryBtn() {
        binding.searchDetailCategoryLaptopScreensizeBtn.text = getString(R.string.screen_size)
        offDetailCategoryButton(binding.searchDetailCategoryLaptopScreensizeBtn)
        binding.searchDetailCategoryLaptopPriceBtn.text = getString(R.string.price)
        offDetailCategoryButton(binding.searchDetailCategoryLaptopPriceBtn)
        binding.searchDetailCategoryLaptopBrandBtn.text = getString(R.string.brand)
        offDetailCategoryButton(binding.searchDetailCategoryLaptopBrandBtn)
        binding.searchDetailCategoryLaptopCpuBtn.text = getString(R.string.cpu)
        offDetailCategoryButton(binding.searchDetailCategoryLaptopCpuBtn)
        binding.searchDetailCategoryLaptopRamBtn.text = getString(R.string.ram)
        offDetailCategoryButton(binding.searchDetailCategoryLaptopRamBtn)
        binding.searchDetailCategoryLaptopWeightBtn.text = getString(R.string.weight)
        offDetailCategoryButton(binding.searchDetailCategoryLaptopWeightBtn)


        binding.searchDetailCategoryPhonePriceBtn.text = getString(R.string.price)
        offDetailCategoryButton(binding.searchDetailCategoryPhonePriceBtn)
        binding.searchDetailCategoryPhoneBrandBtn.text = getString(R.string.brand)
        offDetailCategoryButton(binding.searchDetailCategoryPhoneBrandBtn)
        binding.searchDetailCategoryPhoneRamBtn.text = getString(R.string.ram)
        offDetailCategoryButton(binding.searchDetailCategoryPhoneRamBtn)
        binding.searchDetailCategoryPhoneInternalStorageBtn.text = getString(R.string.internal_storage)
        offDetailCategoryButton(binding.searchDetailCategoryPhoneInternalStorageBtn)
        binding.searchDetailCategoryPhoneProtocolBtn.text = getString(R.string.protocol)
        offDetailCategoryButton(binding.searchDetailCategoryPhoneProtocolBtn)

        binding.searchDetailTabletCategoryScreensizeBtn.text = getString(R.string.screen_size)
        offDetailCategoryButton(binding.searchDetailTabletCategoryScreensizeBtn)
        binding.searchDetailTabletCategoryPriceBtn.text = getString(R.string.price)
        offDetailCategoryButton(binding.searchDetailTabletCategoryPriceBtn)
        binding.searchDetailTabletCategoryBrandBtn.text = getString(R.string.brand)
        offDetailCategoryButton(binding.searchDetailTabletCategoryBrandBtn)
        binding.searchDetailTabletCategoryStorageCapacityBtn.text = getString(R.string.storage_capacity)
        offDetailCategoryButton(binding.searchDetailTabletCategoryStorageCapacityBtn)
        binding.searchDetailTabletCategoryRamBtn.text = getString(R.string.ram)
        offDetailCategoryButton(binding.searchDetailTabletCategoryRamBtn)
        binding.searchDetailTabletCategoryCpuBtn.text = getString(R.string.cpu)
        offDetailCategoryButton(binding.searchDetailTabletCategoryCpuBtn)

        binding.searchDetailCategorySpeakerPriceBtn.text = getString(R.string.price)
        offDetailCategoryButton(binding.searchDetailCategorySpeakerPriceBtn)
        binding.searchDetailCategorySpeakerBrandBtn.text = getString(R.string.brand)
        offDetailCategoryButton(binding.searchDetailCategorySpeakerBrandBtn)
        binding.searchDetailCategorySpeakerWattOutputBtn.text = getString(R.string.watt_output)
        offDetailCategoryButton(binding.searchDetailCategorySpeakerWattOutputBtn)

        binding.searchDetailCategoryDesktopScreensizeBtn.text = getString(R.string.screen_size)
        offDetailCategoryButton(binding.searchDetailCategoryDesktopScreensizeBtn)
        binding.searchDetailCategoryDesktopPriceBtn.text = getString(R.string.price)
        offDetailCategoryButton(binding.searchDetailCategoryDesktopPriceBtn)
        binding.searchDetailCategoryDesktopBrandBtn.text = getString(R.string.brand)
        offDetailCategoryButton(binding.searchDetailCategoryDesktopBrandBtn)
        binding.searchDetailCategoryDesktopCpuBtn.text = getString(R.string.cpu)
        offDetailCategoryButton(binding.searchDetailCategoryDesktopCpuBtn)
        binding.searchDetailCategoryDesktopRamBtn.text = getString(R.string.ram)
        offDetailCategoryButton(binding.searchDetailCategoryDesktopRamBtn)
    }

    private fun selectProtocol() {
        val protocolDialog: ProtocolDialog = ProtocolDialog {
            var protocol: String = when (it) {
                0 -> getString(R.string.protocol_3g)
                1 -> getString(R.string.protocol_4g)
                2 -> getString(R.string.protocol_5g)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryPhoneProtocolBtn.text = protocol
            onDetailCategoryButton(binding.searchDetailCategoryPhoneProtocolBtn)
        }
        protocolDialog.show(parentFragmentManager, "ProtocolDialog")
    }

    private fun selectHighPrice(product: String) {
        val highPriceDialog: HighPriceDialog = HighPriceDialog {
            var price: String = when (it) {
                0 -> getString(R.string.price_100_down)
                1 -> getString(R.string.price_100_to_200)
                else -> getString(R.string.price_200_up)
            }
            when (product) {
                "laptop" -> {
                    binding.searchDetailCategoryLaptopPriceBtn.text = price
                    onDetailCategoryButton(binding.searchDetailCategoryLaptopPriceBtn)
                }
                "phone" -> {
                    binding.searchDetailCategoryPhonePriceBtn.text = price
                    onDetailCategoryButton(binding.searchDetailCategoryPhonePriceBtn)
                }
                "tablet" -> {
                    binding.searchDetailTabletCategoryPriceBtn.text = price
                    onDetailCategoryButton(binding.searchDetailTabletCategoryPriceBtn)
                }
                "desktop" -> {
                    binding.searchDetailCategoryDesktopPriceBtn.text = price
                    onDetailCategoryButton(binding.searchDetailCategoryDesktopPriceBtn)
                }
            }
        }
        highPriceDialog.show(parentFragmentManager, "HighPriceDialog")
    }

    private fun selectScreensize() {
        val screensizeDialog: ScreensizeDialog = ScreensizeDialog {
            var screensize: String = when (it) {
                0 -> getString(R.string.inch13)
                1 -> getString(R.string.inch15)
                2 -> getString(R.string.inch17)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryLaptopScreensizeBtn.text = screensize
            onDetailCategoryButton(binding.searchDetailCategoryLaptopScreensizeBtn)
        }
        screensizeDialog.show(parentFragmentManager, "ScreensizeDialog")
    }

    private fun selectLowPrice() {
        val lowPriceDialog: LowPriceDialog = LowPriceDialog {
            var price: String = when (it) {
                0 -> getString(R.string.price_1_down)
                1 -> getString(R.string.price_1_to_5)
                2 -> getString(R.string.price_5_to_10)
                else -> getString(R.string.price_10_up)
            }
            binding.searchDetailCategorySpeakerPriceBtn.text = price
            onDetailCategoryButton(binding.searchDetailCategorySpeakerPriceBtn)
        }
        lowPriceDialog.show(parentFragmentManager, "LowPriceDialog")
    }

    private fun selectBrand() {
        val brandDialog: BrandDialog = BrandDialog {
            var brand: String = when (it) {
                0 -> getString(R.string.brand_lg)
                1 -> getString(R.string.brand_apple)
                else -> getString(R.string.brand_samsung)
            }
            binding.searchDetailCategoryLaptopBrandBtn.text = brand
            onDetailCategoryButton(binding.searchDetailCategoryLaptopBrandBtn)
        }
        brandDialog.show(parentFragmentManager, "BrandDialog")
    }

    private fun selectLaptopCpu() {
        val laptopCpuDialog: LaptopCpuDialog = LaptopCpuDialog {
            var cpu: String = when (it) {
                0 -> getString(R.string.cpu_i3)
                1 -> getString(R.string.cpu_i5)
                2 -> getString(R.string.cpu_i7)
                3 -> getString(R.string.cpu_i9)
                4 -> getString(R.string.cpu_M1)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryLaptopCpuBtn.text = cpu
            onDetailCategoryButton(binding.searchDetailCategoryLaptopCpuBtn)
        }
        laptopCpuDialog.show(parentFragmentManager, "LaptopCpuDialog")
    }

    private fun selectLaptopRam() {
        val laptopRamDialog: LaptopRamDialog = LaptopRamDialog {
            var ram: String = when (it) {
                0 -> getString(R.string.gb8)
                1 -> getString(R.string.gb16)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryLaptopRamBtn.text = ram
            onDetailCategoryButton(binding.searchDetailCategoryLaptopRamBtn)
        }
        laptopRamDialog.show(parentFragmentManager, "LaptopRamDialog")
    }

    private fun selectWeight() {
        val weightDialog: WeightDialog = WeightDialog {
            var weight: String = when (it) {
                0 -> getString(R.string.weight1)
                1 -> getString(R.string.weight2)
                else -> getString(R.string.weight3)
            }
            binding.searchDetailCategoryLaptopWeightBtn.text = weight
            onDetailCategoryButton(binding.searchDetailCategoryLaptopWeightBtn)
        }
        weightDialog.show(parentFragmentManager, "WeightDialog")
    }

    private fun selectMemory(product: String) {
        val memoryDialog: MemoryDialog = MemoryDialog {
            var memory: String = when (it) {
                0 -> getString(R.string.gb128)
                1 -> getString(R.string.gb256)
                2 -> getString(R.string.gb512)
                else -> getString(R.string.ect)
            }
            when (product) {
                "phone" -> {
                    binding.searchDetailCategoryPhoneInternalStorageBtn.text = memory
                    onDetailCategoryButton(binding.searchDetailCategoryPhoneInternalStorageBtn)
                    }
                "tablet" ->{
                    binding.searchDetailTabletCategoryStorageCapacityBtn.text = memory
                    onDetailCategoryButton(binding.searchDetailTabletCategoryStorageCapacityBtn)
                    }
                }
            }
        memoryDialog.show(parentFragmentManager, "MemoryDialog")
    }

    private fun selectLowRam(product: String) {
        val lowRamDialog: LowRamDialog = LowRamDialog {
            var ram: String = when (it) {
                0 -> getString(R.string.gb4)
                1 -> getString(R.string.gb6)
                2 -> getString(R.string.gb8)
                else -> getString(R.string.ect)
            }
            when (product) {
                "phone" -> {
                    binding.searchDetailCategoryPhoneRamBtn.text = ram
                    onDetailCategoryButton(binding.searchDetailCategoryPhoneRamBtn)
                }
                "tablet" ->{
                    binding.searchDetailTabletCategoryRamBtn.text = ram
                    onDetailCategoryButton(binding.searchDetailTabletCategoryRamBtn)
                }
            }
        }
        lowRamDialog.show(parentFragmentManager, "LowRamDialog")
    }

    private fun selectSpeakerBrand() {
        val speakerBrandDialog: SpeakerBrandDialog = SpeakerBrandDialog {
            var brand: String = when (it) {
                0 -> getString(R.string.brand_jbl)
                1 -> getString(R.string.brand_marshall)
                2 -> getString(R.string.brand_geneva)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategorySpeakerBrandBtn.text = brand
            onDetailCategoryButton(binding.searchDetailCategorySpeakerBrandBtn)
        }
        speakerBrandDialog.show(parentFragmentManager, "SpeakerBrandDialog")
    }

    private fun selectFewBrand(product: String) {
        val fewBrandDialog: FewBrandDialog = FewBrandDialog {
            var brand: String = when (it) {
                0 -> getString(R.string.brand_apple)
                1 -> getString(R.string.brand_samsung)
                else -> getString(R.string.ect)
            }
            when (product) {
                "phone" -> {
                    binding.searchDetailCategoryPhoneBrandBtn.text = brand
                    onDetailCategoryButton(binding.searchDetailCategoryPhoneBrandBtn)
                }
                "tablet" ->{
                    binding.searchDetailTabletCategoryBrandBtn.text = brand
                    onDetailCategoryButton(binding.searchDetailTabletCategoryBrandBtn)
                }
            }
        }
        fewBrandDialog.show(parentFragmentManager, "FewBrandDialog")
    }

    private fun selectWattOutput() {
        val wattOutputDialog: WattOutputDialog = WattOutputDialog {
            var watt: String = when (it) {
                0 -> getString(R.string.watt_05_to_15)
                1 -> getString(R.string.watt_16_to_30)
                2 -> getString(R.string.watt_31_to_50)
                3 -> getString(R.string.watt_51_to_100)
                else -> getString(R.string.watt_100_up)
            }
            binding.searchDetailCategorySpeakerWattOutputBtn.text = watt
            onDetailCategoryButton(binding.searchDetailCategorySpeakerWattOutputBtn)
        }
        wattOutputDialog.show(parentFragmentManager, "WattOutputDialog")
    }

    private fun selectDesktopBrand() {
        val desktopBrandDialog: DesktopBrandDialog = DesktopBrandDialog {
            var brand: String = when (it) {
                0 -> getString(R.string.brand_apple)
                1 -> getString(R.string.brand_samsung_electronics)
                2 -> getString(R.string.brand_lg_electronics)
                3 -> getString(R.string.brand_lenovo)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryDesktopBrandBtn.text = brand
            onDetailCategoryButton(binding.searchDetailCategoryDesktopBrandBtn)
        }
        desktopBrandDialog.show(parentFragmentManager, "DesktopBrandDialog")
    }

    private fun selectDesktopScreen() {
        val desktopScreenDialog: DesktopScreenDialog = DesktopScreenDialog {
            var screen: String = when (it) {
                0 -> getString(R.string.inch22)
                1 -> getString(R.string.inch24)
                2 -> getString(R.string.inch27)
                3 -> getString(R.string.inch29)
                4 -> getString(R.string.inch32)
                5 -> getString(R.string.inch34)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryDesktopScreensizeBtn.text = screen
            onDetailCategoryButton(binding.searchDetailCategoryDesktopScreensizeBtn)
        }
        desktopScreenDialog.show(parentFragmentManager, "DesktopScreenDialog")
    }

    private fun selectDesktopCpu() {
        val desktopCpuDialog: DesktopCpuDialog = DesktopCpuDialog {
            var cpu: String = when (it) {
                0 -> getString(R.string.cpu_i3)
                1 -> getString(R.string.cpu_i5)
                2 -> getString(R.string.cpu_i7)
                3 -> getString(R.string.cpu_i9)
                4 -> getString(R.string.cpu_ryzen5)
                5 -> getString(R.string.cpu_ryzen7)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryDesktopCpuBtn.text = cpu
            onDetailCategoryButton(binding.searchDetailCategoryDesktopCpuBtn)
        }
        desktopCpuDialog.show(parentFragmentManager, "DesktopCpuDialog")
    }

    private fun selectDesktopRam() {
        val desktopRamDialog: DesktopRamDialog = DesktopRamDialog {
            var ram: String = when (it) {
                0 -> getString(R.string.gb4)
                1 -> getString(R.string.gb8)
                2 -> getString(R.string.gb16)
                3 -> getString(R.string.gb32)
                else -> getString(R.string.ect)
            }
            binding.searchDetailCategoryDesktopRamBtn.text = ram
            onDetailCategoryButton(binding.searchDetailCategoryDesktopRamBtn)
        }
        desktopRamDialog.show(parentFragmentManager, "DesktopRamDialog")
    }

    private fun selectTabletCpu() {
        val tabletCpuDialog: TabletCpuDialog = TabletCpuDialog {
            var cpu: String = when (it) {
                0 -> getString(R.string.cpu_i3)
                1 -> getString(R.string.cpu_i5)
                2 -> getString(R.string.cpu_i7)
                3 -> getString(R.string.cpu_M1)
                else -> getString(R.string.ect)
            }
            binding.searchDetailTabletCategoryCpuBtn.text = cpu
            onDetailCategoryButton(binding.searchDetailTabletCategoryCpuBtn)
        }
        tabletCpuDialog.show(parentFragmentManager, "TabletCpuDialog")
    }

    private fun selectTabletScreen() {
        val tabletScreenDialog: TabletScreenDialog = TabletScreenDialog {
            var screen: String = when (it) {
                0 -> getString(R.string.inch11)
                1 -> getString(R.string.inch13)
                2 -> getString(R.string.inch15)
                else -> getString(R.string.ect)
            }
            binding.searchDetailTabletCategoryScreensizeBtn.text = screen
            onDetailCategoryButton(binding.searchDetailTabletCategoryScreensizeBtn)
        }
        tabletScreenDialog.show(parentFragmentManager, "TabletScreenDialog")
    }

    private fun onLaptopBtn() {
        binding.laptopCardView.setCardBackgroundColor(getColor(requireContext(), R.color.primary))
        binding.laptopTv.setTextColor(getColor(requireContext(), R.color.white))
        binding.categorySearchLaptopScrollview.visibility = View.VISIBLE
        binding.laptopCardView.isEnabled = false
    }
    private fun onPhoneBtn() {
        binding.phoneCardView.setCardBackgroundColor(getColor(requireContext(), R.color.primary))
        binding.phoneTv.setTextColor(getColor(requireContext(), R.color.white))
        binding.categorySearchPhoneScrollview.visibility = View.VISIBLE
        binding.phoneCardView.isEnabled = false
    }
    private fun onTabletBtn() {
        binding.tabletCardView.setCardBackgroundColor(getColor(requireContext(), R.color.primary))
        binding.tabletTv.setTextColor(getColor(requireContext(), R.color.white))
        binding.categorySearchTabletScrollview.visibility = View.VISIBLE
        binding.tabletCardView.isEnabled = false
    }
    private fun onSpeakerBtn() {
        binding.speakerCardView.setCardBackgroundColor(getColor(requireContext(), R.color.primary))
        binding.speakerTv.setTextColor(getColor(requireContext(), R.color.white))
        binding.categorySearchSpeakerScrollview.visibility = View.VISIBLE
        binding.speakerCardView.isEnabled = false
    }
    private fun onDesktopBtn() {
        binding.desktopCardView.setCardBackgroundColor(getColor(requireContext(), R.color.primary))
        binding.desktopTv.setTextColor(getColor(requireContext(), R.color.white))
        binding.categorySearchDesktopScrollview.visibility = View.VISIBLE
        binding.desktopCardView.isEnabled = false
    }

    private fun offAllCategoryBtn() {
        // 모든 버튼 색상 초기화
        binding.laptopCardView.setCardBackgroundColor(null)
        binding.laptopTv.setTextColor(getColor(requireContext(), R.color.normal))

        binding.phoneCardView.setCardBackgroundColor(null)
        binding.phoneTv.setTextColor(getColor(requireContext(), R.color.normal))

        binding.tabletCardView.setCardBackgroundColor(null)
        binding.tabletTv.setTextColor(getColor(requireContext(), R.color.normal))

        binding.speakerCardView.setCardBackgroundColor(null)
        binding.speakerTv.setTextColor(getColor(requireContext(), R.color.normal))

        binding.desktopCardView.setCardBackgroundColor(null)
        binding.desktopTv.setTextColor(getColor(requireContext(), R.color.normal))

        // 모든 카테고리 visibility = gone
        binding.categorySearchLaptopScrollview.visibility = View.GONE
        binding.categorySearchPhoneScrollview.visibility = View.GONE
        binding.categorySearchTabletScrollview.visibility = View.GONE
        binding.categorySearchSpeakerScrollview.visibility = View.GONE
        binding.categorySearchDesktopScrollview.visibility = View.GONE

        // 모든 버튼 비활성화
        binding.laptopCardView.isEnabled = true
        binding.phoneCardView.isEnabled = true
        binding.tabletCardView.isEnabled = true
        binding.speakerCardView.isEnabled = true
        binding.desktopCardView.isEnabled = true
    }

    private fun changeProductActivity(products: Products) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        val gson = Gson()
        val productJson = gson.toJson(products)
        intent.putExtra("product", productJson)
        startActivity(intent)
    }

    // 타이틀 변경
    override fun onResume() {
        super.onResume()
        super.showActionBar()
        super.setActionBarTitle("카테고리로 조회")
    }

    override fun onGetCategoryLoading() {
        // 로딩 이미지
    }

    override fun onGetCategorySuccess(Code: Int, result: List<CategoryResult>) {
        initRecyclerView(result)
    }

    override fun onGetCategoryFailure(Code: Int, message: String) {
        // 로딩 실패
    }
}