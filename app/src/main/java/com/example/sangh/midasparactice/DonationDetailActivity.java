package com.example.sangh.midasparactice;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DonationDetailActivity extends AppCompatActivity {


    private TextView dTitle;
    private TextView dContent;
    private TextView dResult;
    private Bitmap dImage;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation_details);

        dTitle = (TextView)findViewById(R.id.donation_detail_title);
        dContent = (TextView)findViewById(R.id.donation_detail_content);
        dResult = (TextView)findViewById(R.id.donation_detail_result);

//        intent.putExtra("title", mDonation.getTitle());
//        intent.putExtra("point", mDonation.getPoint());
//        intent.putExtra("totalPoint", mDonation.getTotalPoint());
//        intent.putExtra("contents", mDonation.getContents());
//        intent.putExtra("history", mDonation.getDonationHistory());

//        Intent intent = getIntent();
//        title = intent.getExtras().getString("title");

//        dTitle.setText(s);
    }
}

