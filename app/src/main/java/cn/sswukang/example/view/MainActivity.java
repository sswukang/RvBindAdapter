package cn.sswukang.example.view;

import android.annotation.SuppressLint;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ListPopupWindow;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

import cn.sswukang.example.R;
import cn.sswukang.example.base.BaseActivity;
import cn.sswukang.example.base.BaseFragment;
import cn.sswukang.example.base.BaseFragmentAdapter;
import cn.sswukang.example.databinding.ActivityMainBinding;
import cn.sswukang.example.viewmodel.MainViewModel;

/**
 * 主界面
 *
 * @author sswukang on 2017/2/21 11:57
 * @version 1.0
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    // 左pop
    private ListPopupWindow leftMenuPop;
    // 右pop
    private ListPopupWindow rightMenuPop;

    // FragmentAdapter
    private BaseFragmentAdapter<BaseFragment> fragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        // 数据绑定
        getDataBinding().setMain(getViewModel());
        // 初始化ActionBar
        setSupportActionBar(getDataBinding().topToolbar);
        // 初始化PopupWindow
        initLeftMenuPop();
        initRightMenuPop();
        // 初始化ViewPager
        fragmentAdapter = new BaseFragmentAdapter<>(getSupportFragmentManager(),
                Arrays.asList(new MainSingleFragment(), new MainMultiFragment(),
                        new MainStickyFragment(), new MainSideStickyFragment()));
        getDataBinding().setAdapter(fragmentAdapter);
    }

    @SuppressLint("RestrictedApi")
    private void initLeftMenuPop() {
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        menuBuilder.setOptionalIconsVisible(true);
        menuBuilder.add(R.string.main_single).setIcon(R.drawable.ic_main_single);
        menuBuilder.add(R.string.main_multi).setIcon(R.drawable.ic_main_multi);
        menuBuilder.add(R.string.main_sticky).setIcon(R.drawable.ic_main_sticky);
        menuBuilder.add(R.string.main_sticky_side).setIcon(R.drawable.ic_main_sticky_side);
        leftMenuPop = new ListPopupWindow(getContext());
        leftMenuPop.setAdapter(new MenuAdapter(menuBuilder, getLayoutInflater(), true));
        leftMenuPop.setWidth(getResources().getDisplayMetrics().widthPixels / 2);
        leftMenuPop.setHeight(ListPopupWindow.WRAP_CONTENT);
        leftMenuPop.setAnchorView(getDataBinding().topToolbar);
        leftMenuPop.setDropDownGravity(Gravity.START);
        leftMenuPop.setModal(true);//设置是否是模式
        leftMenuPop.setOnItemClickListener((parent, view, position, id) -> {
            getDataBinding().setCurrentItem(position);
            leftMenuPop.dismiss();
        });
    }

    @SuppressLint("RestrictedApi")
    private void initRightMenuPop() {
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        menuBuilder.setOptionalIconsVisible(true);
        menuBuilder.add(R.string.main_asc).setIcon(R.drawable.ic_main_asc);
        menuBuilder.add(R.string.main_desc).setIcon(R.drawable.ic_main_desc);
        menuBuilder.add(R.string.main_shuffle).setIcon(R.drawable.ic_main_shuffle);
        rightMenuPop = new ListPopupWindow(getContext());
        rightMenuPop.setAdapter(new MenuAdapter(menuBuilder, getLayoutInflater(), true));
        rightMenuPop.setWidth(getResources().getDisplayMetrics().widthPixels / 2);
        rightMenuPop.setHeight(ListPopupWindow.WRAP_CONTENT);
        rightMenuPop.setAnchorView(getDataBinding().topToolbar);
        rightMenuPop.setDropDownGravity(Gravity.END);
        rightMenuPop.setModal(true);//设置是否是模式
        rightMenuPop.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) { // 调用排序方法
                case 0:
                    fragmentAdapter.getItem(getDataBinding().mainViewPager.getCurrentItem()).asc();
                    break;
                case 1:
                    fragmentAdapter.getItem(getDataBinding().mainViewPager.getCurrentItem()).desc();
                    break;
                case 2:
                    fragmentAdapter.getItem(getDataBinding().mainViewPager.getCurrentItem()).shuffle();
                    break;
            }
            rightMenuPop.dismiss();
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // 可对菜单进行设置，如visible
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                leftMenuPop.show();
                return true;
            case R.id.main_pop:
                rightMenuPop.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}