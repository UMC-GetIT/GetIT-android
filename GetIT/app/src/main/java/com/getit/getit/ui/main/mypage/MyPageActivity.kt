package com.getit.getit.ui.main.mypage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.getit.getit.R
import com.getit.getit.databinding.FragmentMypage2Binding
import com.getit.getit.databinding.FragmentProductDetailBinding
import com.getit.getit.utils.BASE_URL
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageActivity : AppCompatActivity() {
    lateinit var binding: FragmentMypage2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentMypage2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
/*
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {
            Log.d("Response",it.userIdx.toString())
            Log.d("Response", it.name.toString())
            Log.d("Response",it.nickname.toString())

            //id값을 통해 접근
            val name : TextView = findViewById(R.id.name)
            name.text = "제니퍼"
            val nickname : TextView = findViewById(R.id.nickname)
            nickname.text="jennifer"
        })


    }*/
   /* private lateinit var binding: FragmentMypage2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypage2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Clean TextViews
        binding.name.text = ""
        binding.nickname.text = ""

        parseJSON()
    }


    private fun parseJSON() {

        // .addConverterFactory(GsonConverterFactory.create()) for Gson converter
        // .addConverterFactory(MoshiConverterFactory.create()) for Moshi converter
        // .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) for Kotlinx Serialization converter
        // .addConverterFactory(JacksonConverterFactory.create()) for Jackson converter

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            //.baseUrl("http://14.34.174.239")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getPerson()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    /* Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(response.body())
                    Log.d("Pretty Printed JSON :", prettyJson)
                    binding.jsonResultsTextview.text = prettyJson*/
                /*
                    // ID
                    val nameid = response.body()?.name ?: "N/A"
                    Log.d("이름: ", nameid)
                    binding.name.text = nameid

                    // Employee Name
                    val nickNameid = response.body()?.nickname ?: "N/A"
                    Log.d("아이디: ", nickNameid)
                    binding.nickname.text = nickNameid
                    */
                    Log.d("response","Success : ${response.body()}")

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }*/
