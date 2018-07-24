package cn.sswukang.library.adapter.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.sswukang.library.listener.RecyclerClickListener;

/**
 * RecyclerView基础Adapter。(DataBinding模式)
 *
 * @param <T> 数据类型
 * @param <B> 内容布局绑定类
 * @param <H> ViewHold类
 * @author sswukang on 2017/2/23 18:13
 * @version 1.0
 */
public abstract class BaseBindAdapter<T, B extends ViewDataBinding, H extends BaseBindViewHolder<B>>
        extends RecyclerView.Adapter<H> implements RecyclerClickListener {

    private Context context;
    @LayoutRes
    private int layoutId;
    private List<T> data;

    /**
     * @param layoutId adapter需要的布局资源id
     * @param data     数据
     */
    protected BaseBindAdapter(@LayoutRes int layoutId, List<T> data) {
        this.layoutId = layoutId;
        this.data = data;
        setHasStableIds(true);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * @return 获得item数据总个数
     */
    protected final int getDataSize() {
        List<T> data = getData();
        if (data != null)
            return data.size();
        return 0;
    }

    /**
     * @param position item下标
     * @return 获得item数据封装
     */
    @Nullable
    protected final T getDataItem(int position) {
        List<T> data = getData();
        if (data != null && data.size() > 0) {
            if (position >= data.size()) {
                position = position % data.size();
            }
            return data.get(position);
        }
        return null;
    }

    // 设置ID，保证item操作不错乱
    @Override
    public long getItemId(int position) {
        T t = getDataItem(position);
        if (t != null)
            return t.hashCode();
        else
            return super.getItemId(position);
    }

    /**
     * @return 设置item总个数（一般为数据总个数，设置成{@link Integer#MAX_VALUE}可实现无限轮播）
     */
    @Override
    public int getItemCount() {
        return getDataSize();
    }

    /**
     * 利用getItemViewType传递layout id
     *
     * @param position 当前行数
     * @return layout id
     */
    @LayoutRes
    @Override
    public int getItemViewType(int position) {
        return layoutId;
    }

    // 创建hold
    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, @LayoutRes int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        B binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return (H) H.get(binding, viewType, this);
    }

    // 绑定hold
    @Override
    public void onBindViewHolder(@NonNull H holder, int position) {
        context = holder.getContext();
        B binding = holder.getBinding();
        convert(position, getDataItem(position), binding, holder);
        binding.executePendingBindings();
    }

    /**
     * 实现该抽象方法，完成数据的绑定。
     *
     * @param position 当前item的position（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @param binding  {@link B}
     * @param holder   {@link H}
     */
    public abstract void convert(int position, @Nullable T t, B binding, H holder);

    /**
     * 单击事件
     *
     * @param itemView 点击的item {@link H#itemView}
     * @param position 当前点击的position，采用{@link H#getLayoutPosition()}（无限轮播时会超过数据总个数）
     * @param layoutId item布局id{@link H#getLayoutId()}
     */
    @Override
    public void onItemClick(View itemView, int position, @LayoutRes int layoutId) {
    }

    /**
     * 长按事件
     *
     * @param itemView 点击的item {@link H#itemView}
     * @param position 当前点击的position，采用{@link H#getLayoutPosition()}（无限轮播时会超过数据总个数）
     * @param layoutId item布局id{@link H#getLayoutId()}
     * @return 是否消费事件
     */
    @Override
    public boolean onItemLongClick(View itemView, int position, @LayoutRes int layoutId) {
        return false;
    }

    /**
     * 得到ItemView依赖的Context
     *
     * @return {@link View#getContext()}
     */
    public Context getContext() {
        return context;
    }
}
