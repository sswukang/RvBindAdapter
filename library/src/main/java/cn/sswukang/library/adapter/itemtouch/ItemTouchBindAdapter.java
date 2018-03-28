package cn.sswukang.library.adapter.itemtouch;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import cn.sswukang.library.adapter.base.BaseBindAdapter;


/**
 * ItemTouch Adapter Adapter (DataBinding模式)
 *
 * @param <T> 数据类型
 * @param <B> 内容布局绑定类
 * @author sswukang on 2017/2/24 14:16
 * @version 1.0
 */
public abstract class ItemTouchBindAdapter<T, B extends ViewDataBinding>
        extends BaseBindAdapter<T, B, ItemTouchBindViewHolder<B>>
        implements ItemTouchCallBack.OnMoveSwipeListener, ItemTouchBindViewHolder.ItemViewStateChangeListener {
    /**
     * @param layoutId adapter需要的布局资源id
     * @param data     数据
     */
    protected ItemTouchBindAdapter(@LayoutRes int layoutId, List<T> data) {
        super(layoutId, data);
    }

    /**
     * @return 设置item总个数（不允许设置无限轮播）
     */
    @Override
    public final int getItemCount() {
        return super.getItemCount();
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public final ItemTouchBindViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        B binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return ItemTouchBindViewHolder.get(binding, viewType, this, this);
    }

    @Override
    public final void convert(int position, T t, B binding, ItemTouchBindViewHolder<B> holder) {
        convert(position, t, binding);
    }

    @Override
    public final void onItemClick(View itemView, int position, @LayoutRes int layoutId) {
        onItemClick(itemView, position, getDataItem(position));
    }

    @Override
    public final boolean onItemLongClick(View itemView, int position, @LayoutRes int layoutId) {
        return onItemLongClick(itemView, position, getDataItem(position));
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换数据源位置
        Collections.swap(getData(), fromPosition, toPosition);
        //交换列表中数据位置
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        //删除数据源中对应数据
        getData().remove(position);
        //删除列表中对应位置
        notifyItemRemoved(position);
    }

    /**
     * 实现该抽象方法，完成数据的绑定。
     *
     * @param position 当前item的position
     * @param t        position 对应的对象
     * @param binding  {@link B}
     */
    public abstract void convert(int position, T t, B binding);

    /**
     * item的单击事件
     *
     * @param itemView 点击的item {@link ItemTouchBindViewHolder#itemView}
     * @param position 当前点击的position，采用{@link ItemTouchBindViewHolder#getLayoutPosition()}
     * @param t        position 对应的对象
     */
    public void onItemClick(View itemView, int position, T t) {
        // do something...
    }

    /**
     * item的长按事件
     *
     * @param itemView 点击的item {@link ItemTouchBindViewHolder#itemView}
     * @param position 当前点击的position，采用{@link ItemTouchBindViewHolder#getLayoutPosition()}
     * @param t        position 对应的对象
     * @return 长按事件是否被消费
     */
    public boolean onItemLongClick(View itemView, int position, T t) {
        return false;
    }

    public void onItemPressed(View itemView) {
        itemView.setBackgroundColor(Color.GRAY);
    }

    public void onItemClear(View itemView) {
        itemView.setBackgroundColor(Color.TRANSPARENT);
    }
}
