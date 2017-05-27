package com.example.sangh.midasparactice.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Adapter.VolunteerAdapter;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.Model.Volunteer;
import com.example.sangh.midasparactice.R;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sangh on 2017-05-27.
 */

public class VolunteerListFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private VolunteerAdapter mAdapter;
    private ArrayList<Volunteer> mVolunteerArrayList =new ArrayList<>();;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

       // long testnum = DbAdapter.getInstance().createVolunteer("title1", BitmapFactory.decodeResource(this.getResources(), R.drawable.main_tree), 300, "content1", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );
       // testnum = DbAdapter.getInstance().createVolunteer("title2", BitmapFactory.decodeResource(this.getResources(), R.drawable.main_tree), 200, "content2", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );
       // testnum = DbAdapter.getInstance().createVolunteer("title3", BitmapFactory.decodeResource(this.getResources(), R.drawable.main_tree), 100, "content3", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.volunteer_fragment , container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_volunteer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
       // dummyData();
        return v;
    }


    public void updateUI(){
        mAdapter = new VolunteerAdapter(getContext(), mVolunteerArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void dummyData(){
       // mVolunteerArrayList= DbAdapter.getInstance().getVolunteerList();

        mAdapter.notifyDataSetChanged();
    }
}
