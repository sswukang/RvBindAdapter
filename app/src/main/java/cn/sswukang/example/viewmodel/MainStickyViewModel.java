package cn.sswukang.example.viewmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.sswukang.example.base.BaseFragmentViewModel;
import cn.sswukang.example.databinding.MainStickyFragmentBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.view.MainStickyFragment;

/**
 * Main Sticky Fragment ViewModel
 *
 * @author sswukang on 2017/3/2 14:26
 * @version 1.0
 */
public class MainStickyViewModel extends BaseFragmentViewModel<MainStickyFragment, MainStickyFragmentBinding> {
    @Override
    public void asc() {
        // Country 排序
        List<Country> sort = CountryManager.getInstance().getCountryList();
        Collections.sort(sort, CountryManager.getInstance().comparatorNameEnAcs());
        // 刷新
        getFragment().updateAdapter(sort);
    }

    @Override
    public void desc() {
        // Country 排序
        List<Country> sort = CountryManager.getInstance().getCountryList();
        Collections.sort(sort, CountryManager.getInstance().comparatorNameEnAcs());
        Collections.reverse(sort);
        // 刷新
        getFragment().updateAdapter(sort);
    }

    @Override
    public void shuffle() {
        // 首字母乱序
        List<String> initialsShuffle = CountryManager.getInstance().getInitialsList();
        Collections.shuffle(initialsShuffle);
        // Country 乱序
        List<Country> shuffle = new ArrayList<>();
        // 循环乱序首字母
        for (String initials : initialsShuffle) {
            List<Country> initialsList = new ArrayList<>();
            for (Country country : CountryManager.getInstance().getCountryList()) {
                if (country.getCountryNameEn().startsWith(initials)) {
                    // 找到首字母与该字母相同的元素，并添加
                    initialsList.add(country);
                }
            }
            // 该字母所有元素排序
            Collections.sort(initialsList, CountryManager.getInstance().comparatorNameEnAcs());
            shuffle.addAll(initialsList);
        }
        // 刷新
        getFragment().updateAdapter(shuffle);
    }
}
