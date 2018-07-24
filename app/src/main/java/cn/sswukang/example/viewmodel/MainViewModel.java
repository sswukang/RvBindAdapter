package cn.sswukang.example.viewmodel;

import android.databinding.ObservableField;

import cn.sswukang.example.base.BaseActivityViewModel;
import cn.sswukang.example.databinding.ActivityMainBinding;
import cn.sswukang.example.view.MainActivity;

/**
 * Main Activity ViewModel
 *
 * @author sswukang on 2017/2/21 18:01
 * @version 1.0
 */
public class MainViewModel extends BaseActivityViewModel<MainActivity, ActivityMainBinding> {
    // 中文名
    public ObservableField<String> nameCn = new ObservableField<>();
    // 英文名
    public ObservableField<String> nameEn = new ObservableField<>();
}
