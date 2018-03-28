package cn.sswukang.example.view;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import java.util.List;

import cn.sswukang.example.R;
import cn.sswukang.example.base.BaseFragment;
import cn.sswukang.example.databinding.MainSideStickyFragmentBinding;
import cn.sswukang.example.databinding.StickyAdapterContentBinding;
import cn.sswukang.example.databinding.StickyAdapterTitleBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.viewmodel.MainSideStickyViewModel;
import cn.sswukang.library.adapter.sticky.StickyHeaderBindAdapter;

/**
 * Side Sticky Fragment
 *
 * @author sswukang on 2017/3/7 11:09
 * @version 1.0
 */
public class MainSideStickyFragment extends BaseFragment<MainSideStickyFragmentBinding, MainSideStickyViewModel, MainActivity> {

    private StickyHeaderBindAdapter<Country, StickyAdapterTitleBinding, StickyAdapterContentBinding> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_side_sticky;
    }

    @Override
    public void initView() {
        // ViewModel数据绑定
        getDataBinding().setMainSideSticky(getViewModel());
        // Adapter数据绑定
        adapter = new StickyHeaderBindAdapter<Country, StickyAdapterTitleBinding, StickyAdapterContentBinding>(
                R.layout.rv_sticky_title, R.layout.rv_sticky_content, CountryManager.getInstance().getCountryList()) {
            @Override
            public int setHeaderHeight() {
                return getResources().getDimensionPixelSize(R.dimen.main_sticky_header_height);
            }

            @Override
            public long getHeaderId(Country country, int position) {
                return country.getCountryNameEn().charAt(0);
            }

            @Override
            public void convertHeader(Country country, StickyAdapterTitleBinding binding, int position) {
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
        getDataBinding().setItemDecoration(new DividerItemDecoration(getCreatorActivity(), DividerItemDecoration.VERTICAL));
        getDataBinding().setAdapter(adapter);
        getDataBinding().setLinkageMove(true);
    }

    public void updateAdapter(List<Country> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
