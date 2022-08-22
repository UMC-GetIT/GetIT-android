package com.getit.getit.ui.main.mypage.like

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.getit.getit.databinding.ActivityMypageLikeListBinding
import com.getit.getit.ui.BaseActivity
import com.getit.getit.utils.ApplicationClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LikeProductActivity : BaseActivity<ActivityMypageLikeListBinding>(ActivityMypageLikeListBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener {
            backspace()
        }

        //데이터불러오는 함수
        loadLikeData()

    }

    private fun setAdapter(LikeList: List<result>){
        val mAdapter = LikeRVAdatper(LikeList,this)
        binding.userLikeRecyclerView.adapter = mAdapter
        binding.userLikeRecyclerView.layoutManager =  GridLayoutManager(this, 3)
        binding.userLikeRecyclerView.setHasFixedSize(false)
    }


    private fun loadLikeData(){
        val mypageLikeRetrofit = ApplicationClass.retrofit.create(LikeApiService::class.java)
        mypageLikeRetrofit.getLikeProducts().enqueue(object : Callback<LikeProducts> {


            override fun onResponse(call: Call<LikeProducts>, response: Response<LikeProducts>) {
                Log.d("테스트",response.body().toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        Log.d("테스트",response.body().toString())


                        if(body.result?.isEmpty()==true){
                            binding.userLikeNoProduct.setVisibility(View.VISIBLE)
                            binding.userLikeRecyclerView.setVisibility(View.INVISIBLE)
                        }

                        else{
                            binding.userLikeNoProduct.setVisibility(View.INVISIBLE)
                            binding.userLikeRecyclerView.setVisibility(View.VISIBLE)
                        }

                    }
                }
            }

            override fun onFailure(call: Call<LikeProducts>, t: Throwable) {
            }
        })
    }

    override fun initAfterBinding() {
    }
}

