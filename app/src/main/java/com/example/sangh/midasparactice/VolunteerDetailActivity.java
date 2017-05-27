package com.example.sangh.midasparactice;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Model.Volunteer;

import java.util.ArrayList;

/**
 * Created by sangh on 2017-05-27.
 */

public class VolunteerDetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView contents;
    private TextView Date;
    private TextView point;
    private Button mBtn;
    private ImageView mImageView;

    private String mTitle;
    private String mContents;
    private Bitmap mBitmap;
    private String mStartDate;
    private String mEndDate;
    private int mPoint;
    private boolean isJoin;
    private long number;
    private int flag;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_details);

        title = (TextView) findViewById(R.id.volunteer_detail_title);
        contents = (TextView)findViewById(R.id.volunteer_detail_content);
        mBtn = (Button) findViewById(R.id.volunteer_detail_btn);
        Date = (TextView) findViewById(R.id.volunteer_detail_Date);
        point = (TextView)findViewById(R.id.volunteer_detail_point);
        mImageView =(ImageView)findViewById(R.id.volunteer_detail_image);

        mToolbar = (Toolbar)findViewById(R.id.toolbar_volunteer_details);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("봉사 세부내용");

        Intent intent=getIntent();
        mTitle=intent.getExtras().getString("title");
        mContents = intent.getExtras().getString("contents");
        mPoint =intent.getExtras().getInt("point");
        mStartDate =intent.getExtras().getString("startDate");
        mEndDate =intent.getExtras().getString("endDate");
        isJoin = intent.getExtras().getBoolean("join");
        number = intent.getExtras().getLong("number",1L);
        mBitmap =(Bitmap)intent.getParcelableExtra("img");

        flag=0;

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "신청되었습니다.", Toast.LENGTH_SHORT).show();
                mBtn.setText("이미 참여중인 봉사");
                mBtn.setBackgroundColor(Color.rgb(0,153,255));
                mBtn.setEnabled(false);
                DbAdapter.getInstance().addUserPoint(number,mPoint);
                DbAdapter.getInstance().changeVolunteerJoin(number);
                flag=1;
            }
        });

        ArrayList<Volunteer> arrayList =DbAdapter.getInstance().getVolunteerList();

        for(Volunteer volunteer :arrayList){
            if(volunteer.getNumber()==number){
                if(volunteer.isJoin()){
                    mBtn.setText("이미 참여중인 봉사");
                    mBtn.setBackgroundColor(Color.rgb(0,153,255));
                    mBtn.setEnabled(false);
                    flag=1;
                }
                break;
            }
        }
        if(flag==0) {
            mBtn.setText("신청하기");
            mBtn.setEnabled(true);
            mBtn.setBackgroundColor(Color.rgb(84, 186, 255));
        }
        title.setText(mTitle);
        contents.setText(mContents);
        mBtn.setText("신청하기");
        Date.setText(mStartDate + " ~ " + mEndDate);
        point.setText(mPoint+"");
        mImageView.setImageBitmap(mBitmap);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
