package com.example.mapsactivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_PERMISSIONS = 1000;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLoactionClient;
    public static Context mContext;
    MarkerOptions makerOptions = new MarkerOptions();
    private LocationManager locationManager;
    private static final int REQUEST_CODE_LOCATION = 2;


    double[] x = {37};
    double[] y = {127};
    double[] x1 = {37};
    double[] y1 = {127};

    String dat = null;
    String dat2 = null;
    String dat3 = null;
    String dat4 = null;
    List<Address> addresses = null;
    List<Address> addresses2 = null;

    public final String dbName = "mydb";
    public final String tableName = "location";
    SQLiteDatabase mydb = null;
    public static final String TAG_NAME = "Latitude";
    public static final String TAG_NAME2 = "Longtitude";
    public static final String TAG_NAME3 = "Time";
/*
    public final String tableName2 = "location2";
    public static final String TAG_NAME4 = "Latitude2";
    public static final String TAG_NAME5 = "Longtitude2";
    public static final String TAG_NAME6 = "Time2";
*/
    public static String time = "null";
    public static String time2 = "null";

    public String tr(){
        if(time != null){
            return time;
        }
        else{
            return null;
        }

    }

    public String tr2(){
        if(time2 != null){
            return time2;
        }
        else{
            return null;
        }
    }

    public String ar(){
        if (addresses != null) {
            String[] splitStr = addresses.get(0).toString().split("\"");
            String addr = splitStr[1];//.substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() -2 );
            return addr;
        }
        else{
            return null;
        }
    }
    public String ar2(){
        if (addresses != null) {
            String[] splitStr = addresses2.get(0).toString().split("\"");
            String addr = splitStr[1];//.substring(splitStr[0].indexOf("\"") + 1, splitStr[0].length() -2 );
            return addr;
        }
        else{
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLoactionClient = LocationServices.getFusedLocationProviderClient( this);
        mContext = this;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.339717, 127.262495);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("한국외국어대학교"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

            // Add a marker in Sydney and move the camera

    }
    public void onConnected(@Nullable Bundle bundel){
    }
    public void onConnectionSuspended(int i){
    }
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult){

    }

   // Button sub = (Button)findViewById(R.id.SubActivity)

    public void onLastLocationButtonClicked(View view) {

        Intent intent = new Intent(
                getApplicationContext(),
                SubActivity.class);
        startActivity(intent);
    }

    public void marked2(final int num1){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSIONS);
            return;
        }
        final long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        final Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        final SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // nowDate 변수에 값을 저장한다.
        final String formatDate = sdfNow.format(date);
        final Geocoder mGeoCoder = new Geocoder(mContext);
        mFusedLoactionClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                    if (num1 == 0){
                        x[0] = location.getLatitude();
                        y[0] = location.getLongitude();
                        //  디비에 저장
                        try{
                            mydb = openOrCreateDatabase(dbName, MODE_PRIVATE,null);
                            // 테이블이 존재하지 않으면 새로 생성
                            mydb.execSQL("CREATE TABLE IF NOT EXISTS " + tableName
                                    + " (Latitude DOUBLE(20), Longtitude DOUBLE(20), Time VARCHAR(30));");
                            // 초기화
                            mydb.execSQL("DELETE FROM " + tableName  );
                            mydb.execSQL("INSERT INTO " + tableName
                                    + " (Latitude, Longtitude, Time)  Values ('" + x[0] + "', '" + y[0]+"','" + formatDate+ "');");
                            mydb.close();
                        }
                        catch (SQLiteException se){
                            //  Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
                            //  Log.e("", se.getMessage());
                        }


                        //    mMap.addMarker(new MarkerOptions()
                        //           .position(mylocation)
                        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        //           .title("페어링시작"));
                        //   Toast.makeText(MapsActivity.this, "위도" + x[0] + "경도" + y[0], Toast.LENGTH_LONG).show();
                        //   mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation));
                        //   mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

                    }
                    else{/*
                        x1[0] = location.getLatitude();
                        y1[0] = location.getLongitude();

                        try{
                            mydb = openOrCreateDatabase(dbName, MODE_PRIVATE,null);
                            // 테이블이 존재하지 않으면 새로 생성
                            mydb.execSQL("CREATE TABLE IF NOT EXISTS " + tableName2
                                    + " (Latitude2 DOUBLE(20), Longtitude2 DOUBLE(20), Time2 VARCHAR(30));");
                            mydb.execSQL("DELETE FROM " + tableName2 );
                            mydb.execSQL("INSERT INTO " + tableName2
                                    + " (Latitude2, Longtitude2, Time2)  Values ('" + x1[0] + "', '" + y1[0]+"','" + formatDate+ "');");
                            mydb.close();
                        }
                        catch (SQLiteException se){
                            //  Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
                            //  Log.e("", se.getMessage());
                        }*/

                        try {
                            SQLiteDatabase ReadDB = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
                            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
                            Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);
                            if (c != null) {
                                if (c.moveToFirst()) {
                                    do {
                                        //테이블에서 두개의 컬럼값을 가져와서
                                        x[0] = c.getDouble(c.getColumnIndex("Latitude"));
                                        y[0] = c.getDouble(c.getColumnIndex("Longtitude"));
                                        dat = c.getString(c.getColumnIndex("Time"));
                                    } while (c.moveToNext());
                                }
                            }
                            //mydb.execSQL("DELETE FROM " + tableName );

                            ReadDB.close();
                        }
                        catch (SQLiteException se){
                            // Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
                            // Log.e("", se.getMessage());
                        }
                        // 디비에서 뽑아 와서 2개 점  찍기 테이블의 저장
                        //TextView txtStartAddress2 = (TextView)findViewById(R.id.txtStartAddress2);
                        //String la = Double.toString(x[0]);
                        //txtStartAddress2.setText(la);
                        //t.add(x[0]);
                        //t.add(y[0]);
                        //t.add(mylocation.latitude);
                        //t.add(mylocation.longitude);
                        //time = dat;
                        time = dat;
                        time2 = formatDate;
                        try {
                            addresses = mGeoCoder.getFromLocation(x[0],y[0],10);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            addresses2 = mGeoCoder.getFromLocation(mylocation.latitude,mylocation.longitude,10);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(x[0],y[0]))
                                .snippet(dat)
                                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                .title("페어링 시작"));
                        //Toast.makeText(getApplicationContext(), "vlaue is "+x[0]+y[0], Toast.LENGTH_LONG).show();
                        mMap.addMarker(new MarkerOptions()
                                .position(mylocation)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                .snippet(formatDate)
                                .title("페어링 끝"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(mylocation)); //내추가

                        // table의 값을 집어 넣고 Subjectivity 호출
                        // Toast.makeText(MapsActivity.this, "위도" + mylocation.latitude + "경도" + mylocation.longitude, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    /*
    public void marked3(final int num2){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSIONS);
            return;
        }
        final long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        final Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        final SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        // nowDate 변수에 값을 저장한다.
        final String formatDate = sdfNow.format(date);
        final Geocoder mGeoCoder = new Geocoder(mContext);
        mFusedLoactionClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());
                        try {
                            SQLiteDatabase ReadDB = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
                            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옵니다..
                            Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName, null);
                            if (c != null) {
                                if (c.moveToFirst()) {
                                    do {
                                        //테이블에서 두개의 컬럼값을 가져와서
                                        x[0] = c.getDouble(c.getColumnIndex("Latitude"));
                                        y[0] = c.getDouble(c.getColumnIndex("Longtitude"));
                                        dat3 = c.getString(c.getColumnIndex("Time"));
                                    } while (c.moveToNext());
                                }
                            }
                            ReadDB.close();
                        }
                        catch (SQLiteException se){
                        }
                        try {
                            SQLiteDatabase ReadDB = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
                            Cursor c = ReadDB.rawQuery("SELECT * FROM " + tableName2, null);
                            if (c != null) {
                                if (c.moveToFirst()) {
                                    do {
                                        x1[0] = c.getDouble(c.getColumnIndex("Latitude2"));
                                        y1[0] = c.getDouble(c.getColumnIndex("Longtitude2"));
                                        dat4 = c.getString(c.getColumnIndex("Time2"));
                                    } while (c.moveToNext());
                                }
                            }
                            ReadDB.close();
                        }
                        catch (SQLiteException se){
                        }
                        time = dat3;
                        time2 = dat4;
                        try {
                            addresses = mGeoCoder.getFromLocation(x[0],y[0],10);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            addresses2 = mGeoCoder.getFromLocation(x1[0],y1[0],10);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (num2 == 1){
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(x[0],y[0]))
                                    .snippet(dat3)
                                    .title("페어링 시작"));
                            LatLng firstloc = new LatLng(x[0], y[0]);
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(firstloc));
                        }
                        else if (num2 == 2){
                            mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(x1[0],y1[0]))
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                                    .snippet(dat4)
                                    .title("페어링 끝"));
                            LatLng firstloc = new LatLng(x1[0], y1[0]);
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(firstloc));
                        }

                    }
                }

        });

    }

*/
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                        .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "권한 체크 거부됨", Toast.LENGTH_SHORT).show();
        }
    }
    }
}