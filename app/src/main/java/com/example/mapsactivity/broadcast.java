package com.example.mapsactivity;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class broadcast extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){

        if(BluetoothDevice.ACTION_ACL_CONNECTED.equals(intent.getAction()))
        {
            ((MapsActivity)MapsActivity.mContext).marked2(0);
        //  Toast.makeText(context,formatDate, Toast.LENGTH_SHORT).show();
        }
        else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(intent.getAction()))
        {
            ((MapsActivity)MapsActivity.mContext).marked2(1);
          //  Toast.makeText(context,formatDate, Toast.LENGTH_SHORT).show();
        }
    }

}


