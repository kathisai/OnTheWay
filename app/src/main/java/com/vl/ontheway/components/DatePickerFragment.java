package com.vl.ontheway.components;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * DatePickerFragment is a dialogue to select date
 *
 * @author ssurishetty
 */
public class DatePickerFragment extends DialogFragment implements OnDateSetListener {
    private TextView mSelectDateEt;
    private TextView mSelectMonthEt;

    public DatePickerFragment(TextView mSelDateEt, TextView monthTV) {
        this.mSelectDateEt = mSelDateEt;
        this.mSelectMonthEt = monthTV;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String formattedDate = sdf.format(c.getTime());
        mSelectDateEt.setText(formattedDate);

        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM");
        String formattedDate1 = sdf1.format(c.getTime());
        mSelectMonthEt.setText(formattedDate1);
    }
}