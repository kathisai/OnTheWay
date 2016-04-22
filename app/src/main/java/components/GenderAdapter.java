package components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.vl.ontheway.BaseActivity;
import com.vl.ontheway.R;


public class GenderAdapter extends BaseAdapter {

    private BaseActivity activity;
    private String[] spinnerItems;

    public GenderAdapter(BaseActivity activity, String[] spinnerItems) {
        this.activity = activity;
        this.spinnerItems = spinnerItems;

		/*
           <string-array name="title_list">
		        <item>Title</item> - 0
		        <item>Ms</item> - 1
		        <item>Mr</item> - 2
		        <item>Mrs</item> - 3
	    	</string-array> 
		 */
		
		/*
			<string-array name="gender_list">
		        <item>Male</item> - 0
		        <item>Female</item> - 1
    		</string-array>
		 */
    }

    public void updateGenderSpinnerItems(String[] items) {
        this.spinnerItems = items;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (spinnerItems != null ? spinnerItems.length : 0);
    }

    @Override
    public String getItem(int position) {
        return spinnerItems[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.spinner_item, parent, false);
        }

        CustomTextView spinnerItem = (CustomTextView) convertView.findViewById(R.id.spinner_item);
        spinnerItem.setText(getItem(position).toString());

        return convertView;
    }

}
