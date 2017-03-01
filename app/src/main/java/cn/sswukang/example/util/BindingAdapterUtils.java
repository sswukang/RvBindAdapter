package cn.sswukang.example.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

/**
 * DataBinding绑定注解，系统自动调用。
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

    @BindingAdapter("pagerAdapter")
    public static void setPagerAdapter(ViewPager viewPager, FragmentPagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingConversion
    public static ColorDrawable convertToBgColor(int countryId) {
        return new ColorDrawable(Color.parseColor(countryId % 2 == 0 ? "#f4237a" : "#1296db"));
    }
}
