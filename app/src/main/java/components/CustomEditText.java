package components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.vl.ontheway.R;


/**
 * @author hpadagala
 *         This is the CustomEditText class . This is used in all xml files in place of EditText to set the custom font
 */
public class CustomEditText extends EditText {

    public static final String TAG = "CustomEditText";

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTahomaTypeface(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTahomaTypeface(context, attrs);
    }

    /**
     * This method is used for giving the 'Tahome' typeface to the text provided
     * on the {@link EditText}.
     *
     * @param context {@link Context} reference in which the {@link EditText} is
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
