package cn.sswukang.library.adapter.multi;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

import cn.sswukang.library.adapter.base.BaseBindAdapter;
import cn.sswukang.library.adapter.base.BaseBindViewHolder;


/**
 * multi Item Adapter。(DataBinding模式)
 *
 * @param <T> 数据类型
 * @author sswukang on 2017/2/23 18:35
 * @version 1.0
 */
public abstract class MultiBindAdapter<T>
        extends BaseBindAdapter<T, ViewDataBinding, BaseBindViewHolder<ViewDataBinding>> {

    /**
     * @param data 数据
     */
    protected MultiBindAdapter(List<T> data) {
        super(-1, data);
    }

    /**
     * 利用getItemViewType传递layout id
     *
     * @param position 当前item的position（无限轮播时会超过数据总个数）
     * @return layout id
     */
    @LayoutRes
    @Override
    public int getItemViewType(int position) {
        return getItemLayoutId(position, getDataItem(position));
    }

    @Override
    public final void convert(int position, T t, ViewDataBinding binding, BaseBindViewHolder<ViewDataBinding> holder) {
        convert(position, t, binding, holder.getLayoutId());
    }

    @Override
    public final void onItemClick(View itemView, int position, @LayoutRes int layoutId) {
        onItemClick(itemView, position, getDataItem(position), layoutId);
    }

    @Override
    public final boolean onItemLongClick(View itemView, int position, @LayoutRes int layoutId) {
        return onItemLongClick(itemView, position, getDataItem(position), layoutId);
    }

    /**
     * 实现该抽象方法，得到单个item的layout id。
     *
     * @param position 当前item的position（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @return layout id
     */
    public abstract int getItemLayoutId(int position, T t);

    /**
     * 实现该抽象方法，完成数据的填充。
     *
     * @param position 当前item的position（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @param binding  {@link ViewDataBinding}
     * @param layoutId 布局id (用于区别不同item)
     */
    public abstract void convert(int position, T t, ViewDataBinding binding, @LayoutRes int layoutId);

    /**
     * item的单击事件
     *
     * @param itemView 触发点击事件的View
     * @param position 当前点击的position，采用{@link BaseBindViewHolder#getLayoutPosition()}（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @param layoutId 布局id (用于区别不同item)
     */
    public void onItemClick(View itemView, int position, T t, @LayoutRes int layoutId) {
        // do something...
    }

    /**
     * item的长按事件
     *
     * @param itemView 触发点击事件的View
     * @param position 当前点击的position，采用{@link BaseBindViewHolder#getLayoutPosition()}（无限轮播时会超过数据总个数）
     * @param t        position 对应的对象（无限轮播时为对数据总个数取余后对应的对象）
     * @param layoutId 布局id (用于区别不同item)
     * @return 长按事件是否被消费
     */
    public boolean onItemLongClick(View itemView, int position, T t, @LayoutRes int layoutId) {
        return false;
    }

    /**
     * 得到每个item的ViewDataBinding(注：一定要是item xml对应的ViewDataBinding)
     *
     * @param binding {@link ViewDataBinding}
     * @param <B>     {@link B}
     * @return {@link B};
     */
    @SuppressWarnings("unchecked")
    protected <B extends ViewDataBinding> B getItemBinding(ViewDataBinding binding) {
        return (B) binding;
    }
}
