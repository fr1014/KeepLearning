package com.fr1014.mixscroll;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewSyncManager {
    private final List<RecyclerView> recyclerViews;
    private final RecyclerView.OnScrollListener scrollListener;
    public int firstVisibleItemPosition;
    public int offset;
    public RecyclerView currentScrollingRV;

    public RecyclerViewSyncManager() {
        recyclerViews = new ArrayList<>();
        scrollListener = new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    currentScrollingRV = recyclerView;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    return;
                }
                if (recyclerView != currentScrollingRV) {
                    recyclerView.stopScroll();
                }
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                if (manager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
                    // 获取第一个可见项的位置和偏移量
                    firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    View firstVisibleItem = linearLayoutManager.findViewByPosition(firstVisibleItemPosition);
                    offset = firstVisibleItem != null ? firstVisibleItem.getLeft() : 0;
                    // 更新所有其他RecyclerView的滑动位置
                    for (RecyclerView otherRecyclerView : recyclerViews) {
                        if (otherRecyclerView != recyclerView) {
                            RecyclerView.LayoutManager layoutManager = otherRecyclerView.getLayoutManager();
                            if (layoutManager instanceof LinearLayoutManager) {
                                ((LinearLayoutManager) layoutManager)
                                        .scrollToPositionWithOffset(firstVisibleItemPosition, offset);
                            }
                        }
                    }
                }
            }
        };
    }

    public void addRecyclerView(RecyclerView recyclerView) {
        recyclerViews.add(recyclerView);
        recyclerView.addOnScrollListener(scrollListener);
    }

}
