package com.example.sangh.midasparactice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.midasparactice.Holder.DonationHolder;
import com.example.sangh.midasparactice.Holder.MyDonationHolder;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.R;

import java.util.ArrayList;

/**
 * Created by sangh on 2017-05-28.
 */

public class MyDonationAdapter extends RecyclerView.Adapter<MyDonationHolder> {

    Context mcon;
    ArrayList<Donation> mDonationArrayList;

    public MyDonationAdapter(Context mcon, ArrayList<Donation> donationArrayList) {
        this.mcon = mcon;
        this.mDonationArrayList = donationArrayList;

    }

    @Override
    public  MyDonationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcon);
        View view = layoutInflater.inflate(R.layout.item_my_donation, parent, false);
        return new MyDonationHolder(mcon, view);
    }

    @Override
    public void onBindViewHolder(MyDonationHolder holder, int position) {
        Donation donation = mDonationArrayList.get(position);
        holder.onBindView(donation);
    }

    @Override
    public int getItemCount() {
        return mDonationArrayList.size();
    }

}

