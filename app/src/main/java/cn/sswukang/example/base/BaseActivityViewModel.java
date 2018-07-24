package cn.sswukang.example.base;

import android.databinding.ViewDataBinding;

/**
 * Activity ViewModel 基类
 *
 * @author sswukang on 2017/2/22 15:55
 * @version 1.0
 */
public class BaseActivityViewModel<A extends BaseActivity<B, ?>, B extends ViewDataBinding> {
    // Activity
    private A mActivity;
    // 视图绑定对象
    private B mDataBinding;

    public final void setView(A activity) {
        mActivity = activity;
        mDataBinding = activity.getDataBinding();
    }

    public final A getActivity() {
        return mActivity;
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
}
