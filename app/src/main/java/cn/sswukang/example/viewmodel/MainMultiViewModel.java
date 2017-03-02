package cn.sswukang.example.viewmodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cn.sswukang.example.base.BaseFragmentViewModel;
import cn.sswukang.example.databinding.MainMultiFragmentBinding;
import cn.sswukang.example.manager.CountryManager;
import cn.sswukang.example.model.Country;
import cn.sswukang.example.view.MainMultiFragment;

/**
 * Main Multi Fragment ViewModel
 *
 * @author sswukang on 2017/3/1 14:56
 * @version 1.0
 */
public class MainMultiViewModel extends BaseFragmentViewModel<MainMultiFragment, MainMultiFragmentBinding> {
    @Override
    public void asc() {
        // Country 排序
        List<Country> sort = CountryManager.getInstance().getCountryList();
        Collections.sort(sort, CountryManager.getInstance().comparatorIdAcs());
        // 多item数据
        Country header = new Country();
        header.setCountryNameEn("Recycler View Multi Adapter Item.");
        List<Country> list = new ArrayList<>();
        for (int i = 0, j = new Random().nextInt(5) + 1; i < j; i++) {
            list.add(header); // 随机添加1~5个数据
        }
        list.addAll(sort);
        // 刷新
        getFragment().updateAdapter(list);
    }

    @Override
    public void desc() {
        // Country 排序
        List<Country> sort = CountryManager.getInstance().getCountryList();
        Collections.sort(sort, CountryManager.getInstance().comparatorIdAcs());
        Collections.reverse(sort);
        // 多item数据
        Country header = new Country();
        header.setCountryNameEn("Recycler View Multi Adapter Item.");
        List<Country> list = new ArrayList<>();
        for (int i = 0, j = new Random().nextInt(5) + 1; i < j; i++) {
            list.add(header); // 随机添加1~5个数据
        }
        list.addAll(sort);
        // 刷新
        getFragment().updateAdapter(list);
    }

    @Override
    public void shuffle() {
        // Country 乱序
        List<Country> shuffle = CountryManager.getInstance().getCountryList();
        Collections.shuffle(shuffle);
        // 多item数据
        Country header = new Country();
        header.setCountryNameEn("Recycler View Multi Adapter Item.");
        List<Country> list = new ArrayList<>();
        for (int i = 0, j = new Random().nextInt(5) + 1; i < j; i++) {
            list.add(header); // 随机添加1~5个数据
        }
        list.addAll(shuffle);
        // 刷新
        getFragment().updateAdapter(list);
    }
}
