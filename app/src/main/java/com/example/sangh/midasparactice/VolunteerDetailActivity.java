package com.example.sangh.midasparactice;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sangh on 2017-05-27.
 */

public class VolunteerDetailActivity extends AppCompatActivity {

    private TextView vTitle;
    private TextView vContent;
    private Bitmap vImage;
    private Button vBtn;

    private String title;
    private String contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation_details);

        vTitle = (TextView) findViewById(R.id.volunteer_detail_title );
        vContent = (TextView)findViewById(R.id.volunteer_detail_content);
        vBtn = (Button) findViewById(R.id.volunteer_detail_btn);

        Intent intent=getIntent();
        title=intent.getExtras().getString("title");
        contents = intent.getExtras().getString("contents");

        vTitle.setText(title);
        vContent.setText(contents);
        //dContent.setText(contents);

    }
}
