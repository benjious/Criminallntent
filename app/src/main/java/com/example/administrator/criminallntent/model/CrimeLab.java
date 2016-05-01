package com.example.administrator.criminallntent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/25.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;


    private CrimeLab(Context mAppContext) {
        this.mAppContext = mAppContext;
        mCrimes = new ArrayList<Crime>();
        for (int i=0;i<100;i++) {
            Crime crime=new Crime();
            crime.setmTitle("Crime #"+i);
            crime.setmSolved(i%2==0);
            mCrimes.add(crime);

        }
    }

    /**
     * 相当于单例类中的getInstance()方法
     * @param context 传递过来的上下文
     * @return 返回单例的实例
     */
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context.getApplicationContext());

        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getmCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID uuid) {
        for (Crime crime : mCrimes) {
            if (crime.getmId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }


}
