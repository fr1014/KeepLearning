package com.fr1014.keeplearning

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fr1014.keeplearning.databinding.ItemContentViewBinding

/**
 * Create by fanrui07
 * Date: 2022/3/24
 * Describe:
 */
class MainAdapter(private val tvContentList: ArrayList<String>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemContentViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.root.apply {
            text = tvContentList[position]
            setBackgroundColor(
                if (position % 2 == 0)
                    context.getColor(R.color.ffab00)
                else
                    context.getColor(R.color.ffd600)
            )
            setOnClickListener {
                val intent = Intent()
                intent.data = Uri.parse("keep_learning://www.fr1014.com:${position + 1}")
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = tvContentList.size

    class MainViewHolder(val binding: ItemContentViewBinding) : RecyclerView.ViewHolder(binding.root)
}