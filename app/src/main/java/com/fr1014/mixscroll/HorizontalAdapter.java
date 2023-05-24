package com.fr1014.mixscroll;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fr1014.keeplearning.R;

import java.util.List;

/**
 * Create by fanrui07
 * Date: 2023/5/19
 * Describe:
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {
    private List<String> data; // 假设数据是一个字符串列表

    public HorizontalAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new HorizontalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        String item = data.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class HorizontalViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
