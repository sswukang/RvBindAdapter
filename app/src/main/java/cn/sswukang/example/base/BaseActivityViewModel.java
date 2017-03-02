package cn.sswukang.example.base;

import android.databinding.ViewDataBinding;

/**
 * ViewModel Activity 基类
 *
 * @author sswukang on 2017/2/22 15:55
 * @version 1.0
 */
public class BaseActivityViewModel<A extends BaseActivity, B extends ViewDataBinding> {

    // Activity
    private A mActivity;
    // 视图绑定对象
    private B mDataBinding;

    @SuppressWarnings("unchecked")
    public final void setView(BaseActivity activity) {
        mActivity = (A) activity;
        mDataBinding = (B) activity.getDataBinding();
    }

    public final A getActivity() {
        return mActivity;
    }

    public final B getDataBinding() {
        return mDataBinding;
    }

}
