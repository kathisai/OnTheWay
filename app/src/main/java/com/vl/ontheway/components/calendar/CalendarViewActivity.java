package com.vl.ontheway.components.calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vl.ontheway.Constants;
import com.vl.ontheway.R;

import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarViewActivity extends Activity implements OnClickListener, CalendarEventsListener {

    private CalendarGridAdapter mCalendarGridAdapter;

    private TextView mDateTypeTextView;
    private ImageView mCloseDatePickerImageView;
    private TextView mMonthYearHeaderTextView;
    private RelativeLayout mPreviousMonthArrowLayout;
    private RelativeLayout mNextMonthArrowLayout;
    private GridView mGridview;

    private GregorianCalendar mSelectedDateCal = (GregorianCalendar) GregorianCalendar.getInstance();
    private long mCalendarStartDateInMillis;
    private long mCalendarEndDateInMillis;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Width and hide title for the activity as we are displaying it as dialog
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels);
        setContentView(R.layout.calendar);
        getWindow().setLayout(screenWidth, LayoutParams.WRAP_CONTENT);
        Locale.setDefault(Locale.getDefault());

        // get intent date
        String dateTypeString = "";
        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.DATE_TYPE_HEADER_STRING)) {
                dateTypeString = getIntent().getExtras().getString(Constants.DATE_TYPE_HEADER_STRING);
            }

            if (getIntent().hasExtra(Constants.SELECTED_DATE_IN_MILLIS)) {
                long selectedDateInMills = getIntent().getExtras().getLong(Constants.SELECTED_DATE_IN_MILLIS,
                        GregorianCalendar.getInstance().getTimeInMillis());
                mSelectedDateCal.setTimeInMillis(selectedDateInMills);
            }

            // calendar start date
            if (getIntent().hasExtra(Constants.CALENDAR_START_DATE)) {
                mCalendarStartDateInMillis = getIntent().getExtras().getLong(Constants.CALENDAR_START_DATE,
                        GregorianCalendar.getInstance().getTimeInMillis());
            }

            // calendar end date
            if (getIntent().hasExtra(Constants.CALENDAR_END_DATE)) {
                mCalendarEndDateInMillis = getIntent().getExtras().getLong(Constants.CALENDAR_END_DATE,
                        GregorianCalendar.getInstance().getTimeInMillis());
            }
        }

        // get layout views
        mDateTypeTextView = (TextView) findViewById(R.id.dateTypeTextView);
        mCloseDatePickerImageView = (ImageView) findViewById(R.id.closeDatePickerImageView);
        mMonthYearHeaderTextView = (TextView) findViewById(R.id.monthYearHeaderTextView);
        mPreviousMonthArrowLayout = (RelativeLayout) findViewById(R.id.previousMonthArrowLayout);
        mNextMonthArrowLayout = (RelativeLayout) findViewById(R.id.nextMonthArrowLayout);
        mGridview = (GridView) findViewById(R.id.gridview);

        // set event listeners
        mCloseDatePickerImageView.setOnClickListener(this);
        mPreviousMonthArrowLayout.setOnClickListener(this);
        mNextMonthArrowLayout.setOnClickListener(this);

        // set Date Type header text retrieved from intent data
        if (!TextUtils.isEmpty(dateTypeString)) {
            mDateTypeTextView.setText(dateTypeString);
        }

        // Instantiate calendar GridView adapter
        mCalendarGridAdapter = new CalendarGridAdapter(this, mSelectedDateCal,
                mCalendarStartDateInMillis, mCalendarEndDateInMillis);
        mGridview.setAdapter(mCalendarGridAdapter);

        // set current month for header textview
        mMonthYearHeaderTextView.setText(android.text.format.DateFormat.format("MMMM yyyy", mSelectedDateCal).toString().toUpperCase());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeDatePickerImageView:
                finish();
                break;

            case R.id.previousMonthArrowLayout:
                mCalendarGridAdapter.setPreviousMonth();
                mCalendarGridAdapter.refreshCalendar();
                break;

            case R.id.nextMonthArrowLayout:
                mCalendarGridAdapter.setNextMonth();
                mCalendarGridAdapter.refreshCalendar();
                break;

            default:
                break;
        }
    }

    @Override
    public void onUpdateSelectedMonth(GregorianCalendar selectedDateCal) {
        String selectedMonth = android.text.format.DateFormat.format("MMMM yyyy", selectedDateCal).toString().toUpperCase();

        mMonthYearHeaderTextView.setText(selectedMonth);

        Intent intent = new Intent();
        intent.putExtra(Constants.SELECTED_DATE_IN_MILLIS, selectedDateCal.getTimeInMillis());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onUpdateNavigatedMonth(GregorianCalendar navigatedDateCal) {
        String selectedMonth = android.text.format.DateFormat.format("MMMM yyyy", navigatedDateCal).toString().toUpperCase();
        mMonthYearHeaderTextView.setText(selectedMonth);
    }

}