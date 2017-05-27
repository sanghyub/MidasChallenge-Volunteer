package com.example.sangh.midasparactice;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by sangh on 2017-05-27.
 */

public class VolunteerDetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView contents;
    private TextView Date;
    private TextView point;
    private Button mBtn;

    private String mTitle;
    private String mContents;
    private Bitmap mBitmap;
    private String mStartDate;
    private String mEndDate;
    private int mPoint;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation_details);

        title = (TextView) findViewById(R.id.volunteer_title);
        contents = (TextView)findViewById(R.id.volunteer_detail_content);
        mBtn = (Button) findViewById(R.id.volunteer_btn);
        Date = (TextView) findViewById(R.id.volunteer_detail_Date);
        point = (TextView)findViewById(R.id.volunteer_detail_point);

        Intent intent=getIntent();
        mTitle=intent.getExtras().getString("title");
        mContents = intent.getExtras().getString("contents");
        mPoint =intent.getExtras().getInt("point");
        mStartDate =intent.getExtras().getString("startDate");
        mEndDate =intent.getExtras().getString("endDate");


        title.setText(mTitle);
        contents.setText(mContents);
        mBtn.setText("신청하기");
        Date.setText(mStartDate + " ~ " + mEndDate);
        point.setText(mPoint+"");
    }


}
