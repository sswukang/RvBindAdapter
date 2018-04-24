package cn.sswukang.example.base;

import android.databinding.ViewDataBinding;

/**
 * ViewModel Fragment 基类
 *
 * @author sswukang on 2017/2/22 16:05
 * @version 1.0
 */
public class BaseFragmentViewModel<F extends BaseFragment, B extends ViewDataBinding> {

    // fragment
    private F mFragment;
    // 视图绑定对象
    private B mDataBinding;

    @SuppressWarnings("unchecked")
    public final void setView(F fragment) {
        mFragment = fragment;
        mDataBinding = (B) fragment.getDataBinding();
    }

    public final F getFragment() {
        return mFragment;
    }

    public final B getDataBinding() {
        return mDataBinding;
    }

    public void initViewModel() {
        // do something...
    }

    public void releaseViewModel() {
        // do something...
    }

    public void asc() {
        // do something...
    }

    public void desc() {
        // do something...
    }

    public void shuffle() {
        // do something...
    }
}
