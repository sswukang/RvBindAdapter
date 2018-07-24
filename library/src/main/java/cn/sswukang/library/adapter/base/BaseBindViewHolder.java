package cn.sswukang.library.adapter.base;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.sswukang.library.listener.DebouncingOnClickListener;
import cn.sswukang.library.listener.RecyclerClickListener;


/**
 * 自定义 RecyclerView 的 ViewHolder (DataBinding模式)
 *
 * @author sswukang on 2017/2/23 18:08
 * @version 1.0
 */
public class BaseBindViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private B binding;
    @LayoutRes
    private int layoutId;

    protected BaseBindViewHolder(B binding, @LayoutRes int layoutId, RecyclerClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.layoutId = layoutId;

        //添加监听事件
        itemView.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                listener.onItemClick(v, getLayoutPosition(), getLayoutId());
            }
        });
        itemView.setOnLongClickListener(v -> listener.onItemLongClick(v, getLayoutPosition(), getLayoutId()));
    }

    /**
     * 自定义ViewHolder创建方法
     *
     * @param binding  {@link B}
     * @param layoutId 该条目的layout id，可用于多条目的区分
     * @param listener 条目的监听
     * @return {@link BaseBindViewHolder}
     */
    public static <B extends ViewDataBinding> BaseBindViewHolder<B> get(B binding, @LayoutRes int layoutId, RecyclerClickListener listener) {
        return new BaseBindViewHolder<>(binding, layoutId, listener);
    }

    /**
     * @return {@link B}
     */
    public B getBinding() {
        return binding;
    }

    /**
     * 获得context，建议布局里使用用此方法得到context。
     *
     * @return {@link Context}
     */
    public Context getContext() {
        return itemView.getContext();
    }

    /**
     * 获得item布局资源id（可用于multi adapter里区别不同item）
     *
     * @return item view res id
     */
    @LayoutRes
    public int getLayoutId() {
        return layoutId;
    }

}
