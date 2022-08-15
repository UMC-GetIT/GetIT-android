package com.getit.getit.ui.main.mypage

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    var service : Service

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://52.79.227.36")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(Service::class.java)
    }
}