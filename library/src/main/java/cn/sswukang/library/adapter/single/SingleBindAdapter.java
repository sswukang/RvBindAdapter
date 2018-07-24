package cn.sswukang.library.adapter.single;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import cn.sswukang.library.adapter.base.BaseBindAdapter;
import cn.sswukang.library.adapter.base.BaseBindViewHolder;


/**
 * single item Adapter (DataBinding模式)
 *
 * @param <T> 数据类型
 * @param <B> 内容布局绑定类
 * @author sswukang on 2017/2/23 18:17
 * @version 1.0
 */
public abstract class SingleBindAdapter<T, B extends ViewDataBinding>
        extends BaseBindAdapter<T, B, BaseBindViewHolder<B>> {
    /**
     * @param layoutId adapter需要的布局资源id
     * @param data     数据
     */
    protected SingleBindAdapter(@LayoutRes int layoutId, List<T> data) {
        super(layoutId, data);
    }

    @Override
    public final void convert(int position, @Nullable T t, B binding, BaseBindViewHolder<B> holder) {
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

    /**
     * 实现该抽象方法，完成数据的绑定。
     *
     * @param position 当前item的position（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @param binding  {@link B}
     */
    public abstract void convert(int position, @Nullable T t, B binding);

    /**
     * item的单击事件
     *
     * @param itemView 点击的item {@link BaseBindViewHolder#itemView}
     * @param position 当前点击的position，采用{@link BaseBindViewHolder#getLayoutPosition()}（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     */
    public void onItemClick(View itemView, int position, @Nullable T t) {
        // do something...
    }

    /**
     * item的长按事件
     *
     * @param itemView 点击的item {@link BaseBindViewHolder#itemView}
     * @param position 当前点击的position，采用{@link BaseBindViewHolder#getLayoutPosition()}（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @return 长按事件是否被消费
     */
    public boolean onItemLongClick(View itemView, int position, @Nullable T t) {
        return false;
    }
}
