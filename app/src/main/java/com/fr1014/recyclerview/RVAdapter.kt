package com.fr1014.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fr1014.keeplearning.databinding.ItemHoverCommonBinding

/**
 * Create by fanrui07
 * Date: 2022/5/16
 * Describe:
 */
class RVAdapter : RecyclerView.Adapter<RVAdapter.RVViewHolder>() {

    class RVViewHolder(binding: ItemHoverCommonBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val binding = ItemHoverCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 20
}