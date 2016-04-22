package com.vl.ontheway.components.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.vl.ontheway.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class GPSUtils implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    private Context mContext;
    //private LocationClient mLocationClient;
    private Location mLastLocation;
    private String TAG = "GPS Utils";
    private GPSListener mListener;

    public GPSUtils(Context context, GPSListener listener) {

        mContext = context;
        mListener = listener;
        buildGoogleApiClient();
        //	mLocationClient = new LocationClient(mContext, this, this);

    }

    @SuppressWarnings("deprecation")
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {

    }

    @Override
    public void onConnected(Bundle arg0) {

        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);


        if (mLastLocation != null) {
            if (Constants.LOG)
                Log.e(TAG, "Last Location " + mLastLocation.getLatitude() + " *** " + mLastLocation.getLongitude());
            mListener.onLocationRecieved(mLastLocation);
            handleLocation(mLastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void disconnectLocationClient() {

        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();

    }

    public void connectLocationClient() {

        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();

    }

    public Location getUserLocation() {

        return mLastLocation;

    }

    private void handleLocation(Location location) {
        String errorMessage = "";

        Geocoder geocoder = new Geocoder(mContext, Locale.ENGLISH);
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // In this sample, get just a single address.

            if (addresses != null && addresses.size() > 0) {
                Address adrs = addresses.get(0);
                if (adrs != null) {
                    if (Constants.LOG)
                        Log.v(TAG, adrs.getLocality() + "");
                    Util.saveStringInSP(mContext, Constants.SP_LOCATION, adrs.getLocality());
                }
            }

        } catch (IOException ioException) {
            // Catch network or other I/O problems.
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
            }

        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
        }
    }

    public interface GPSListener {
        void onLocationRecieved(Location loc);
    }

}
