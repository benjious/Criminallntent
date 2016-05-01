package com.example.administrator.criminallntent.Fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;


import java.util.Calendar;

/**
 * Created by Administrator on 2016/4/28.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //use the current date as the default date in the picker
        final Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int minute=calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));

    }


    /**
     * 这是设置之后的操作
     * @param view 视图
     * @param hourOfDay hour
     * @param minute minute
     */
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        //Do something with the time chosen by the user



    }





}
