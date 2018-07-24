package cn.sswukang.example.view;


import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import cn.sswukang.example.R;
import cn.sswukang.example.base.BaseFragment;
import cn.sswukang.example.databinding.FragmentMainSingleBinding;
import cn.sswukang.example.databinding.RvSingleItemBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.viewmodel.MainSingleViewModel;
import cn.sswukang.library.adapter.single.SingleBindAdapter;

/**
 * Single Fragment
 *
 * @author sswukang on 2017/2/22 16:29
 * @version 1.0
 */
public class MainSingleFragment extends BaseFragment<FragmentMainSingleBinding, MainSingleViewModel, MainActivity> {
    private SingleBindAdapter<Country, RvSingleItemBinding> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_single;
    }

    @Override
    public void initView() {
        // ViewModel数据绑定
        getDataBinding().setMainSingle(getViewModel());
        // Adapter数据绑定
        adapter = new SingleBindAdapter<Country, RvSingleItemBinding>(R.layout.rv_single_item,
                CountryManager.getInstance().getCountryList()) {
            @Override
            public void convert(int position, @Nullable Country country, RvSingleItemBinding binding) {
                // adapter数据绑定
                binding.setCountry(country);
            }

            @Override
            public void onItemClick(View itemView, int position, @Nullable Country country) {
                if (null == country) return;
                Snackbar.make(itemView, country.toString(), Snackbar.LENGTH_SHORT).show();
                // 点击改变opToolbar内容
                getCreatorActivity().getViewModel().nameCn.set(country.getCountryNameCn());
                getCreatorActivity().getViewModel().nameEn.set(country.getCountryNameEn());
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
