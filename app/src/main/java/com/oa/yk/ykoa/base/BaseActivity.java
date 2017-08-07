package com.oa.yk.ykoa.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.oa.yk.ykoa.OaApplication;
import com.oa.yk.ykoa.R;
import com.oa.yk.ykoa.activity.LoginActivity;
import com.oa.yk.ykoa.bean.UserDTO;
import com.oa.yk.ykoa.fragment.FragmentFactory;
import com.oa.yk.ykoa.utils.AcacheUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 78560 on 2017/8/2.
 */

public class BaseActivity extends AppCompatActivity {
    private String TAG = "base";
    private int mContentLayoutID;
    protected String mFragmentOpenState = FragmentFactory.FLAG_FRAGMENT_NONE;

    protected AcacheUtils mAcache = AcacheUtils.get(OaApplication.getContext()); // 初始化，一般放在基类里
    protected UserDTO uInfo = (UserDTO) mAcache.getAsObject("user");



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OaApplication.addActivity(this);
        Log.d(TAG, getClass().getSimpleName());
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.color_0176da));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开fragment
     *
     * @param tag 对应的fragment在FrgamentFactory里的标签字符串值
     */
    public void openFragment(String tag) {
        if (mFragmentOpenState.equals(tag)) {
            return;
        }
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment == null) {
            fragment = FragmentFactory.getFragment(tag);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(mContentLayoutID, fragment, tag);
        fragmentTransaction.commit();
        setFragmentOpenState(tag);
    }

    /**
     * 切换fragment
     * @param from 当前fragment的标识
     * @param to 需要切换的fragment的标识
     */
    public void switchFragment(String from, String to) {
        if (mFragmentOpenState.equals(to)) {
            return;
        }
        Fragment fromFg = getSupportFragmentManager().findFragmentByTag(from);
        if (fromFg == null) {
            fromFg = FragmentFactory.getFragment(from);
        }
        Fragment toFg = getSupportFragmentManager().findFragmentByTag(to);
        if (toFg == null) {
            toFg = FragmentFactory.getFragment(to);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!toFg.isAdded()) {
            fragmentTransaction.hide(fromFg).add(mContentLayoutID, toFg, to);
        } else {
            fragmentTransaction.hide(fromFg).show(toFg);
        }
        try{
            fragmentTransaction.commit();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }

        setFragmentOpenState(to);
    }
    /**
     * 设置该acitivty中需要替换成fragment的ID
     * {@link #openFragment(String)} }
     *
     * @param mContentLayoutID
     */
    public void setContentLayoutID(int mContentLayoutID) {
        this.mContentLayoutID = mContentLayoutID;
    }

    protected void setFragmentOpenState(String state) {
        mFragmentOpenState = state;

    }

    public void tost(String name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    public void tost(int name) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OaApplication.removeActivity(this);
    }
}
