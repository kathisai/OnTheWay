package com.vl.ontheway.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.vl.ontheway.R;


/**
 * @author rmaddineni
 */

public class CustomButton extends Button {

    /**
     * Constructor for instantiating the {@link Button}.
     *
     * @param context  {@link Context} reference in which the {@link Button} is used.
     * @param attrs    {@link AttributeSet}
     * @param defStyle style type given for the {@link Button}
     */
    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTahomaTypeface(context, attrs);
//		this.setBackgroundResource(context.getResources().getDrawable(R.drawable.buttons_with_rounded_red_bg));
//		this.setPadding(R.dimen.dp_40, 0, R.dimen.dp_40, 0);
//		this.setHeight(R.dimen.dp_40);
    }

    /**
     * Constructor for instantiating the {@link Button}.
     *
     * @param context {@link Context} reference in which the {@link Button} is used.
     * @param attrs   {@link AttributeSet}
     */
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTahomaTypeface(context, attrs);
//		this.setBackgroundResource(R.drawable.buttons_with_rounded_red_bg);
//		this.setPadding(R.dimen.dp_40, 0, R.dimen.dp_40, 0);
//		this.setHeight(R.dimen.dp_40);
    }

    /**
     * This method is used for giving the 'Tahome' typeface to the text provided
     * on the {@link Button}.
     *
     * @param context {@link Context} reference in which the {@link Button} is used.
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
        } else if (boldStyle != null && boldStyle.equals("semi-bold")) {
            //Typeface typeface_bold = Typeface.createFromAsset(context.getAssets(), "font/OpenSans-Bold.ttf");
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
