package com.example.sangh.midasparactice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DonationDetailActivity extends AppCompatActivity {


    private TextView dTitle;
    private TextView dContent;
    private TextView dResult;
    private Bitmap dImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation_details);

        dTitle = (TextView)findViewById(R.id.donationTitle);
        dContent = (TextView)findViewById(R.id.donationContent);
        dResult = (TextView)findViewById(R.id.donationResult);

        Intent intent=new Intent(this.getIntent());
        String title= "이거이거 되느?";
//                intent.getStringExtra("text");

        dTitle.setText(title);
    }
}

