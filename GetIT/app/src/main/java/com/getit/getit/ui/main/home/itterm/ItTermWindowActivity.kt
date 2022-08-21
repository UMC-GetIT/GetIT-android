package com.getit.getit.ui.main.home.itterm

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.getit.getit.R
import com.getit.getit.databinding.ActivityIttermWindowBinding

class ItTermWindowActivity : AppCompatActivity() {

    var itTermKind : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityIttermWindowBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        itTermKind = intent.getStringExtra("itTermKind").toString()
        var content = getItTermExplainContent(itTermKind,this)

        content["image"]?.let { binding.explainImage.setImageResource(it.toInt()) }
        binding.explainTitleTv.text = content["title"]
        binding.explainContentTv.text = content["content"]

        val toolbar : Toolbar = binding.toolbar
        toolbar.title = "상세 정보"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}