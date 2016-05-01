package com.example.administrator.criminallntent.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.administrator.criminallntent.Fragment.CrimeFragment;
import com.example.administrator.criminallntent.R;
import com.example.administrator.criminallntent.model.Crime;
import com.example.administrator.criminallntent.model.CrimeLab;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/27.
 */
public class CrimePagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes= CrimeLab.get(this).getmCrimes();
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
        //这里调用了setAdpater()方法,其后面的运行机制就是添加了fragment,就是CrimeFragment
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public int getCount() {
                return mCrimes.size();
            }

            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getmId());

            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crime crime = mCrimes.get(position);
                if (crime.getmTitle()!=null) {
                    setTitle(crime.getmTitle());

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID uuid = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i=0;i<mCrimes.size();i++) {
            if (mCrimes.get(i).getmId().equals(uuid)) {
                mViewPager.setCurrentItem(i);
                break;
            }

        }




    }
}
