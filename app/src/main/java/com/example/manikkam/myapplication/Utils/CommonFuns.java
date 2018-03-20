package com.example.manikkam.myapplication.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.manikkam.myapplication.R;

import java.io.File;

/**
 * Created by manikkam on 19/3/18.
 */

public class CommonFuns {

    public static final String UploadImagePath= Environment.getExternalStorageDirectory().toString() + File.separator + ".ICanHelp";
    public static final File newFile=new File(UploadImagePath);

    public static final int LOCATION_UPDATE_TIMER=1000*60*15;

    static ProgressBar progress;

    static ProgressDialog mProgressDialog;

    public static boolean isNetworkConnected(Activity context) {

        try {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni == null) {
                // There are no active networks.
                //ShowAlertMessage(context);
                return false;
            } else {
                return true;
            }

        } catch (Exception exp) {

            return false;
        }
    }

    public static boolean isNetworkConnection(Activity context) {

        try {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni == null) {
                return false;
            } else {
                return true;
            }

        } catch (Exception exp) {

            return false;
        }
    }

    public static boolean isNetworkConnectedcontext(Context context) {

        try {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni == null) {
                Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }

        } catch (Exception exp) {

            return false;
        }
    }




    public static String GetDeviceId(Activity activity) {

        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static void showProgressDialog(Activity context) {
        mProgressDialog= ProgressDialog.show(context, context.getResources().getString(R.string.app_name), "Loading Please wait . . ");
    }

    public static void stopProgressDialog(Activity context) {
        if(mProgressDialog!=null)
            if(mProgressDialog.isShowing())
                mProgressDialog.dismiss();
    }


}
