package cn.sswukang.library.adapter.itemtouch;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import cn.sswukang.library.adapter.base.BaseBindAdapter;
import cn.sswukang.library.adapter.base.BaseBindViewHolder;


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

    @SuppressWarnings("unchecked")
    @Override
    public final ItemTouchBindViewHolder<B> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        B binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return ItemTouchBindViewHolder.get(binding, viewType, this, this);
    }

    @Override
    public final void convert(T t, B binding, ItemTouchBindViewHolder<B> holder) {
        convert(t, binding);
    }

    @Override
    public final void onItemClick(View itemView, int position, @LayoutRes int layoutId) {
        onItemClick(itemView, getItem(position), position);
    }

    @Override
    public final boolean onItemLongClick(View itemView, int position, @LayoutRes int layoutId) {
        return onItemLongClick(itemView, getItem(position), position);
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
     * @param t       每个 position 对应的封装
     * @param binding {@link B}
     */
    public abstract void convert(T t, B binding);

    /**
     * item的单击事件
     *
     * @param itemView 点击的item {@link ItemTouchBindViewHolder#itemView}
     * @param t        每个 position 对应的封装
     * @param position 当前行数，采用{@link BaseBindViewHolder#getLayoutPosition()}
     */
    public void onItemClick(View itemView, T t, int position) {
        // do something...
    }

    /**
     * item的长按事件
     *
     * @param itemView 点击的item {@link ItemTouchBindViewHolder#itemView}
     * @param t        每个 position 对应的封装
     * @param position 当前行数，采用{@link BaseBindViewHolder#getLayoutPosition()}
     * @return 长按事件是否被消费
     */
    public boolean onItemLongClick(View itemView, T t, int position) {
        return false;
    }

    public void onItemPressed(View itemView) {
        itemView.setBackgroundColor(Color.GRAY);
    }


    public void onItemClear(View itemView) {
        itemView.setBackgroundColor(Color.TRANSPARENT);
    }
}
