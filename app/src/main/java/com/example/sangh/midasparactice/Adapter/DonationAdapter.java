package com.example.sangh.midasparactice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.midasparactice.Holder.DonationHolder;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.R;

import java.util.ArrayList;

/**
 * Created by sangh on 2017-05-27.
 */

public class DonationAdapter extends RecyclerView.Adapter<DonationHolder> {

    Context mcon;
    ArrayList<Donation> mDonationArrayList;

    public DonationAdapter(Context mcon, ArrayList<Donation> donationArrayList) {
        this.mcon = mcon;
        this.mDonationArrayList = donationArrayList;

    }

    @Override
    public DonationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcon);
        View view = layoutInflater.inflate(R.layout.donation_list_item, parent, false);
        return new DonationHolder(mcon, view);
    }

    @Override
    public void onBindViewHolder(DonationHolder holder, int position) {
        Donation donation = mDonationArrayList.get(position);
        holder.onBindView(donation);
    }

    @Override
    public int getItemCount() {
        return mDonationArrayList.size();
    }

}
