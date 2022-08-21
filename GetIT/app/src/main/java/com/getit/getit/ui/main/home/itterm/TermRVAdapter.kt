package com.getit.getit.ui.main.home.itterm

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.getit.getit.databinding.ItemIttermBinding
import com.getit.getit.ui.main.home.data.ItTermIcon
import com.getit.getit.ui.main.mypage.settings.ChangeProfileActivity

class TermRVAdapter (private val ittermList: ArrayList<ItTermIcon>, activity: Activity) : RecyclerView.Adapter<TermRVAdapter.ViewHolder>() {


    val activity: Activity = activity
    inner class ViewHolder(val binding: ItemIttermBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itterm: ItTermIcon) {
            binding.ittermAnswerTv.text = itterm.name
            binding.ittermAnswerIcon.setImageResource(itterm.icon!!)
            binding.ittermAnswerIcon.setOnClickListener{
                val intent = Intent(activity, ItTermWindowActivity::class.java)
                intent.putExtra("itTermKind", itterm.name)
                activity.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemIttermBinding = ItemIttermBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ittermList[position])
    }

    override fun getItemCount(): Int = ittermList.size

}