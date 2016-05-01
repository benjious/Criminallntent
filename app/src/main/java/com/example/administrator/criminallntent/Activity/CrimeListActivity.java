package com.example.administrator.criminallntent.Activity;

import android.support.v4.app.Fragment;

import com.example.administrator.criminallntent.Fragment.CrimeListFragment;
import com.example.administrator.criminallntent.SingleFragmentActivity;

/**
 * Created by Administrator on 2016/4/26.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

