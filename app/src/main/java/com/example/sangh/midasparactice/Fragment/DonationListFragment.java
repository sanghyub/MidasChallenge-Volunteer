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
import com.example.sangh.midasparactice.Adapter.DonationAdapter;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sangh on 2017-05-27.
 */

public class DonationListFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private DonationAdapter mAdapter;
    private ArrayList<Donation> mDonationItems=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.donation_fragment , container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_donation);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        dummyData();
        return v;
    }


    public void updateUI(){
        mAdapter = new DonationAdapter(getContext(), mDonationItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void dummyData(){
        ArrayList<Donation> arrayList =DbAdapter.getInstance().getDonationList();
        for(Donation donation : arrayList){
            mDonationItems.add(donation);
        }
        mAdapter.notifyDataSetChanged();

    }

}
