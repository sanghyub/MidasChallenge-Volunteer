package com.example.sangh.midasparactice.Holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangh.midasparactice.DonationDetailActivity;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.R;

/**
 * Created by sangh on 2017-05-28.
 */

public class MyDonationHolder extends BaseViewHolder<Donation> {
    private Context mContext;
    Donation mDonation;
    private TextView title;
    private TextView point;

    public MyDonationHolder(Context context,View itemView) {
        super(itemView);
        this.mContext =context;
        title = (TextView)itemView.findViewById(R.id.myDonation_title);
        point = (TextView)itemView.findViewById(R.id.myDonation_point);
    }

    @Override
    public void onBindView(Donation donation) {
        mDonation= donation;
        title.setText(donation.getTitle());
        point.setText(donation.getPoint()+"");
    }
}
