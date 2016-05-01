package com.example.administrator.criminallntent.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.criminallntent.R;
import com.example.administrator.criminallntent.UtilLog.LogUtil;
import com.example.administrator.criminallntent.model.Crime;
import com.example.administrator.criminallntent.model.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Administrator on 2016/4/23.
 * 这个fragment中,各个组件获取了资源了,
 * 这个类是模型及视图对象交互的控制器
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.example.administrator.criminallntent.crime_id";
    private Crime mCrime;
    private EditText mTitileField;

    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mTimePicker;

    public  static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    public  static final String DIALOG_TIME = "time";


    /**
     * @param savedInstanceState 保存的数据
     *                           注意这个方法和Acitivity的不同,是public,因为需要被托管的fragment的任何activity调用,
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitileField = (EditText) view.findViewById(R.id.crime_title);
        mTitileField.setText(mCrime.getmTitle());
        mTitileField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        mDateButton = (Button) view.findViewById(R.id.crime_data);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
                // DatePickeFragment dialog=new DatePickeFragment();
                //DialogFragment dialog = DatePickeFragment.newInstance(mCrime.getmDate());
                //dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);

                AlterFragment dialog=AlterFragment.newInstance(mCrime.getmDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);


            }
        });



        mTimePicker=(Button)view.findViewById(R.id.crime_timePicker);
        mTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                TimePickerFragment timePickerFragment=new TimePickerFragment();
                timePickerFragment.show(fragmentManager, DIALOG_TIME);


            }
        });



        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });
        return view;

    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickeFragment.EXTRA_DATE);
            mCrime.setmDate(date);
            LogUtil.d("CrimeFragment-----", "执行了吗?");
            Log.d("CrimeFragment"," wo  ai ni");
            updateDate();
        }

    }

    private void updateDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE,MMMM dd,yyyy", Locale.US);
        mDateButton.setText(simpleDateFormat.format(mCrime.getmDate()));

    }
}
