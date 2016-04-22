package com.vl.ontheway.components;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.vl.ontheway.Constants;
import com.vl.ontheway.R;
import com.vl.ontheway.components.util.Util;


public class FlightCustomDialog extends Dialog {

    private String mDeptCode;
    private String mArriveCode;
    private String mTitle;
    private String mType;
    private String mDate;

    /**
     * Flight Custom Dialog
     *
     * @param context
     * @param departCode  -- Departure Station code
     * @param arrivalCode -- Arrival Station code
     * @param date        -- Journey date
     * @param title       -- Message title
     * @param type        -- oneway / twoway / addons -- Used to set image resources
     */
    public FlightCustomDialog(Context context, String departCode, String arrivalCode,
                              String date, String title, String type) {
        super(context);
        this.mDeptCode = departCode;
        this.mTitle = title;
        this.mArriveCode = arrivalCode;
        this.mType = type;
        this.mDate = date;
    }

    public String getmDeptCode() {
        return mDeptCode;
    }

    public void setmDeptCode(String mDeptCode) {
        this.mDeptCode = mDeptCode;
    }

    public String getmArriveCode() {
        return mArriveCode;
    }

    public void setmArriveCode(String mArriveCode) {
        this.mArriveCode = mArriveCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_flight_dialog);

        ImageView flightIV = (ImageView) findViewById(R.id.flightImage);
        TextView flight = (TextView) findViewById(R.id.flightNameTextView);
        TextView tirle = (TextView) findViewById(R.id.dialog_title);
        tirle.setText(mTitle);
        String date = "";

        if (!TextUtils.isEmpty(mDate)) {
            date = Util.getDateFormattedString(Constants.SEARCH_FLIGHTS_DATE_FORMAT, mDate, Constants.ISSUE_DATE_FORMAT);
        }

        String text = "";
        if (!TextUtils.isEmpty(date)) {
            text = "<br> <font color='#282828'> on " + date + "</font>";
        }
        flight.setText(Html.fromHtml(mDeptCode + "- " + mArriveCode + text));

        if (mType.equalsIgnoreCase("oneway")) {
            flightIV.setImageResource(R.drawable.popup_upwardflight);
        } else if (mType.equalsIgnoreCase("twoway")) {
            flightIV.setImageResource(R.drawable.popup_returnflight);
        } else if (mType.equalsIgnoreCase("addons")) {
            flightIV.setImageResource(R.drawable.popup_anscillaries);
        }

        this.setCancelable(false);
    }

}
