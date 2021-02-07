package com.example.mapsactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {


    public static Context mContext;
    public String addr = null;
    public String addr2 = null;
    public String time = null;
    public String time2 = null;
    Button btn_p1;
    Button btn_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);
        this.InitializeView();
        this.SetListener();

        //ct = ((MapsActivity)MapsActivity.mContext).rx1();
        addr = ((MapsActivity) MapsActivity.mContext).ar();
        time = ((MapsActivity) MapsActivity.mContext).tr();
        addr2 = ((MapsActivity) MapsActivity.mContext).ar2();
        time2 = ((MapsActivity) MapsActivity.mContext).tr2();

        if (addr != null) {
            TextView txtStartAddress2 = findViewById(R.id.txtStartAddress2);
            String la = addr;
            txtStartAddress2.setText(la);
        } else {
            TextView txtStartAddress2 = findViewById(R.id.txtStartAddress2);
            txtStartAddress2.setText("null");

        }
        if (time != null) {
            TextView txtStartAddress4 = findViewById(R.id.txtStartAddress4);
            String la1 = time;
            txtStartAddress4.setText(la1);
        } else {
            TextView txtStartAddress4 = findViewById(R.id.txtStartAddress4);
            txtStartAddress4.setText("null");

        }
        if (addr2 != null) {
            TextView txtStartAddress6 = findViewById(R.id.txtStartAddress6);
            String la2 = addr2;
            txtStartAddress6.setText(la2);
        } else {
            TextView txtStartAddress6 = findViewById(R.id.txtStartAddress6);
            txtStartAddress6.setText("null");

        }
        if (time2 != null) {
            TextView txtStartAddress8 = findViewById(R.id.txtStartAddress8);
            String la3 = time2;
            txtStartAddress8.setText(la3);
        } else {
            TextView txtStartAddress8 = findViewById(R.id.txtStartAddress8);
            txtStartAddress8.setText("null");

        }


        //ti = ((MapsActivity)MapsActivity.mContext).ar();
        //TextView txtStartTime2 = (TextView)findViewById(R.id.txtStartTime2);
        //Address la2 = ti;
        //txtStartTime2.setText((CharSequence) la2);

    }
    public void InitializeView()
    {
        btn_p1 = findViewById(R.id.bt1);
        btn_p2 = findViewById(R.id.bt2);
    }

    @Override
    public void onClick(View view) {

        finish();

        switch (view.getId()) {
            case R.id.bt1:
                finish();
                //((MapsActivity)MapsActivity.mContext).marked3(1);
            case R.id.bt2:
                finish();
                //((MapsActivity)MapsActivity.mContext).marked3(2);

        }
    }
    public void SetListener()
    {
        btn_p1.setOnClickListener(this);
        btn_p2.setOnClickListener(this);
    }
}
