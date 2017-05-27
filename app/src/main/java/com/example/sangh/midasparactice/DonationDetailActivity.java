package com.example.sangh.midasparactice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class DonationDetailActivity extends AppCompatActivity {


    private TextView dTitle;
    private TextView dContent;
    private TextView dResult;
    private TextView dPoint;
    private TextView dTotalPoint;
    private Bitmap dImage;
    private String title;

    private String contents;
    private int totalPoint;
    private String history;
    private int point;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation_details);

        mToolbar = (Toolbar)findViewById(R.id.toolbar_donation_details);


        dTitle = (TextView)findViewById(R.id.donation_detail_title);
        dContent = (TextView)findViewById(R.id.donation_details_content);
        dPoint = (TextView)findViewById(R.id.donation_detail_point);
        dTotalPoint =(TextView)findViewById(R.id.donation_detail_total_point);
        dResult =(TextView)findViewById(R.id.donation_detail_history);

        Intent intent=getIntent();
        title=intent.getExtras().getString("title");
        point=intent.getExtras().getInt("point");
        totalPoint =intent.getExtras().getInt("totalPoint");
        history = intent.getExtras().getString("history");
        contents = intent.getExtras().getString("contents");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("기부 상세내용");

        dTitle.setText(title);
        dPoint.setText(point+"");
        dTotalPoint.setText(totalPoint+"");
        dContent.setText(contents);
        dResult.setText(history);

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

