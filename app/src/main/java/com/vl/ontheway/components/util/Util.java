package com.vl.ontheway.components.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;
import com.vl.ontheway.R;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Util {
	/**
	 * Show an alert dialog and navigate back to previous screen if goBack is
	 * true
	 */
	public static void showAlert(final Activity _activity, String title,
			String alertMsg, final boolean goBack) {
		AlertDialog.Builder alert = new AlertDialog.Builder(_activity);
		alert.setTitle(title);
		alert.setCancelable(false);
		alert.setMessage(alertMsg);
		alert.setPositiveButton(_activity.getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (goBack)
							_activity.finish();
					}
				});
		alert.show();
	}

	/**
	 * Show alert dialog with OK positive button and will set the provided
	 * listener to this.
	 * 
	 * @param _activity
	 * @param title
	 *            -- Title
	 * @param alertMsg
	 *            -- Message
	 * @param goBack
	 * @param okListener
	 *            -- Listener to set for OK button
	 */
	public static void showAlert(final Activity _activity, String title,
			String alertMsg, final boolean goBack,
			DialogInterface.OnClickListener okListener) {
		AlertDialog.Builder alert = new AlertDialog.Builder(_activity);
		alert.setTitle(title);
		alert.setCancelable(false);
		alert.setMessage(alertMsg);

		if (okListener != null) {
			alert.setPositiveButton(_activity.getString(R.string.ok),
					okListener);
		} else {
			alert.setPositiveButton(_activity.getString(R.string.ok),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (goBack)
								_activity.finish();
						}
					});
		}

		alert.show();
	}

	/**
	 * Show alert dialog with OK positive button and Cancel negative button and
	 * will set the provided listener to this.
	 * 
	 * @param _activity
	 * @param title
	 *            -- Title
	 * @param alertMsg
	 *            -- Message
	 * @param goBack
	 * @param okListener
	 *            -- Listener to set for OK button
	 */
	public static void showCancelAlert(final Activity _activity, String title,
			String alertMsg, final boolean goBack,
			DialogInterface.OnClickListener okListener,
			final boolean showNegativeButton) {
		AlertDialog.Builder alert = new AlertDialog.Builder(_activity);
		alert.setTitle(title);
		alert.setCancelable(false);
		alert.setMessage(alertMsg);
		if (showNegativeButton) {
			alert.setNegativeButton(_activity.getString(R.string.no),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
		}
		if (okListener != null) {
			alert.setPositiveButton(_activity.getString(R.string.yes),
					okListener);
		} else {
			alert.setPositiveButton(_activity.getString(R.string.yes),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (goBack)
								_activity.finish();
						}
					});
		}

		alert.show();
	}

	/**
	 * Check whether the internet connection is present or not. <uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE" />
	 */
	// To check whether network connection is available on device or not
	public static boolean checkInternetConnection(Activity _activity) {
		ConnectivityManager conMgr = (ConnectivityManager) _activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conMgr.getActiveNetworkInfo() != null
				&& conMgr.getActiveNetworkInfo().isAvailable()
				&& conMgr.getActiveNetworkInfo().isConnected())
			return true;
		else
			return false;
	}

	/**
	 * Delete value from SharedPreference for the given key
	 */
	public static void deleteFromSP(Activity _activity, String key) {
		SharedPreferences preferences = _activity.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.remove(key);
		editor.commit();
	}

	/**
	 * Retrieve string value from SharedPreference for the given key
	 */
	public static String getStringFromSP(Context _activity, String key) {
		SharedPreferences preferences = _activity.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		return preferences.getString(key, null);
	}

	/**
	 * Retrieve boolean value from SharedPreference for the given key
	 */
	public static boolean getBooleanFromSP(Activity _activity, String key) {
		SharedPreferences preferences = _activity.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		return preferences.getBoolean(key, false);
	}

	/**
	 * Retrieve integer value from SharedPreference for the given key
	 */
	public static int getIntFromSP(Activity _activity, String key) {
		SharedPreferences preferences = _activity.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		return preferences.getInt(key, 0);
	}

	/**
	 * Save string value from SharedPreference for the given key
	 */
	public static void saveStringInSP(Context mContext, String key, String value) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * Retrieve boolean value from SharedPreference for the given key
	 */
	public static void saveBooleanInSP(Activity _activity, String key,
			boolean value) {
		SharedPreferences preferences = _activity.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * Retrieve boolean value from SharedPreference for the given key
	 */
	public static void saveIntInSP(Activity _activity, String key, int value) {
		SharedPreferences preferences = _activity.getSharedPreferences(
				Constants.SHARED_PREFERENCES_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * Make a call to the specific number also if the number is any USSD code
	 * then dials the USSD code. <uses-permission
	 * android:name="android.permission.CALL_PHONE" />
	 */
	/*public static void callNumber(Activity _activity, String number) {
		try {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			if (number.contains("#")) {
				callIntent.setData(Uri.parse("tel:"
						+ number.replaceAll("#", Uri.encode("#"))));
			} else {
				callIntent.setData(Uri.parse("tel:" + number));
			}
			_activity.startActivity(callIntent);
		} catch (ActivityNotFoundException activityException) {
			if (Constants.LOG)
				Log.e("helloandroid dialing example", "Call failed",
						activityException);
		}
	}
*/
	/**
	 * Send mail for the given mail address.
	 */
	public static void sendEMail(Activity _activity, String emailId,
			String subject, String body, Uri attachmentUri) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("message/rfc822");
		if (subject != null)
			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		if (body != null)
			intent.putExtra(Intent.EXTRA_TEXT, body);
		if (emailId != null)
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailId });
		if (attachmentUri != null)
			intent.putExtra(Intent.EXTRA_STREAM, attachmentUri);
		_activity.startActivity(Intent.createChooser(intent,
				"Send email via..."));
	}

	/**
	 * Convert the given String to Date object.
	 */
	public static Date ConvertStingtoDate(String date, String dateFormat)
			throws ParseException {
		SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(dateFormat,
				Locale.ENGLISH);
		System.out.println("" + date);
		return _simpleDateFormat.parse(date);
	}

	/**
	 * This method validates the given emailID based on the common email
	 * pattern.
	 *
	 * @param emailstring
	 *            input email ID
	 * @return true - if input emailID is in valid pattern, false - otherwise.
	 */
	public static boolean isValidEmail(String emailstring) {
		Pattern emailPattern = Pattern
				.compile("^([a-zA-Z0-9])+([\\.a-zA-Z0-9_-])*@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-]+)+$");
		Matcher emailMatcher = emailPattern.matcher(emailstring);
		return emailMatcher.matches();

	}

	/**
	 * Used to Get Device GPS Location <uses-permission
	 * android:name="android.permission.ACCESS_FINE_LOCATION">
	 */
	/*public static LocationResult locationResult = new LocationResult() {
		@Override
		public void gotLocation(Location location) {

		}
	};
*/
	/**
	 * Download the image as drawable from the given url if net connection is
	 * present. <uses-permission android:name="android.permission.INTERNET" />
	 */
	public static Drawable getImage(String urlString) throws IOException {
		Log.i("url", urlString);
		URL url = new URL(urlString);
		InputStream is = url.openStream();
		Bitmap bitmap = BitmapFactory.decodeStream(new FlushedInputStream(is));
		Drawable image = new BitmapDrawable(bitmap);
		return image;
	}// getImage

	public static boolean isValidIP(String ip) {
		try {
			if (ip == null || ip.isEmpty() || !ip.contains("\\.")) {
				return false;
			}

			String[] parts = ip.split("\\.");
			if (parts.length != 4) {
				return false;
			}

			for (String s : parts) {
				int i = Integer.parseInt(s);
				if ((i < 0) || (i > 255)) {
					return false;
				}
			}
			return true;
		} catch (Exception nfe) {
			return false;
		}
	}

	/*
	 * opens a Soap Connection Using KSoap2
	 */
	public static SoapObject openSoapRequest(SoapObject request,
											 String SOAP_ACTION, String SERVER_URL) {
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		soapEnvelope.implicitTypes = true;
		soapEnvelope.dotNet = true;
		SoapObject result = null;
		if (Constants.LOG)
			Log.d("Request", "" + request);
		soapEnvelope.setOutputSoapObject(request);
		AndroidHttpTransport aht = new AndroidHttpTransport(SERVER_URL);
		aht.debug = true;
		// SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		// int storedPreference = preferences.getInt("storedInt", 0);

		try {
			if (Constants.LOG)
				Log.d("request is", "" + request);
			if (Constants.LOG)
				Log.d("SOAP_ACTION::" + SOAP_ACTION, "SERVER_URL::"
						+ SERVER_URL);
			aht.call(SOAP_ACTION, soapEnvelope);
			if (Constants.LOG)
				Log.d("Request", "" + aht.requestDump);
			result = (SoapObject) soapEnvelope.getResponse();
			if (Constants.LOG)
				Log.d("Response", "" + aht.responseDump);

		} catch (UnknownHostException e) {
			if (Constants.LOG)
				Log.d("UnknownHostException", "" + e);
			result = new SoapObject("", "");
			result.addProperty(Constants.TEXT_FAULT,
					Constants.ERROR_UNABLE_TO_CONNECT_SERVER);
			if (Constants.LOG)
				Log.d("Exception in com.vl.ontheway.components.util", e.getMessage());

		} catch (IOException e) {

			if (Constants.LOG)
				Log.d("IOException", "" + e);
			result = new SoapObject("", "");
			result.addProperty(Constants.TEXT_FAULT, Constants.ERROR_IO);
			e.printStackTrace();

		} catch (XmlPullParserException e) {

			if (Constants.LOG)
				Log.d("XmlPullParserException", "" + e);
			result = new SoapObject("", "");
			result.addProperty(Constants.TEXT_FAULT,
					Constants.ERROR_PARSER_EXCEPTION);
			e.printStackTrace();

		} catch (Exception e) {
			if (Constants.LOG)
				Log.d("Exception", "" + e);
			result = new SoapObject("", "");
			result.addProperty(Constants.TEXT_FAULT, Constants.ERROR_EXCEPTION);
			e.printStackTrace();

		}
		return result;
	}

	/**
	 * set date to calendar object
	 *
	 * @param string DateFormat
	 * @param //Date date
	 * @return
	 */
	public static Date setDateToCalendar(String string, Date date) {
		DateFormat dateFormat = new SimpleDateFormat(string);
		System.out.println(dateFormat.format(date));

		Date date11 = new Date(dateFormat.format(date));
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date11);
		date11 = cal1.getTime();
		return date11;
	}

	/**
	 * Convert the response InputStream in to String response
	 *
	 * @param is response-stream
	 * @return xml string
	 */
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			is.close();
		} catch (OutOfMemoryError om) {
			om.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sb.toString();
	}

	/*public static HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			HttpParams httpParams = new BasicHttpParams();
			HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
			HttpConnectionParams.setConnectionTimeout(httpParams,
					Constants.HTTP_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpParams,
					Constants.SOCKET_TIMEOUT);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));
			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					httpParams, registry);
			return new DefaultHttpClient(ccm, httpParams);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}*/// getNewHttpClient()

//	public static InputStream postHTTPS_request(String strUrl, String reqData,
//			String soapAction) {
//		InputStream iStream = null;
//		String result = "";
//		HttpParams httpParams = null;
//		HttpPost request = null;
//		HttpClient client = null;
//		if (Constants.LOG)
//			Log.d("postHTTPS_request:::URL::" + strUrl, ":::SoapAction::"
//					+ soapAction + ":::Req Data::" + reqData);
//		try {
//			// set up the connection
//			httpParams = new BasicHttpParams();
//			HttpConnectionParams.setConnectionTimeout(httpParams,
//					Constants.HTTP_TIMEOUT);
//			HttpConnectionParams.setSoTimeout(httpParams,
//					Constants.SOCKET_TIMEOUT);
//			client = getNewHttpClient();
//			// connect to the server
//			request = new HttpPost(strUrl);
//			StringEntity se = new StringEntity(reqData);
//			request.setHeader(
//					"User-Agent",
//					"Mozilla/5.0 (X11; U; Linux "
//							+ "i686; en-US; rv:1.8.1.6) Gecko/20061201 Firefox/2.0.0.6 (Ubuntu-feisty)");
//			request.setHeader(
//					"Accept",
//					"text/html,application/xml,"
//							+ "application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//			request.setHeader("Content-Type", "text/xml; charset=utf-8");
//			request.setHeader("SOAPAction", soapAction);
//			request.setEntity(se);
//
//			// fetch the response
//			HttpResponse response = client.execute(request);
//			HttpEntity entity = response.getEntity();
//			iStream = entity.getContent();
//		} catch (MalformedURLException e) {
//			if (Constants.LOG)
//				Log.d("postHTTPS_request", ".execute() ? exception", e);
//			e.printStackTrace();
//			iStream = null;
//		} catch (HttpResponseException e) {
//			// implement retry behavior
//			if (Constants.LOG)
//				Log.d("postHTTPS_request", ".execute() ? exception", e);
//			e.printStackTrace();
//			iStream = null;
//		} catch (SSLException e) {
//			if (Constants.LOG)
//				Log.d("postHTTPS_request", ".execute() ? exception", e);
//			e.printStackTrace();
//			iStream = null;
//		} catch (IOException e) {
//			if (Constants.LOG)
//				Log.d("postHTTPS_request", ".execute() ? exception", e);
//			e.printStackTrace();
//			iStream = null;
//		} catch (Exception e) {
//			if (Constants.LOG)
//				Log.d("postHTTPS_request", ".execute() ? exception", e);
//			e.printStackTrace();
//			iStream = null;
//		} finally {
//			request = null;
//			client = null;
//			httpParams = null;
//		}
//		return iStream;
//	}// postHTTPS_request()
//
//	public static void getGPS(Activity _activity) {
//		MyLocation myLocation = new MyLocation();
//		myLocation.getLocation(_activity, locationResult);
//	}
//
//	static HttpParams httpParameters;
//	static {
//		httpParameters = new BasicHttpParams();
//		// Set the timeout in milliseconds until a connection is established.
//		HttpConnectionParams.setConnectionTimeout(httpParameters,
//				Constants.HTTP_TIMEOUT);
//
//		HttpConnectionParams.setSoTimeout(httpParameters,
//				Constants.SOCKET_TIMEOUT);
//	}
//
//	public static InputStream postPage(String url, String data) {
//		String ret = null;
//		BasicHttpContext localContext = new BasicHttpContext();
//		DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
//		HttpPost httpPost = new HttpPost(url);
//		HttpResponse response = null;
//		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
//				CookiePolicy.RFC_2109);
//		StringEntity tmp = null;
//
//		httpPost.setHeader(
//				"User-Agent",
//				"Mozilla/5.0 (X11; U; Linux "
//						+ "i686; en-US; rv:1.8.1.6) Gecko/20061201 Firefox/2.0.0.6 (Ubuntu-feisty)");
//		httpPost.setHeader(
//				"Accept",
//				"text/html,application/xml,"
//						+ "application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
//
//		try {
//			tmp = new StringEntity(data, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage UnsupportedEncodingException Ex::" + e);
//		}
//
//		httpPost.setEntity(tmp);
//
//		try {
//			response = httpClient.execute(httpPost, localContext);
//		} catch (ClientProtocolException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage ClientProtocolException Ex::" + e);
//		} catch (IOException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage IOException Ex::" + e);
//		}
//
//		ret = response.getStatusLine().toString();
//		if (Constants.LOG)
//			Log.d("UTIL", "postPage STATUS::" + ret);
//		HttpEntity he = response.getEntity();
//		InputStream inStream = null;
//
//		try {
//			inStream = he.getContent();
//			if (Constants.LOG)
//				Log.d("UTIL", "isStream available::" + inStream.available());
//		} catch (IllegalStateException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage IllegalStateException1 Ex::" + e);
//		} catch (IOException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage IOException1 Ex::" + e);
//		}
//
//		return inStream;
//	}
//
//	public static String postPageJson(String url, String data, String key[],
//			String value[]) {
//		if (Constants.LOG)
//			Log.v("Search results", url);
//		if (Constants.LOG)
//			Log.d("Request Data", "" + data);
//
//		String ret = null;
//		BasicHttpContext localContext = new BasicHttpContext();
//		DefaultHttpClient httpClient = (DefaultHttpClient) getNewHttpClient();
//		HttpPost httpPost = new HttpPost(url);
//		HttpResponse response = null;
//		// Constants.RESPONSE_CODE = -1;
//		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
//				CookiePolicy.RFC_2109);
//		StringEntity tmp = null;
//		httpPost.setHeader(
//				"User-Agent",
//				"Mozilla/5.0 (X11; U; Linux "
//						+ "i686; en-US; rv:1.8.1.6) Gecko/20061201 Firefox/2.0.0.6 (Ubuntu-feisty)");
//		httpPost.setHeader(
//				"Accept",
//				"text/html,application/xml,application/json"
//						+ "application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//		// httpPost.setHeader("Content-Type",
//		// "application/x-www-form-urlencoded");
//		httpPost.setHeader("Content-Type", "application/json");
//
//		if (key != null) {
//			for (int index = 0; index < key.length; index++) {
//				httpPost.setHeader(key[index], value[index]);
//			}
//		}
//
//		if (data != null) {
//			try {
//				tmp = new StringEntity(data, "UTF-8");
//				httpPost.setEntity(tmp);
//			} catch (UnsupportedEncodingException e) {
//				if (Constants.LOG)
//					Log.d("UTIL", "postPage UnsupportedEncodingException Ex::"
//							+ e);
//			}
//
//		}
//
//		InputStream inStream = null;
//
//		try {
//			response = httpClient.execute(httpPost, localContext);
//			// Constants.RESPONSE_CODE =
//			// response.getStatusLine().getStatusCode();
//			ret = response.getStatusLine().toString();
//
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage STATUS::" + ret);
//
//			return EntityUtils.toString(response.getEntity());
//
//		} catch (ClientProtocolException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage ClientProtocolException Ex::" + e);
//		} catch (IOException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage IOException Ex::" + e);
//		}
//
//		catch (IllegalStateException e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage IllegalStateException1 Ex::" + e);
//		} catch (Exception e) {
//			if (Constants.LOG)
//				Log.d("UTIL", "postPage Exception" + e);
//		}
//
//		return null;
//	}

	/**
	 * Removes zeros after decimal
	 *
	 * @param val
	 * @return
	 */
	public static String removeZeroAfterDecimal(String val) {
		try {
			String valAfterRemovingZero = null;
			if (val.contains(".")
					&& String.valueOf(val.charAt((val.length() - 1)))
					.equalsIgnoreCase("0")) {
				valAfterRemovingZero = String.valueOf(Math.round(Float
						.parseFloat(val)));
			} else if (!(val.contains("."))) {
				valAfterRemovingZero = val;
			} else if (val.length() == 0)
				valAfterRemovingZero = "0";
			else {
				// valAfterRemovingZero = String.valueOf(Float.parseFloat(val));
				double rounded = (double) Math
						.round(Double.parseDouble(val) * 10) / 10;
				String value = Double.toString(rounded);
				if (String.valueOf(value.charAt(value.length() - 1))
						.equalsIgnoreCase("0")) {
					valAfterRemovingZero = value.split("\\.")[0];
				} else {
					valAfterRemovingZero = value;
				}
			}
			return valAfterRemovingZero;
		} catch (Exception e) {
			return val;
		}
	}

//	public static DatePickerDialog makeDatePicker(Calendar c,
//			Activity mActivity,
//			DatePickerDialog.OnDateSetListener dateSetListener, boolean isMin,
//			Date date, boolean isMax, Date maxDate) {
//		if (c == null) {
//			c = Calendar.getInstance();
//		}
//		int year = c.get(Calendar.YEAR);
//		int month = c.get(Calendar.MONTH);
//		int day = c.get(Calendar.DAY_OF_MONTH);
//
//		DatePickerDialog pickerDialog = new DatePickerDialog(mActivity,
//				dateSetListener, year, month, day);
//		if (isMin) {
//			Date date11 = setDateToCalendar(Constants.BOOKING_DATE_FORMAT, date);
//			pickerDialog.getDatePicker().setMinDate(date11.getTime());
//		}
//		// else{
//		// newFragment.getDatePicker().setMaxDate(new Date().getTime());
//		// }
//		if (isMax) {
//			Date date11 = setDateToCalendar("MM/dd/yyyy", maxDate);
//			pickerDialog.getDatePicker().setMaxDate(date11.getTime());
//		}
//
//		// removes the original topbar:
//		pickerDialog.setTitle("");
//		pickerDialog.getDatePicker().setCalendarViewShown(false);
//
//		// Divider changing:
//		DatePicker dpView = pickerDialog.getDatePicker();
//		LinearLayout llFirst = (LinearLayout) dpView.getChildAt(0);
//		LinearLayout llSecond = (LinearLayout) llFirst.getChildAt(0);
//
//		// New top:
//		int titleHeight = (int) mActivity.getResources().getDimension(
//				R.dimen.spiner_height);
//		// Container:
//		LinearLayout llTitleBar = new LinearLayout(mActivity);
//		llTitleBar.setOrientation(LinearLayout.VERTICAL);
//		llTitleBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//				titleHeight));
//
//		// TextView Title:
//		TextView tvTitle = new TextView(mActivity);
//		tvTitle.setText("Select a date");
//		tvTitle.setGravity(Gravity.CENTER);
//		tvTitle.setPadding(10, 10, 10, 10);
//		tvTitle.setTextSize(24);
//		tvTitle.setTextColor(mActivity.getResources().getColor(
//				R.color.app_theme_red));
//		tvTitle.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
//				titleHeight - 2));
//		llTitleBar.addView(tvTitle);
//
//		// View line:
//		View vTitleDivider = new View(mActivity);
//		vTitleDivider.setLayoutParams(new LayoutParams(
//				LayoutParams.MATCH_PARENT, 2));
//		vTitleDivider.setBackgroundColor(mActivity.getResources().getColor(
//				R.color.app_theme_red));
//		llTitleBar.addView(vTitleDivider);
//
//		dpView.addView(llTitleBar);
//		FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) llFirst
//				.getLayoutParams();
//		lp.setMargins(0, titleHeight, 0, 0);
//
//		pickerDialog.show();
//		return pickerDialog;
//
//	}

	/**
	 * This method is used to convert required date string object
	 *
	 * @param dateString
	 *            original date string
	 * @return dateFormat required format date string
	 */
	public static String getDateFormattedString(String originalFormat,
												String dateString, String dateFormat) {
		SimpleDateFormat mTargetFormat = new SimpleDateFormat(dateFormat,
				Locale.ENGLISH);
		SimpleDateFormat mOriginalFormat = new SimpleDateFormat(originalFormat,
				Locale.ENGLISH);

		String reqstring = null;
		try {
			reqstring = mTargetFormat.format(mOriginalFormat.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reqstring;
	}

	// public static Date convertStringToDate(String dateString, String
	// dateFormat ) {
	// Date date = null;
	// SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	// try {
	// date = format.parse(dateString);
	// System.out.println(date);
	// } catch (ParseException e) {
	// e.printStackTrace();
	// }
	// return date;
	// }

	/**
	 * This method uses the GET Method for connecting to the server
	 *
	 * @param url
	 * @param headers
	 *            -- Headers to include if any. Otherwise null.
	 * @param classTag
	 *            -- Class Name/Tag to use while logging.
	 */
//	public static String httpGetConnection(String url,
//			Map<String, String> headers) {
//
//		HttpParams httpParameters;
//		try {
//			if (Constants.LOG)
//				Log.e("", "URL::::::::: " + url);
//
//			httpParameters = new BasicHttpParams();
//			HttpGet request = new HttpGet(url);
//
//			request.setHeader(
//					"User-Agent",
//					"Mozilla/5.0 (X11; U; Linux "
//							+ "i686; en-US; rv:1.8.1.6) Gecko/20061201 Firefox/2.0.0.6 (Ubuntu-feisty)");
//			request.setHeader(
//					"Accept",
//					"text/html,application/xml,application/json"
//							+ "application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
//			request.setHeader("Content-Type",
//					"application/x-www-form-urlencoded");
//
//			if (headers != null && headers.size() > 0) {
//				for (String key : headers.keySet()) {
//					request.addHeader(key, headers.get(key));
//				}
//			}
//
//			HttpConnectionParams.setSoTimeout(httpParameters,
//					Constants.HTTP_TIMEOUT);
//			DefaultHttpClient client = (DefaultHttpClient) getNewHttpClient();
//
//			HttpResponse response = client.execute(request);
//
//			return EntityUtils.toString(response.getEntity());
//		}
//
//		catch (Exception e) {
//			if (Constants.LOG && e != null) {
//				e.printStackTrace();
//				if (Constants.LOG)
//					Log.e("", Constants.ERROR + " :::: " + e.getMessage());
//			}
//		}
//
//		return null;
//	}

	public static String getDateAfter6MonthsFromCurrentDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 6);

		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.SIMPLE_DATE_FORMAT);
		if (Constants.LOG)
			Log.d("", "Date After 6Months " + sdf.format(cal.getTime()));

		return sdf.format(cal.getTime());

	}

	/**
	 * Calculates the time between departure and arriaval time.
	 *
	 * @paramtimeInfo
	 * @return The time of the flight journey
	 */
	public static String getTravelTime(String endDate, String startDate,
									   String currentFormat) {
		String travelTime = "";
		try {
			if (startDate != null && endDate != null && currentFormat != null) {

				// Calculate the difference in hours b/w departure and arrival
				// times
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						currentFormat, Locale.ENGLISH);

				Date date1 = simpleDateFormat.parse(startDate);
				Date date2 = simpleDateFormat.parse(endDate);

				long diff = date1.getTime() - date2.getTime();

				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;

				// Set the difference to UI
				travelTime = diffHours + "H" + " " + diffMinutes + "M";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return travelTime;
	}

//	public static double countTotalFare(PriceSummaryBean priceBean, double count) {
//		try {
//			double meal = 0;
//			double excess = 0;
//			double baggage = 0;
//			double assurance = 0;
//			double seats = 0;
//
//			double adultfare = Double.parseDouble(priceBean
//					.getmAdultRegularFair());
//			double childfare = Double.parseDouble(priceBean
//					.getmChildRegularFair());
//			double infantfare = Double.parseDouble(priceBean
//					.getmInfantRegularFair());
//			if (priceBean.getmMealsFair() != null
//					&& !priceBean.getmMealsFair().equalsIgnoreCase("")) {
//				meal = Double.parseDouble(priceBean.getmMealsFair());
//			}
//
//			if (priceBean.getmExcessFair() != null
//					&& !priceBean.getmExcessFair().equalsIgnoreCase("")) {
//				excess = Double.parseDouble(priceBean.getmExcessFair());
//			}
//
//			if (priceBean.getmBaggageFair() != null
//					&& !priceBean.getmBaggageFair().equalsIgnoreCase("")) {
//				baggage = Double.parseDouble(priceBean.getmBaggageFair());
//			}
//			if (priceBean.getSpiceAssurance() != null
//					&& !priceBean.getSpiceAssurance().equalsIgnoreCase("")) {
//				assurance = Double.parseDouble(priceBean.getSpiceAssurance());
//			}
//			if (priceBean.getSeats() != null
//					&& !priceBean.getSeats().equalsIgnoreCase("")) {
//				seats = Double.parseDouble(priceBean.getSeats());
//			}
//
//			count = count + adultfare + childfare + infantfare + meal + baggage
//					+ excess + assurance + seats;
//			ArrayList<TaxFeesBean> taxList = priceBean.getmTaxFees();
//
//			for (int i = 0; i < taxList.size(); i++) {
//				TaxFeesBean taxBean = taxList.get(i);
//				double fare = Double.parseDouble(taxBean.getmTotalAmount());
//				count = count + fare;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return count;
//	}
//
//	/**
//	 * To clear baggage,meals, assurance cost from billing when navigating back
//	 * from Add-ons Screen
//	 *
//	 * @param fragment
//	 */
//	public static void clearAddonsSplitFare() {
//		BillingBean bean = BillingBean.getInstance();
//		PriceSummaryBean priceSummaryBean;
//		// if (fragment instanceof AddonsPagerFragment && fragment.isAdded()) {
//		if (bean.getOnwardJouneyBean() != null
//				&& bean.getOnwardPriceBean() != null) {
//			priceSummaryBean = bean.getOnwardPriceBean();
//			priceSummaryBean.setmBaggageFair("0.0");
//			priceSummaryBean.setmExcessFair("0.0");
//			priceSummaryBean.setmMealsFair("0.0");
//			priceSummaryBean.setSpiceAssurance("0");
//
//			priceSummaryBean.setSeats("0");
//
//			if (bean.getPassengerBeanAL() != null
//					&& bean.getPassengerBeanAL().size() > 0) {
//				for (int i = 0; i < bean.getPassengerBeanAL().size(); i++) {
//					bean.getPassengerBeanAL().get(i).clearMeals();
//					bean.getPassengerBeanAL().get(i)
//							.setOnwardSeatsSelectedInChangeFlight(false);
//				}
//			}
//			bean.setOnwardBGBaggegesBean(null);
//
//		}
//		if (bean.getReturnJouneyBean() != null
//				&& bean.getReturnPriceBean() != null) {
//			priceSummaryBean = bean.getReturnPriceBean();
//			priceSummaryBean.setmBaggageFair("0.0");
//			priceSummaryBean.setmExcessFair("0.0");
//			priceSummaryBean.setmMealsFair("0.0");
//			priceSummaryBean.setSpiceAssurance("0");
//			priceSummaryBean.setSeats("0");
//			if (bean.getPassengerBeanAL() != null
//					&& bean.getPassengerBeanAL().size() > 0) {
//				for (int i = 0; i < bean.getPassengerBeanAL().size(); i++) {
//					bean.getPassengerBeanAL().get(i).clearMeals();
//					bean.getPassengerBeanAL().get(i)
//							.setReturnSeatsSelectedInChangeFlight(false);
//				}
//			}
//			bean.setReturnBGBaggegesBean(null);
//		}
//		// }
//
//	}
//
//	/**
//	 * Clears data related to excess baggage
//	 */
//	private static void clearExcessBaggeData() {
//		BillingBean bean = BillingBean.getInstance();
//		ArrayList<PassengerBean> passengerBeanAL = bean.getPassengerBeanAL();
//		for (int index = 0; index < passengerBeanAL.size(); index++) {
//			PassengerBean passengerBean = passengerBeanAL.get(index);
//			passengerBean.clearBaggage();
//		}
//	}

	public static boolean isEmpty(JSONObject jsonObj, String tagName) {

		try {
			if (jsonObj != null && jsonObj.has(tagName)) {
				String json = jsonObj.getString(tagName);
				return json.equalsIgnoreCase("")
						|| json.equalsIgnoreCase("null");
			} else {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String checkDigit(int number) {
		return number <= 9 ? "0" + number : String.valueOf(number);
	}

	/**
	 * Returns dates between from and to date
	 *
	 * @param fromDate
	 * @param toDate
	 * @return Dates List
	 */
	public static List<Date> getInBetweenDates(Date fromDate, Date toDate) {

		List<Date> datesList = new ArrayList<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.SIMPLE_DATE_FORMAT);

		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);

		while (cal.getTime().before(toDate)) {

			String dateStr = sdf.format(cal.getTime());
			try {
				Date formatedDate = sdf.parse(dateStr);
				datesList.add(formatedDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			cal.add(Calendar.DATE, 1);
		}

		return datesList;
	}

	public static String getDuration(String startTime, String endTime) {

		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.ITINERARY_DATE_FORMAT);
		try {

			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);

			long dif = endDate.getTime() - startDate.getTime();
			Date difDate = new Date(dif);

			SimpleDateFormat durationSDF = new SimpleDateFormat(
					Constants.TIME_FORMAT);
			String duration = durationSDF.format(difDate);
			return duration;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String getMonth(String date) {

		DateFormat inputDF = new SimpleDateFormat(Constants.ISSUE_DATE_FORMAT);
		Date date1;
		Calendar cal = null;
		try {
			date1 = inputDF.parse(date);
			cal = Calendar.getInstance();
			cal.setTime(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int month = cal.get(Calendar.MONTH);

		return new DateFormatSymbols().getMonths()[month];
	}

//	public static StatusBean getStatusBean(JSONObject jsonObj) {
//		StatusBean bean = null;
//		try {
//			bean = new StatusBean();
//			if (jsonObj.has("IsSuccess")) {
//				Object object = jsonObj.get("IsSuccess");
//				if (object instanceof JSONObject) {
//					bean.setSuceess(true);
//				} else {
//					bean.setSuceess(jsonObj.getBoolean("IsSuccess"));
//				}
//			}
//			if (jsonObj.has("Signature")) {
//				bean.setSignature(jsonObj.getString("Signature"));
//			}
//			if (jsonObj.has("ErrorMessage")) {
//				bean.setErrorMessage(jsonObj.getString("ErrorMessage"));
//			}
//
//			if (jsonObj.has("MessageCode")) {
//				bean.setErrorCode(jsonObj.getString("MessageCode"));
//			}
//			if (jsonObj.has("IsSessionExpired")) {
//				bean.setExpired(jsonObj.getBoolean("IsSessionExpired"));
//			}
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return bean;
//	}

	public static String getDay(String date) {
		DateFormat inputDF = new SimpleDateFormat(Constants.ISSUE_DATE_FORMAT);
		Date date1;
		Calendar cal = null;
		try {
			date1 = inputDF.parse(date);
			cal = Calendar.getInstance();
			cal.setTime(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int day = cal.get(Calendar.DAY_OF_MONTH);

		return day + "";
	}

	public static String getYear(String date) {
		DateFormat inputDF = new SimpleDateFormat(Constants.ISSUE_DATE_FORMAT);
		Date date1;
		Calendar cal = null;
		try {
			date1 = inputDF.parse(date);
			cal = Calendar.getInstance();
			cal.setTime(date1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int year = cal.get(Calendar.YEAR);

		return year + "";
	}

	/****
	 * Method for Setting the Height of the ListView dynamically. Hack to fix
	 * the issue of not showing all the items of the ListView when placed inside
	 * a ScrollView
	 ****/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
			return;

		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
				MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			view = listAdapter.getView(i, view, listView);
			if (i == 0)
				view.setLayoutParams(new LayoutParams(desiredWidth,
						LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	/**
	 * Returns the age.
	 *
	 * @param dateOfBirth
	 * @return
	 */
	public static int getAge(String dateOfBirth) {
		int age = 0;

		return getAge(dateOfBirth, "dd-MMM-yyyy");

	}

	/**
	 * Returns the age.
	 *
	 * @param dateOfBirth
	 * @return
	 */
	public static int getAge(String dateOfBirth, String dateFormat) {
		int age = 1;

		if (dateOfBirth == null || dateFormat == null)
			return age;

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					dateFormat, Locale.ENGLISH);
			Date date = simpleDateFormat.parse(dateOfBirth);

			Calendar dob = Calendar.getInstance();
			Calendar today = Calendar.getInstance();

			dob.setTime(date);
			age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

			// If Today's date of Year is greater than DOB's date of year, then
			// increase his age.
			if (today.get(Calendar.DAY_OF_YEAR) >= dob
					.get(Calendar.DAY_OF_YEAR)) {
				age++;
			}

			if (age < 0)
				age = 1;

			if (Constants.LOG)
				Log.d("", "passenger age " + age);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;

	}

	public static Animation inFromRightAnimation() {
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(300);
		inFromRight.setInterpolator(new AccelerateInterpolator());
		return inFromRight;
	}

	public static Animation outToLeftAnimation() {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(1000);
		outtoLeft.setInterpolator(new AccelerateInterpolator());
		return outtoLeft;
	}

	public static Animation outToRightAnimation() {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoRight.setDuration(1000);
		outtoRight.setInterpolator(new AccelerateInterpolator());
		return outtoRight;
	}

	public static Animation inFromLeftAnimation() {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromLeft.setDuration(300);
		inFromLeft.setInterpolator(new AccelerateInterpolator());
		return inFromLeft;
	}

	public static String getDeviceUniqueID(Context context) {

		String deviceId = Util.getStringFromSP(context, Constants.SP_DEVICE_ID);
		if (!TextUtils.isEmpty(deviceId)) {
			if (Constants.LOG) {
				Log.v("DEVICE ID", "Device Id " + deviceId);
			}
			return deviceId;
		}

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String networkOperator = telephonyManager.getNetworkOperatorName();

		if (networkOperator.equalsIgnoreCase("Android")) {
			return "";
		}

		String uniqueID = telephonyManager.getDeviceId();

		// If IMEI number is not found, i.e for devices with no SIM card, then
		// take the Wifi Mac Address
		if (TextUtils.isEmpty(uniqueID)) {
			uniqueID = getDeviceMacAddress(context);
		}

		// If Mac Address is also not available, then take the Android Unique ID
		if (TextUtils.isEmpty(uniqueID)) {
			uniqueID = getDeviceAndroidID(context);
		}

		if (Constants.LOG) {
			Log.v("DEVICE ID", "Device Id " + uniqueID);
			Log.v("DEVICE TYPE", "Device Type " + networkOperator);
		}

		Util.saveStringInSP(context, Constants.SP_DEVICE_ID, uniqueID);
		return uniqueID;
	}

	private static String getDeviceMacAddress(Context context) {
		WifiManager wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		String wlanMacAdd = wm.getConnectionInfo().getMacAddress();

		return wlanMacAdd;
	}

	private static String getDeviceAndroidID(Context context) {
		String androidId = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		return androidId;
	}

	public static String getVersionName(Context context) {
		PackageInfo pInfo;
		try {
			pInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			String version = pInfo.versionName;
			if (Constants.LOG)
				Log.v("APP_VERSION_NAME", "Version " + version);
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String getAssetsFileAsString(Context context,
			String fileInAssets) {

		StringBuilder buf = new StringBuilder();
		InputStream inStream;
		try {
			inStream = context.getAssets().open(fileInAssets);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					inStream, "UTF-8"));
			String str;

			while ((str = in.readLine()) != null) {
				buf.append(str);
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buf.toString();
	}

	/*public static boolean isUserLoggedIn() {
		String username = Util.getStringFromSP(
				SpiceJetApplication.getSpicejetAppInstance(),
				Constants.SP_USERNAME);
		String password = Util.getStringFromSP(
				SpiceJetApplication.getSpicejetAppInstance(),
				Constants.SP_PASSWORD);
		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
			return true;
		}

		return false;
	}
*/
//	public static void writePersonDetailsToFile(PersonBean personBean) {
//		try {
//			File file = new File(SpiceJetApplication.getSpicejetAppInstance()
//					.getFilesDir(), Constants.FILE_PERSON_DETAILS);
//			FileOutputStream fileOut = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			out.writeObject(personBean);
//
//			out.close();
//			fileOut.close();
//
//			if (Constants.LOG)
//				Log.d("DEBUG", "Person details saved in file...");
//		} catch (IOException io) {
//			io.printStackTrace();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	public static PersonBean readPersonDetailsFromFile() {
//		PersonBean personBean = null;
//		try {
//			File file = new File(SpiceJetApplication.getSpicejetAppInstance()
//					.getFilesDir(), Constants.FILE_PERSON_DETAILS);
//			FileInputStream fileIn = new FileInputStream(file);
//			ObjectInputStream in = new ObjectInputStream(fileIn);
//			Object object = in.readObject();
//			if (object instanceof PersonBean) {
//				personBean = (PersonBean) object;
//			}
//
//			in.close();
//			fileIn.close();
//		} catch (IOException io) {
//			io.printStackTrace();
//		} catch (ClassNotFoundException cnf) {
//			cnf.printStackTrace();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//
//		return personBean;
//	}
//
//	public static void clearCompleteData(BaseActivity activity) {
//
//		BillingBean.clear();
//		Util.saveStringInSP(activity, Constants.PNR_KEY, null);
//
//		// Clear billing bean before navigating any new fragment
//		BillingBean.clear();
//		ManageResponseBean.getInstance().clearResponseBean();
//		SpiceJetApplication.setBookedBillingBean(null);
//		activity.getAppContext().clearAncellaries();
//
//		if (activity instanceof BaseNavigationActivity) {
//			// Clear seat response bean
//			((BaseNavigationActivity) activity).setSeatResposeBean(null);
//		}
//	}

//	public static String getPaxType(int age) {
//		String paxType = "";
//		if (age > Constants.AGE_LIMIT_CHILD) {
//			paxType = PaxEnum.ADULT.toString();
//		} else if (age <= Constants.AGE_LIMIT_INFANT) {
//			paxType = PaxEnum.INFANT.toString();
//		} else {
//			paxType = PaxEnum.CHILD.toString();
//		}
//		return paxType;
//	}

	public static boolean isDevice(Context context) {

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String networkOperator = telephonyManager.getNetworkOperatorName();

		if (networkOperator.equalsIgnoreCase("Android")) {
			return false;
		}

		return true;

	}

	/**
	 * Returns the resolution of device in LDPI, MDPI, HDPI etc..
	 *
	 * @param context
	 * @return empty if the device dose not fall in any of the available
	 *         resolutions as of now.
	 */
	public static String getDeviceResolution(Context context) {
		int density = context.getResources().getDisplayMetrics().densityDpi;

		switch (density) {
		case DisplayMetrics.DENSITY_LOW:
			return "ldpi";

		case DisplayMetrics.DENSITY_MEDIUM:
			return "mdpi";

		case DisplayMetrics.DENSITY_HIGH:
			return "hdpi";

		case DisplayMetrics.DENSITY_XHIGH:
			return "xhdpi";

		case DisplayMetrics.DENSITY_XXHIGH:
			return "xxhdpi";

		default:
			return "xxhdpi";
		}
	}

	public static void saveBitmapToGallery(Context context, Bitmap bmp,
			String fileName, String desc) {
		MediaStore.Images.Media.insertImage(context.getContentResolver(), bmp,
				fileName, desc);
	}

	public static Bitmap getBitmapFromURL(String link) {
		/*--- this method downloads an Image from the given URL,
		 *  then decodes and returns a Bitmap object
		 ---*/
		try {

			URL url = new URL(link);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(),
					url.getHost(), url.getPort(), url.getPath(),
					url.getQuery(), url.getRef());
			url = uri.toURL();

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);

			return myBitmap;

		} catch (IOException e) {
			e.printStackTrace();
			Log.e("getBmpFromUrl error: ", e.getMessage().toString());
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean savePicture(Bitmap bmp, String filePath) {

		if (Constants.LOG)
			Log.v("Image Path", "" + filePath);

		if (filePath == null) {
			return false;
		}

		File imageFile = new File(filePath);
		if (imageFile != null) {

			if (!imageFile.exists()) {
				try {
					imageFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(imageFile.getPath());
				bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		System.gc();
		return true;
	}

	public static String getOutputDirectoryPath() {

		File mediaStorageDir = null;

		if (mediaStorageDir == null) {
			mediaStorageDir = new File(
					Environment.getExternalStorageDirectory(),
					Constants.FOLDER_NAME);

			if (!mediaStorageDir.exists()) {
				mediaStorageDir.mkdirs();
			}
		}

		if (Constants.LOG)
			Log.v("Directory Path", "" + mediaStorageDir.getPath());

		return mediaStorageDir.getPath();
	}

	/**
	 * Download the image as bitmap from the given url if net connection is
	 * present. <uses-permission android:name="android.permission.INTERNET" />
	 */
	public Bitmap getBitmap(String urlString) {
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-agent", "Mozilla/4.0");
			conn.connect();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		InputStream in = null;
		try {
			in = conn.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return BitmapFactory.decodeStream(in);
	}

	static class FlushedInputStream extends FilterInputStream {
		public FlushedInputStream(InputStream inputStream) {
			super(inputStream);
		}

		@Override
		public long skip(long n) throws IOException {
			long totalBytesSkipped = 0L;
			while (totalBytesSkipped < n) {
				long bytesSkipped = in.skip(n - totalBytesSkipped);
				if (bytesSkipped == 0L) {
					int bytee = read();
					if (bytee < 0) {
						break; // we reached EOF
					} else {
						bytesSkipped = 1; // we read one byte
					}
				}
				totalBytesSkipped += bytesSkipped;
			}
			return totalBytesSkipped;
		}
	}

	/**
	 * postHTTPS request for getting input stream from server.
	 */
	private static class MySSLSocketFactory extends SSLSocketFactory {
		SSLContext sslContext = SSLContext.getInstance("TLS");

		public MySSLSocketFactory(KeyStore truststore)
				throws NoSuchAlgorithmException, KeyManagementException,
				KeyStoreException, UnrecoverableKeyException {
			super(truststore);
			TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain,
											   String authType) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
											   String authType) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sslContext.init(null, new TrustManager[]{tm}, null);
		}

		@Override
		public Socket createSocket(Socket socket, String host, int port,
								   boolean autoClose) throws IOException, UnknownHostException {
			return sslContext.getSocketFactory().createSocket(socket, host,
					port, autoClose);
		}

		@Override
		public Socket createSocket() throws IOException {
			return sslContext.getSocketFactory().createSocket();
		}
	}// MySSLSocketFactory-innerclass

	public static class NullStringToEmptyAdapterFactory<T> implements
			TypeAdapterFactory {

		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

			Class<T> rawType = (Class<T>) type.getRawType();
			if (rawType != String.class) {
				return null;
			}
			return (TypeAdapter<T>) new StringAdapter();
		}
	}

	public static class StringAdapter extends TypeAdapter<String> {

		@Override
		public String read(com.google.gson.stream.JsonReader reader)
				throws IOException {
			if (reader.peek() == JsonToken.NULL) {
				reader.nextNull();
				return "";
			}
			return reader.nextString();
		}

		@Override
		public void write(com.google.gson.stream.JsonWriter writer, String value)
				throws IOException {
			if (value == null) {
				writer.nullValue();
				return;
			}
			writer.value(value);
		}
	}
}
