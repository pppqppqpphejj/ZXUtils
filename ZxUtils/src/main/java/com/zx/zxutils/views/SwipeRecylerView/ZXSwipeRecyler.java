package com.zx.zxutils.views.SwipeRecylerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.zx.zxutils.R;
import com.zx.zxutils.other.ZXItemClickSupport;


/**
 * Created by Xiangb on 2017/6/13.
 * 功能：
 */

public class ZXSwipeRecyler extends LinearLayout {
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ZXSRListener zxsrListener;
    private ZXRecycleAdapter adapter;
    private int pageNum = 1, totalNum = 0, pageSize = 10;

    public ZXSwipeRecyler(Context context) {
        super(context);
        init(context);
    }

    public ZXSwipeRecyler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        //在构造函数中将xml中定义的布局解析出来
        LayoutInflater.from(context).inflate(R.layout.view_swipe_recyler, this, true);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_normal_layout);
        recyclerView = (RecyclerView) findViewById(R.id.rv_normal_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        ZXItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ZXItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                if (zxsrListener != null && position < adapter.onItemList().size()) {
                    zxsrListener.onItemClick(adapter.onItemList().get(position), position);
                }
            }
        }).setOnItemLongClickListener(new ZXItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View view) {
                if (zxsrListener != null && position < adapter.onItemList().size()) {
                    zxsrListener.onItemLongClick(adapter.onItemList().get(position), position);
                }
                return true;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (zxsrListener != null) {
                    if (pageNum > 1) {
                        pageNum--;
                    }
                    zxsrListener.onRefresh();
                }
            }
        });
    }

    /**
     * 设置布局管理器
     */
    public ZXSwipeRecyler setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        return this;
    }

    //添加分隔线
    public ZXSwipeRecyler addDivider() {
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        return this;
    }

    /**
     * 设置适配器
     *
     * @param adapter
     * @return
     */
    public ZXSwipeRecyler setAdapter(final ZXRecycleAdapter adapter) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new LoadMoreListener() {
            @Override
            public void LoadMore() {
                if (zxsrListener != null) {
                    if (pageNum * 10 < totalNum) {
                        if (adapter != null && adapter.footerViewHolder != null) {
                            adapter.footerViewHolder.doLoading();
                        }
                        pageNum++;
                        zxsrListener.onLoadMore();
                    }
                }
            }
        });
        return this;
    }

    /**
     * 获取适配器
     *
     * @return
     */
    public ZXRecycleAdapter getAdapter() {
        return adapter;
    }

    /**
     * 获得recylerview对象
     *
     * @return
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * 获得swiperefresh对象
     *
     * @return
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    /**
     * 设置每页数量
     *
     * @param pageSize
     * @return
     */
    public ZXSwipeRecyler setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (adapter != null) {
            adapter.pageSize = pageSize;
        }
        return this;
    }

    /**
     * 设置相关监听
     *
     * @param zxsrListener
     * @return
     */
    public ZXSwipeRecyler setSRListener(ZXSRListener zxsrListener) {
        this.zxsrListener = zxsrListener;
        return this;
    }

    /**
     * 设置是否带有加载信息
     *
     * @param hasLoadInfo
     * @return
     */
    public ZXSwipeRecyler showLoadInfo(boolean hasLoadInfo) {
        if (adapter != null) {
            adapter.hasLoadMore = hasLoadInfo;
        }
        return this;
    }

    /**
     * 数据更新
     */
    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 关闭刷新状态
     */
    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 设置加载信息
     *
     * @param totalNum
     */
    public void setLoadInfo(int totalNum) {
        this.totalNum = totalNum;
        if (adapter != null && adapter.footerViewHolder != null) {
            adapter.footerViewHolder.setStatus(pageNum, totalNum);
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getPageSize() {
        return pageSize;
    }
}
