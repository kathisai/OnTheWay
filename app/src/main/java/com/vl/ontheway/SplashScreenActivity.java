package com.vl.ontheway;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends BaseActivity {

	private Handler splash_time_handler;
	private Runnable r_callingMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// Track the application launch
		// AdWordsConversionReporter.reportWithConversionId(this.getApplicationContext(),
		// "944543988",
		// "nePECIjc-V4Q9LGywgM", "0.00", false);

		// Track usage

//		// call abstract methods here
//		initializeViews();
//		readBundleData();
//		addEventListeners();

		// When application is in background, don't show the Splash screen to
		// user. Directly take him to the Notification details screen, when user
		// selects the push notification.
		if(isFromPush())
			return;

		// Show Splash screen for 3secs and navigate to login screen
		splash_time_handler = new Handler();
		splash_time_handler.postDelayed(r_callingMenu = new Runnable() {
			@Override
			public void run() {
				if(isFromPush()){
					return;
				} else {
//					ModuleManager.startActivity(mBaseActivity, ModuleManager.LOGIN_MODULE,
//							LoginModuleHandler.HOME_ACTIVTIY);
					Intent intent = new Intent(mBaseActivity,LoginActivity.class);
					startActivity(intent);
					finish();
				}
			}
		}, Constants.SPLASHTIME);

	}
	
	private boolean isFromPush(){
		Intent intent = getIntent();
		if (intent.hasExtra(Constants.INTENT_DATA)) {
			Bundle b = null;
			b = intent.getBundleExtra(Constants.INTENT_DATA);
			boolean fromPush = b.getBoolean(Constants.IS_FROM_PUSH_NOTIFICATION_KEY, false);

			if (fromPush) {
				Intent loginIntent = new Intent(mBaseActivity,LoginActivity.class);
				startActivity(loginIntent);
				finish();
				
				return true;
			}
		}
		
		return false;
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			if (splash_time_handler != null && r_callingMenu != null) {
				splash_time_handler.removeCallbacks(r_callingMenu);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
