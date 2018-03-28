package cn.sswukang.example.view;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import cn.sswukang.example.R;
import cn.sswukang.example.base.BaseFragment;
import cn.sswukang.example.databinding.MainStickyFragmentBinding;
import cn.sswukang.example.databinding.StickyAdapterContentBinding;
import cn.sswukang.example.databinding.StickyAdapterTitleBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.viewmodel.MainStickyViewModel;
import cn.sswukang.library.adapter.sticky.StickyHeaderBindAdapter;
import cn.sswukang.library.lib.sticky_header.sticky.StickyRecyclerHeadersDecoration;

/**
 * Sticky Fragment
 *
 * @author sswukang on 2017/3/2 14:27
 * @version 1.0
 */
public class MainStickyFragment extends BaseFragment<MainStickyFragmentBinding, MainStickyViewModel, MainActivity> {

    private StickyHeaderBindAdapter<Country, StickyAdapterTitleBinding, StickyAdapterContentBinding> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_sticky;
    }

    @Override
    public void initView() {
        // ViewModel数据绑定
        getDataBinding().setMainSticky(getViewModel());
        // Adapter数据绑定
        adapter = new StickyHeaderBindAdapter<Country, StickyAdapterTitleBinding, StickyAdapterContentBinding>(
                R.layout.rv_sticky_title, R.layout.rv_sticky_content, CountryManager.getInstance().getCountryList()) {
            @Override
            public int setHeaderHeight() {
                return getResources().getDimensionPixelSize(R.dimen.main_sticky_header_height);
            }

            @Override
            public long getHeaderId(int position, Country country) {
                return country.getCountryNameEn().charAt(0);
            }

            @Override
            public void convertHeader(int position, Country country, StickyAdapterTitleBinding binding) {
                // 粘性头部数据绑定
                binding.setCountry(country);
            }

            @Override
            public void convert(int position, Country country, StickyAdapterContentBinding binding) {
                // 内容数据绑定
                binding.setCountry(country);
            }

            @Override
            public void onItemClick(View itemView, int position, Country country) {
                Snackbar.make(itemView, country.toString(), Snackbar.LENGTH_SHORT).show();
                // 点击改变opToolbar内容
                getCreatorActivity().getViewModel().nameCn.set(country.getCountryNameCn());
                getCreatorActivity().getViewModel().nameEn.set(country.getCountryNameEn());
            }
        };
        getDataBinding().setLayoutManager(new LinearLayoutManager(getContext()));
        getDataBinding().setItemDecoration(new DividerItemDecoration(getCreatorActivity(), DividerItemDecoration.VERTICAL));
        getDataBinding().setStickyDecoration(new StickyRecyclerHeadersDecoration(adapter)); // 必须添加
        getDataBinding().setAdapter(adapter);
    }

    public void updateAdapter(List<Country> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
