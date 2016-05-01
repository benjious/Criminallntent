package com.example.administrator.criminallntent.Fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.example.administrator.criminallntent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/4/27.
 */
public class DatePickeFragment extends android.support.v4.app.DialogFragment {

    public static final String EXTRA_DATE = "com.example.criminallntent.datepickerfragment.";
    private Date mDate;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //这个成员变量是来接受CrimeFragment传递过来的Date,数据的,同时也可以接受内部方法onDateChanged方法保存在
        //arguement中的值,
        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);

        //create a Calender to get the year,the month,the day;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
         int month = calendar.get(Calendar.MONTH);
         int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
        DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                //Translate year,month,day into a Date object using a calendar
                mDate = new GregorianCalendar(year, month, day).getTime();

                //Update arguement to preserve selected value on rotation防止旋转屏幕,失去数据
                getArguments().putSerializable(EXTRA_DATE, mDate);
                
                

            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);


                    }
                })
                .create();


    }
    
    private void sendResult(int resultCode) {
        if (getTargetFragment()==null) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);

    }
    
    


    //这也就定制了DatePickerFragment这个类,替代了他的构造方法,并且是带有数据的,
    public static DatePickeFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickeFragment pickeFragment = new DatePickeFragment();
        pickeFragment.setArguments(args);
        return pickeFragment;

    }


}


