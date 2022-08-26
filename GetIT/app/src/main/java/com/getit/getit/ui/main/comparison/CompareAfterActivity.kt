package com.getit.getit.ui.main.comparison

import android.util.Log
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.getit.getit.R
import com.getit.getit.databinding.ActivityComparisonAfterBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.ui.main.comparison.data.CompareAnswer
import com.getit.getit.ui.main.comparison.server.ComparisonResultListResult
import com.getit.getit.ui.main.comparison.server.ComparisonResultService
import com.getit.getit.utils.LoadingDialog

class CompareAfterActivity  : BaseActivity<ActivityComparisonAfterBinding>(ActivityComparisonAfterBinding::inflate), ComparisonAfterView{

    var type : String? = ""
    lateinit var loadingDialog : LoadingDialog

    override fun initAfterBinding() {
        val toolbar : Toolbar = binding.toolbar
        toolbar.title = "비교 결과"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_left);

        type = intent.getStringExtra("kind");
        val product1 = intent.getStringExtra("product1").toString();
        val product2 = intent.getStringExtra("product2").toString();

        Log.d("테 productIdx1", product1)
        Log.d("테 productIdx2", product2)
        type?.let { Log.d("테 type", it) }

        loadingDialog = LoadingDialog(this)
        loadingDialog.show()
        var comparisonResultService = ComparisonResultService()
        comparisonResultService.setComparisonAfterView(this)
        product1?.let { product2?.let { it1 ->
            comparisonResultService.loadComparisonResult(it,
                it1
            )
        } }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setResultProducts(compareAnswers : ArrayList<CompareAnswer>) {

        val compareResultRVAdapter = CompareResultRVAdapter(compareAnswers)
        binding.compareResultRv.adapter = compareResultRVAdapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.compareResultRv.layoutManager = layoutManager
    }

    override fun loadComparisonResult(code: Int, result: List<ComparisonResultListResult>) {
        Glide.with(this).load(result[0].imgurl[0]!!).into(binding.compareElement1Iv)
        Glide.with(this).load(result[1].imgurl[0]!!).into(binding.compareElement2Iv)
        binding.compareElement1Tv.text = result[0].name
        binding.compareElement2Tv.text = result[1].name
        var compareAnswers = setCompareAnswersByType(result)
        setResultProducts(compareAnswers)
        loadingDialog.hide()

        //binding.compareResultProduct1Btn -> result[0].productIdx
        //binding.compareResultProduct2Btn -> result[1].productIdx
        //여기에 바로 상세 뷰 띄워주면 됩니다
    }

    private fun setCompareAnswersByType(result: List<ComparisonResultListResult>) : ArrayList<CompareAnswer>{
        var compareAnswers = ArrayList<CompareAnswer>()
        when(type){
            getString(R.string.phone) ->{
                compareAnswers.add(CompareAnswer(getString(R.string.date), result[0].date, result[1].date))
                compareAnswers.add(CompareAnswer(getString(R.string.price), result[0].price, result[1].price))
                compareAnswers.add(CompareAnswer(getString(R.string.brand), result[0].brand, result[1].brand))
                compareAnswers.add(CompareAnswer(getString(R.string.size), result[0].size, result[1].size))
                compareAnswers.add(CompareAnswer(getString(R.string.weight), result[0].weight, result[1].weight))
                compareAnswers.add(CompareAnswer(getString(R.string.communication), result[0].communication, result[1].communication))
                compareAnswers.add(CompareAnswer(getString(R.string.innermemory), result[0].innermemory, result[1].innermemory))
                compareAnswers.add(CompareAnswer(getString(R.string.os), result[0].os, result[1].os))
                compareAnswers.add(CompareAnswer(getString(R.string.cpu), result[0].cpu, result[1].cpu))
                compareAnswers.add(CompareAnswer(getString(R.string.ram), result[0].ram, result[1].ram))
                compareAnswers.add(CompareAnswer(getString(R.string.cpurate), result[0].cpurate, result[1].cpurate))
                compareAnswers.add(CompareAnswer(getString(R.string.core), result[0].core, result[1].core))
                return compareAnswers
            }
            getString(R.string.desktop) ->{
                compareAnswers.add(CompareAnswer(getString(R.string.date), result[0].date, result[1].date))
                compareAnswers.add(CompareAnswer(getString(R.string.price), result[0].price, result[1].price))
                compareAnswers.add(CompareAnswer(getString(R.string.brand), result[0].brand, result[1].brand))
                compareAnswers.add(CompareAnswer(getString(R.string.cpu), result[0].cpu, result[1].cpu))
                compareAnswers.add(CompareAnswer(getString(R.string.ram), result[0].ram, result[1].ram))
                compareAnswers.add(CompareAnswer(getString(R.string.cpurate), result[0].cpurate, result[1].cpurate))
                compareAnswers.add(CompareAnswer(getString(R.string.core), result[0].core, result[1].core))
                compareAnswers.add(CompareAnswer(getString(R.string.size), result[0].size, result[1].size))
                compareAnswers.add(CompareAnswer(getString(R.string.hdd), result[0].hdd, result[1].hdd))
                compareAnswers.add(CompareAnswer(getString(R.string.ssd), result[0].ssd, result[1].ssd))
                compareAnswers.add(CompareAnswer(getString(R.string.innermemory), result[0].innermemory, result[1].innermemory))
                return compareAnswers
            }
            getString(R.string.laptop) ->{
                compareAnswers.add(CompareAnswer(getString(R.string.date), result[0].date, result[1].date))
                compareAnswers.add(CompareAnswer(getString(R.string.price), result[0].price, result[1].price))
                compareAnswers.add(CompareAnswer(getString(R.string.brand), result[0].brand, result[1].brand))
                compareAnswers.add(CompareAnswer(getString(R.string.size), result[0].size, result[1].size))
                compareAnswers.add(CompareAnswer(getString(R.string.weight), result[0].weight, result[1].weight))
                compareAnswers.add(CompareAnswer(getString(R.string.cpu), result[0].cpu, result[1].cpu))
                compareAnswers.add(CompareAnswer(getString(R.string.core), result[0].core, result[1].core))
                compareAnswers.add(CompareAnswer(getString(R.string.ram), result[0].ram, result[1].ram))
                compareAnswers.add(CompareAnswer(getString(R.string.cpurate), result[0].cpurate, result[1].cpurate))
                compareAnswers.add(CompareAnswer(getString(R.string.innermemory), result[0].innermemory, result[1].innermemory))
                compareAnswers.add(CompareAnswer(getString(R.string.os), result[0].os, result[1].os))
                return compareAnswers

            }
            getString(R.string.speaker) ->{
                compareAnswers.add(CompareAnswer(getString(R.string.date), result[0].date, result[1].date))
                compareAnswers.add(CompareAnswer(getString(R.string.price), result[0].price, result[1].price))
                compareAnswers.add(CompareAnswer(getString(R.string.brand), result[0].brand, result[1].brand))
                compareAnswers.add(CompareAnswer(getString(R.string.output), result[0].output, result[1].output))
                compareAnswers.add(CompareAnswer(getString(R.string.terminal), result[0].terminal, result[1].terminal))
                compareAnswers.add(CompareAnswer(getString(R.string.weight), result[0].weight, result[1].weight))
                return compareAnswers

            }
            getString(R.string.tablet) ->{
                compareAnswers.add(CompareAnswer(getString(R.string.date), result[0].date, result[1].date))
                compareAnswers.add(CompareAnswer(getString(R.string.price), result[0].price, result[1].price))
                compareAnswers.add(CompareAnswer(getString(R.string.brand), result[0].brand, result[1].brand))
                compareAnswers.add(CompareAnswer(getString(R.string.size), result[0].size, result[1].size))
                compareAnswers.add(CompareAnswer(getString(R.string.weight), result[0].weight, result[1].weight))
                compareAnswers.add(CompareAnswer(getString(R.string.innermemory), result[0].innermemory, result[1].innermemory))
                compareAnswers.add(CompareAnswer(getString(R.string.communication), result[0].communication, result[1].communication))
                compareAnswers.add(CompareAnswer(getString(R.string.os), result[0].os, result[1].os))
                compareAnswers.add(CompareAnswer(getString(R.string.cpu), result[0].cpu, result[1].cpu))
                compareAnswers.add(CompareAnswer(getString(R.string.core), result[0].core, result[1].core))
                compareAnswers.add(CompareAnswer(getString(R.string.ram), result[0].ram, result[1].ram))
                compareAnswers.add(CompareAnswer(getString(R.string.cpurate), result[0].cpurate, result[1].cpurate))
                return compareAnswers

            }
        }
        return compareAnswers
    }
}