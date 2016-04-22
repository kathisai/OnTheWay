package com.vl.ontheway.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.vl.ontheway.R;


/**
 * This is a custom {@link TextView} used for assigning the custom fonts and
 * backgrounds to the default {@link TextView}.
 *
 * @version 1.0
 */
public class CustomTextView extends TextView {
    /**
     * Constructor for instantiating the {@link TextView}.
     *
     * @param context  {@link Context} reference in which the {@link TextView} is
     *                 used.
     * @param attrs    {@link AttributeSet}
     * @param defStyle style type given for the {@link TextView}
     */
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTahomaTypeface(context, attrs);
    }

    /**
     * Constructor for instantiating the {@link TextView}.
     *
     * @param context {@link Context} reference in which the {@link TextView} is
     *                used.
     * @param attrs   {@link AttributeSet}
     */
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTahomaTypeface(context, attrs);
    }

    /**
     * This method is used for giving the 'Tahome' typeface to the text provided
     * on the {@link TextView}.
     *
     * @param context {@link Context} reference in which the {@link TextView} is
     *                used.
     * @param attrs   {@link AttributeSet}
     */
    public void setTahomaTypeface(Context context, AttributeSet attrs) {
        // Typeface.createFromAsset doesn't work in the layout editor.
        // Skipping...
        if (isInEditMode()) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String boldStyle = styledAttrs.getString(R.styleable.CustomTextView_textStyle);

        if (boldStyle != null && boldStyle.equals("light")) {
            //Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/OpenSans-Light.ttf");
            Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/HELVETICNORMAL.TTF");
            setTypeface(typeface_bold);


        } else if (boldStyle != null && boldStyle.equals("regular")) {
            //Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/OpenSans-Regular.ttf");
            Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/HELVETICNORMAL.TTF");
            setTypeface(typeface_bold);


        } else {
            //Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/OpenSans-Regular.ttf");
            Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/HELVETICNORMAL.TTF");
            setTypeface(typeface_bold);
        }

        styledAttrs.recycle();
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        invalidate();
        super.onWindowFocusChanged(hasWindowFocus);
    }


}
