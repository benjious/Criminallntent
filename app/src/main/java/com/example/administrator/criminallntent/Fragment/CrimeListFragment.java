package com.example.administrator.criminallntent.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.criminallntent.Activity.CrimePagerActivity;
import com.example.administrator.criminallntent.R;
import com.example.administrator.criminallntent.UtilLog.LogUtil;
import com.example.administrator.criminallntent.model.Crime;
import com.example.administrator.criminallntent.model.CrimeLab;

import java.util.ArrayList;

/**
 * 这是现实列表Crime的
 * Created by Administrator on 2016/4/26.
 */
public class CrimeListFragment extends android.support.v4.app.ListFragment {

    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";


    //这是个自定义的Adapter
    private class CrimeAdapter extends ArrayAdapter<Crime> {

        //定制自己的Apdapter,创建了个构造方法,并把数据传给父类,调用父类的构造方法
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        //重写方法定制自己的列表项,
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }
            Crime crime = getItem(position);
            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(crime.getmTitle());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(crime.getmDate().toString());

            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(crime.ismSolved());

            return convertView;

        }
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //GET THE CRIME FROM THE adapter
        Crime crime = ((CrimeAdapter) getListAdapter()).getItem(position);
        LogUtil.d(TAG, crime.getmTitle() + "was clicked");

        //start CrimeActivity
        //Intent intent = new Intent(getActivity(), CrimeActivity.class);
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getmId());
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        //这里对CrimeLab实例过了
        mCrimes = CrimeLab.get(getActivity()).getmCrimes();

       // ArrayAdapter<Crime> crimeArrayAdapter = new ArrayAdapter<Crime>(getActivity(), android.R.layout.simple_list_item_1, mCrimes);
        CrimeAdapter crimeArrayAdapter = new CrimeAdapter(mCrimes);
        setListAdapter(crimeArrayAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
