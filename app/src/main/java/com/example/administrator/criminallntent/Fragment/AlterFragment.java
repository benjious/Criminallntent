package com.example.administrator.criminallntent.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.example.administrator.criminallntent.R;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/29.
 */
public class AlterFragment extends DialogFragment {

    private static final String EXTRA_DATE_ALTER = "com.example.administrator.criminallntent.alterfragment";
    public static final String DIALOG_DATE_ALTER = "date";
    public static final String DIALOG_TIME_ALTER = "time";


    private Date mDate;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDate = (Date) getArguments().getSerializable(EXTRA_DATE_ALTER);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choice)
                .setSingleChoiceItems(R.array.choices, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            startDate();
                        } else if (which == 1) {
                            startTime();
                        }
                    }
                });
        return builder.create();

    }

    public void startDate() {
        android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
        DatePickeFragment datePickeFragment = DatePickeFragment.newInstance(mDate);
        datePickeFragment.show(manager, DIALOG_DATE_ALTER);

    }

    public void startTime() {
        android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(manager, DIALOG_TIME_ALTER);
    }

    public static AlterFragment newInstance(Date mDate) {
        Bundle args = new Bundle();
        AlterFragment alterFragment = new AlterFragment();
        args.putSerializable(EXTRA_DATE_ALTER, mDate);
        alterFragment.setArguments(args);
        return alterFragment;
    }
}
