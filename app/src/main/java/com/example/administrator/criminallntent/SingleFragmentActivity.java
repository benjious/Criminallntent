package com.example.administrator.criminallntent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.administrator.criminallntent.Activity.CrimeListActivity;
import com.example.administrator.criminallntent.Fragment.CrimeFragment;

/**
 * Created by Administrator on 2016/4/26.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    //抽象方法,返回一个由Activity托管的Fragment 实例
    protected  abstract android.support.v4.app.Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment==null) {
            fragment=createFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();


        }
    }


}
