package com.example.sangh.midasparactice.Fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Adapter.VolunteerAdapter;
import com.example.sangh.midasparactice.Dummy;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.volunteer_fragment , container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_volunteer);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        dummyData();
        return v;
    }


    public void updateUI(){
        mAdapter = new VolunteerAdapter(getContext(), mVolunteerArrayList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void dummyData(){
        ArrayList<Volunteer> arrayList =DbAdapter.getInstance().getVolunteerList();
        for(Volunteer volunteer : arrayList){
            mVolunteerArrayList.add(volunteer);
        }
        mAdapter.notifyDataSetChanged();
    }
}
