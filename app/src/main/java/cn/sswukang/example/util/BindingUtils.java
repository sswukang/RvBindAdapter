package cn.sswukang.example.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import cn.sswukang.example.model.Conversion;
import cn.sswukang.library.lib.sticky_header.sticky.StickyRecyclerHeadersDecoration;

/**
 * DataBinding工具类
 *
 * @author sswukang on 2017/3/1 11:08
 * @version 1.0
 */
public class BindingUtils {
    public static String getShowCode(int countryCode) {
        return "+" + String.valueOf(countryCode);
    }

    public static String getShowName(String nameCn, String nameEn) {
        return nameCn + "(" + nameEn + ")";
    }

    public static <T> Conversion<T> getConversion(T data) {
        return new Conversion<>(data);
    }


    /* ----------------------------- BindingAdapter ----------------------------- */

    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager viewPager, int index) {
        viewPager.setCurrentItem(index, false);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(ViewPager viewPager, FragmentPagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    @BindingAdapter("itemDecoration")
    public static void setItemDecoration(RecyclerView view, RecyclerView.ItemDecoration decoration) {
        view.addItemDecoration(decoration);
    }

    @BindingAdapter("stickyDecoration")
    public static void setStickyDecoration(RecyclerView view, StickyRecyclerHeadersDecoration decoration) {
        view.addItemDecoration(decoration);
    }


    /* ----------------------------- BindingConversion ----------------------------- */

    @BindingConversion
    public static ColorDrawable convertToBgColor(Conversion<Integer> countryId) {
        return new ColorDrawable(Color.parseColor(countryId.getData() % 2 == 0 ? "#f4237a" : "#1296db"));
    }

    @BindingConversion
    @ColorInt
    public static int convertToTextColor(Conversion<Integer> countryId) {
        return Color.parseColor(countryId.getData() % 2 == 0 ? "#f4237a" : "#1296db");
    }
}
