package cn.sswukang.example.viewmodel;

import java.util.Collections;
import java.util.List;

import cn.sswukang.example.base.BaseFragmentViewModel;
import cn.sswukang.example.databinding.MainSingleFragmentBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.view.MainSingleFragment;

/**
 * Main Single Fragment ViewModel
 *
 * @author sswukang on 2017/2/22 17:08
 * @version 1.0
 */
public class MainSingleViewModel extends BaseFragmentViewModel<MainSingleFragment, MainSingleFragmentBinding> {
    @Override
    public void asc() {
        // Country 排序
        List<Country> sort = CountryManager.getInstance().getCountryList();
        Collections.sort(sort, CountryManager.getInstance().comparatorCodeAcs());
        // 刷新
        getFragment().updateAdapter(sort);
    }

    @Override
    public void desc() {
        // Country 排序
        List<Country> sort = CountryManager.getInstance().getCountryList();
        Collections.sort(sort, CountryManager.getInstance().comparatorCodeAcs());
        Collections.reverse(sort);
        // 刷新
        getFragment().updateAdapter(sort);
    }

    @Override
    public void shuffle() {
        // Country 乱序
        List<Country> shuffle = CountryManager.getInstance().getCountryList();
        Collections.shuffle(shuffle);
        // 刷新
        getFragment().updateAdapter(shuffle);
    }
}
