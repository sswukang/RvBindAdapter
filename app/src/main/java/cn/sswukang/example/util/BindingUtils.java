package cn.sswukang.example.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import cn.sswukang.example.model.Conversion;
import cn.sswukang.library.adapter.sticky.StickyHeaderBindAdapter;
import cn.sswukang.library.lib.side.SideAndStickyHeaderRecyclerView;
import cn.sswukang.library.lib.sticky_header.sticky.StickyRecyclerHeadersDecoration;

/**
 * DataBinding工具类
 *
 * @author sswukang on 2017/3/1 11:08
 * @version 1.0
 */
public class BindingUtils {

    /**
     * 方便xml得到BindingConversion的辅助对象{@link Conversion<T>}
     */
    public static <T> Conversion<T> getConversion(T data) {
        return new Conversion<>(data);
    }

    /**
     * 为展示的区号数据增加“+”号
     */
    public static String getShowCode(int countryCode) {
        return "+" + String.valueOf(countryCode);
    }

    /**
     * 组合中英文名展示数据
     */
    public static String getShowName(String nameCn, String nameEn) {
        return nameCn + "(" + nameEn + ")";
    }


    /* ----------------------------- BindingAdapter ----------------------------- */

    /**
     * 数据绑定方式执行{@link ViewPager#setCurrentItem(int)}
     */
    @BindingAdapter("currentItem")
    public static void setCurrentItem(ViewPager viewPager, int index) {
        viewPager.setCurrentItem(index, false);
    }

    /**
     * 数据绑定方式执行{@link ViewPager#setAdapter(PagerAdapter)}
     */
    @BindingAdapter("adapter")
    public static void setAdapter(ViewPager viewPager, FragmentPagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    /**
     * 数据绑定方式执行{@link RecyclerView#setLayoutManager(RecyclerView.LayoutManager)}
     */
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView view, RecyclerView.LayoutManager manager) {
        view.setLayoutManager(manager);
    }

    /**
     * 数据绑定方式执行{@link RecyclerView#setAdapter(RecyclerView.Adapter)}
     */
    @BindingAdapter("adapter")
    public static void setAdapter(RecyclerView view, RecyclerView.Adapter adapter) {
        view.setAdapter(adapter);
    }

    /**
     * 数据绑定方式执行{@link RecyclerView#addItemDecoration(RecyclerView.ItemDecoration)}
     */
    @BindingAdapter("itemDecoration")
    public static void setItemDecoration(RecyclerView view, RecyclerView.ItemDecoration decoration) {
        view.addItemDecoration(decoration);
    }

    /**
     * 数据绑定方式执行{@link RecyclerView#addItemDecoration(RecyclerView.ItemDecoration)}
     * （注：参数为{@link StickyRecyclerHeadersDecoration}）
     */
    @BindingAdapter("stickyDecoration")
    public static void setStickyDecoration(RecyclerView view, StickyRecyclerHeadersDecoration decoration) {
        view.addItemDecoration(decoration);
    }

    /**
     * 数据绑定方式执行{@link SideAndStickyHeaderRecyclerView#setAdapter(StickyHeaderBindAdapter)}
     */
    @BindingAdapter("adapter")
    public static void setAdapter(SideAndStickyHeaderRecyclerView view, StickyHeaderBindAdapter adapter) {
        view.setAdapter(adapter);
    }

    /**
     * 是否跟随移动
     */
    @BindingAdapter("linkageMove")
    public static void setLinkageMove(SideAndStickyHeaderRecyclerView view, boolean linkageMove) {
        view.linkageMove(linkageMove);
    }

    /**
     * 数据绑定方式执行{@link SideAndStickyHeaderRecyclerView#addItemDecoration(RecyclerView.ItemDecoration)}
     */
    @BindingAdapter("itemDecoration")
    public static void setItemDecoration(SideAndStickyHeaderRecyclerView view, RecyclerView.ItemDecoration decoration) {
        view.addItemDecoration(decoration);
    }


    /* ----------------------------- BindingConversion ----------------------------- */

    /**
     * 使xml里的{@link Conversion<Integer>}数据转换成{@link ColorDrawable}
     */
    @BindingConversion
    public static ColorDrawable convertToBgColor(Conversion<Integer> countryId) {
        return new ColorDrawable(Color.parseColor(countryId.getData() % 2 == 0 ? "#f4237a" : "#1296db"));
    }

    /**
     * 使xml里的{@link Conversion<Integer>}数据转换成{@link ColorInt}
     */
    @BindingConversion
    @ColorInt
    public static int convertToTextColor(Conversion<Integer> countryId) {
        return Color.parseColor(countryId.getData() % 2 == 0 ? "#f4237a" : "#1296db");
    }
}
