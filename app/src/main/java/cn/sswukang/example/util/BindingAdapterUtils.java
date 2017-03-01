package cn.sswukang.example.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * BindingAdapter绑定声明，系统匹配成功自动调用
 *
 * @author sswukang on 2017/3/1 10:54
 * @version 1.0
 */
public class BindingAdapterUtils {
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }
}
