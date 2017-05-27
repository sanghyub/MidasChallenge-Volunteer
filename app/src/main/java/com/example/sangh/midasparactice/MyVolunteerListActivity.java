package com.example.sangh.midasparactice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Adapter.MyVolunteerAdapter;
import com.example.sangh.midasparactice.Adapter.VolunteerAdapter;
import com.example.sangh.midasparactice.Model.Volunteer;

import java.util.ArrayList;

/**
 * Created by sangh on 2017-05-27.
 */

public class MyVolunteerListActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private MyVolunteerAdapter mAdapter;
    private ArrayList<Volunteer> mVolunteerArrayList =new ArrayList<>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_volunteer_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_my_volunteer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mToolbar =(Toolbar)findViewById(R.id.toolbar_volunteer);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("나의 봉사내역");

        updateUI();
        dummyData();

    }
    public void updateUI() {
        mAdapter = new MyVolunteerAdapter(getApplicationContext(), mVolunteerArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void dummyData(){
        ArrayList<Volunteer> arrayList = DbAdapter.getInstance().getVolunteerList();
        for(Volunteer volunteer : arrayList){
            if(volunteer.isJoin())mVolunteerArrayList.add(volunteer);
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
