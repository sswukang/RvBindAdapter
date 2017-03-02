package cn.sswukang.example.base;

import android.databinding.ViewDataBinding;

/**
 * ViewModel Fragment 基类
 *
 * @author sswukang on 2017/2/22 16:05
 * @version 1.0
 */
public abstract class BaseFragmentViewModel<F extends BaseFragment, B extends ViewDataBinding> {

    // fragment
    private F mFragment;
    // 视图绑定对象
    private B mDataBinding;

    @SuppressWarnings("unchecked")
    public final void setView(BaseFragment fragment) {
        mFragment = (F) fragment;
        mDataBinding = (B) fragment.getDataBinding();
    }

    public final F getFragment() {
        return mFragment;
    }

    public final B getDataBinding() {
        return mDataBinding;
    }

    public abstract void asc();

    public abstract void desc();

    public abstract void shuffle();
}
