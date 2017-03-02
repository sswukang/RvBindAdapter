package cn.sswukang.example.view;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.sswukang.example.R;
import cn.sswukang.example.base.BaseFragment;
import cn.sswukang.example.databinding.MainMultiFragmentBinding;
import cn.sswukang.example.databinding.MultiAdapterContentBinding;
import cn.sswukang.example.databinding.MultiAdapterTitleBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.viewmodel.MainMultiViewModel;
import cn.sswukang.library.adapter.multi.MultiBindAdapter;

/**
 * multi Fragment
 *
 * @author sswukang on 2017/3/1 15:37
 * @version 1.0
 */
public class MainMultiFragment extends BaseFragment<MainMultiFragmentBinding, MainMultiViewModel, MainActivity> {

    private MultiBindAdapter<Country> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_multi;
    }

    @Override
    public void initView() {
        // 模拟添加一些数据
        Country header = new Country();
        header.setCountryNameEn("Recycler View Multi Adapter Item.");
        List<Country> list = new ArrayList<>();
        list.add(header); // 添加一些 title item 数据
        list.add(header);
        list.addAll(CountryManager.getInstance().getCountryList()); // content item 数据
        // ViewModel数据绑定
        getDataBinding().setMainMulti(getViewModel());
        // Adapter数据绑定
        adapter = new MultiBindAdapter<Country>(list) {
            @Override
            public int getItemLayoutId(Country country, int position) {
                // 得到每个类型item的布局id
                return position < getItemCount() - CountryManager.getInstance().getListSize() ?
                        R.layout.rv_multi_title : R.layout.rv_multi_content;
            }

            @Override
            public void convert(Country country, ViewDataBinding binding, @LayoutRes int layoutId) {
                // adapter数据绑定
                switch (layoutId) {
                    case R.layout.rv_multi_title:
                        MultiAdapterTitleBinding titleBinding = getItemBinding(binding);
                        titleBinding.setCountry(country);
                        break;
                    case R.layout.rv_multi_content:
                        MultiAdapterContentBinding contentBinding = getItemBinding(binding);
                        contentBinding.setCountry(country);
                        break;
                }
            }

            @Override
            public void onItemClick(View itemView, Country country, @LayoutRes int layoutId) {
                switch (layoutId) {
                    case R.layout.rv_multi_title:
                        Snackbar.make(itemView, "MultiAdapter Title Item.", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.layout.rv_multi_content:
                        Snackbar.make(itemView, country.toString(), Snackbar.LENGTH_SHORT).show();
                        // 点击改变opToolbar内容
                        getCreatorActivity().getViewModel().nameCn.set(country.getCountryNameCn());
                        getCreatorActivity().getViewModel().nameEn.set(country.getCountryNameEn());
                        break;
                }
            }
        };
        getDataBinding().setLayoutManager(new LinearLayoutManager(getContext()));
        getDataBinding().setAdapter(adapter);
    }

    public void updateAdapter(List<Country> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }
}
