package cn.sswukang.library.adapter.sticky;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import cn.sswukang.library.adapter.base.BaseBindViewHolder;
import cn.sswukang.library.adapter.single.SingleBindAdapter;
import cn.sswukang.library.lib.sticky_header.sticky.StickyRecyclerHeadersAdapter;


/**
 * 粘性头部适配器。(DataBinding模式)
 *
 * @param <T>  数据类型
 * @param <SB> 粘性头部布局绑定类
 * @param <B>  内容布局绑定类
 * @author sswukang on 2017/2/23 18:43
 * @version 1.0
 */
public abstract class StickyHeaderBindAdapter<T, SB extends ViewDataBinding, B extends ViewDataBinding>
        extends SingleBindAdapter<T, B> implements StickyRecyclerHeadersAdapter<BaseBindViewHolder<SB>> {

    // sticky header res id;
    @LayoutRes
    private int headerLayoutId;
    // sticky header layout height;
    private int headerHeight;

    /**
     * @param headerLayoutId header需要的布局资源id
     * @param layoutId       content需要的布局资源id
     * @param data           数据
     */
    public StickyHeaderBindAdapter(@LayoutRes int headerLayoutId, @LayoutRes int layoutId, List<T> data) {
        super(layoutId, data);
        this.headerLayoutId = headerLayoutId;
    }

    @Override
    public final long getHeaderId(int position) {
        return getHeaderId(getItem(position), position);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final BaseBindViewHolder<SB> onCreateHeaderViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SB binding = DataBindingUtil.inflate(inflater, headerLayoutId, parent, false);
        this.headerHeight = binding.getRoot().getLayoutParams().height;
        return BaseBindViewHolder.get(binding, headerLayoutId, this);
    }

    @Override
    public final void onBindHeaderViewHolder(BaseBindViewHolder<SB> holder, int position) {
        SB binding = holder.getBinding();
        convertHeader(getItem(position), binding, position);
        binding.executePendingBindings();
    }

    /**
     * 开放粘性头部高度，方便 recycler view 滚动。
     *
     * @return sticky header height
     */
    public int getHeaderHeight() {
        return headerHeight;
    }

    /**
     * 获得 header id 。如果某几个条目有相同的header，其id 需相同。
     * 如某条目不需要header，则return < 0 即可。
     * 例：字符串可以用 String.charAt(0)
     *
     * @param t        每个 position 对应的封装
     * @param position 当前行数
     * @return header id {@link StickyRecyclerHeadersAdapter#getHeaderId(int)}
     */
    public abstract long getHeaderId(T t, int position);

    /**
     * 填充粘性头部显示的内容
     *
     * @param t        header 对象数据封装
     * @param binding  {@link SB}
     * @param position header 条目下标
     */
    public abstract void convertHeader(T t, SB binding, int position);
}
