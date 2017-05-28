package com.example.sangh.midasparactice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Adapter.DonationAdapter;
import com.example.sangh.midasparactice.Adapter.MyDonationAdapter;
import com.example.sangh.midasparactice.Adapter.VolunteerAdapter;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.Model.Volunteer;

import java.util.ArrayList;

/**
 * Created by sangh on 2017-05-27.
 */

public class MyDonationListActivitiy extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyDonationAdapter mAdapter;
    private ArrayList<Donation> myDonationArrayList = new ArrayList<>();
    private Toolbar mToolbar;
    private static View mRootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_donation_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_my_donation);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mToolbar = (Toolbar)findViewById(R.id.toolbar_donation);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("나의 기부내역");
        updateUI();
        dummyData();
    }

    public void updateUI() {
        mAdapter = new MyDonationAdapter(getApplicationContext(), myDonationArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void dummyData(){
        ArrayList<Donation> arrayList = DbAdapter.getInstance().getDonationList();
        for(Donation donation : arrayList){
            if(donation.getPoint()>0)myDonationArrayList.add(donation);
        }
        mAdapter.notifyDataSetChanged();
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

