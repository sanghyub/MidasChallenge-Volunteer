package com.example.sangh.midasparactice.Holder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.AppLog;
import com.example.sangh.midasparactice.DonationDetailActivity;
import com.example.sangh.midasparactice.DonationDialog;
import com.example.sangh.midasparactice.Fragment.DonationListFragment;
import com.example.sangh.midasparactice.Model.Donation;
import com.example.sangh.midasparactice.R;

/**
 * Created by sangh on 2017-05-27.
 */

public class DonationHolder extends BaseViewHolder<Donation> implements View.OnClickListener {
    private Context mContext;
    Donation mDonation;
    private TextView title;
    private TextView point;
    private TextView totalPoint;
    private Button donBtn;

    public DonationHolder(Context context,View itemView) {
        super(itemView);
        this.mContext =context;
        itemView.setOnClickListener(this);
        title = (TextView)itemView.findViewById(R.id.donation_title);
        point = (TextView)itemView.findViewById(R.id.donation_point);
        totalPoint = (TextView)itemView.findViewById(R.id.donation_total_point);
        donBtn = (Button)itemView.findViewById(R.id.donation_btn );
    }

    @Override
    public void onBindView(final Donation donation) {
        mDonation= donation;
        title.setText(donation.getTitle());
        point.setText(donation.getPoint()+"");
        totalPoint.setText(donation.getTotalPoint()+"");
        donBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DonationDialog dialog = new DonationDialog(mContext, donation.getNum());
            dialog.getWindow().clearFlags( WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        point.setText(DbAdapter.getInstance().getDonationJoinPoint(donation.getNum())+"");
                        totalPoint.setText(DbAdapter.getInstance().getDonationPoint(donation.getNum())+"");
                    }
                });
            dialog.getWindow().setAttributes(params);
            dialog.show();
            }
        });

    }

    @Override
    public void onClick(View v){
        Intent intent =new Intent(mContext, DonationDetailActivity.class);
        intent.putExtra("title", mDonation.getTitle());
        intent.putExtra("point", mDonation.getPoint());
        intent.putExtra("totalPoint", mDonation.getTotalPoint());
        intent.putExtra("contents", mDonation.getContents());
        intent.putExtra("history", mDonation.getDonationHistory());
        mContext.startActivity(intent);
    }

}
