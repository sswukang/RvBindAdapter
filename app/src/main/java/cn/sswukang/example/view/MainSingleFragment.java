package cn.sswukang.example.view;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import cn.sswukang.example.R;
import cn.sswukang.example.base.BaseFragment;
import cn.sswukang.example.databinding.MainSingleFragmentBinding;
import cn.sswukang.example.databinding.SingleAdapterBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.viewmodel.MainSingelViewModel;
import cn.sswukang.library.adapter.base.BaseBindViewHolder;
import cn.sswukang.library.adapter.single.SingleBindAdapter;

/**
 * Single Fragment
 *
 * @author sswukang on 2017/2/22 16:29
 * @version 1.0
 */
public class MainSingleFragment extends BaseFragment<MainSingleFragmentBinding, MainSingelViewModel, MainActivity> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_single;
    }

    @Override
    public void initView() {
        // 数据绑定
        getDataBinding().setMainSingle(getViewModel());
        // 初始化列表数据
        getDataBinding().setLayoutManager(new LinearLayoutManager(getContext()));
        getDataBinding().setAdapter(new SingleBindAdapter<Country, SingleAdapterBinding>(R.layout.rv_single_item,
                CountryManager.getInstance().getCountryList()) {
            @Override
            public void convert(Country country, SingleAdapterBinding binding, BaseBindViewHolder<SingleAdapterBinding> holder) {
                // 关键步骤，xml与数据的绑定，否则只有item条数，而无具体数据显示。
                binding.setCountry(country);
            }

            @Override
            public void onItemClick(View itemView, Country country) {
                // 点击改变opToolbar内容
                getCreatorActivity().getViewModel().nameCn.set(country.getCountryNameCn());
                getCreatorActivity().getViewModel().nameEn.set(country.getCountryNameEn());
            }
        });
    }

}
