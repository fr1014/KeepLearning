package com.fr1014.mixscroll;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fr1014.keeplearning.R;

import java.util.List;

/**
 * Create by fanrui07
 * Date: 2023/5/19
 * Describe:
 */
public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder> {
    // 创建RecyclerViewSyncManager实例
    private final RecyclerViewSyncManager syncManager;
    private final List<List<String>> data; // 假设数据是一个二维数据列表

    public VerticalAdapter(List<List<String>> data) {
        this.data = data;
        this.syncManager = new RecyclerViewSyncManager();
    }

    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        VerticalViewHolder holder = new VerticalViewHolder(itemView);
        syncManager.addRecyclerView(holder.horizontalRecyclerView);
        Log.d("hello", "onCreateViewHolder: ");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, int position) {
        Log.d("hello", "onBindViewHolder: ");
        List<String> horizontalData = data.get(position);
        holder.setupHorizontalRecyclerView(horizontalData);
        RecyclerView.LayoutManager layoutManager = holder.horizontalRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            ((LinearLayoutManager) layoutManager)
                    .scrollToPositionWithOffset(syncManager.firstVisibleItemPosition, syncManager.offset);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class VerticalViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView horizontalRecyclerView;

        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);
            horizontalRecyclerView = itemView.findViewById(R.id.horizontalRecyclerView);
        }

        public void setupHorizontalRecyclerView(List<String> data) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            horizontalRecyclerView.setLayoutManager(layoutManager);

            HorizontalAdapter adapter = new HorizontalAdapter(data);
            horizontalRecyclerView.setAdapter(adapter);
        }
    }

}
