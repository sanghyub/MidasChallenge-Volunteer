package com.example.sangh.midasparactice;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Fragment.DonationListFragment;

/**
 * Created by bgh29 on 2017-05-28.
 */

public class DonationDialog extends Dialog implements DialogInterface, View.OnClickListener{
    private OnDismissListener _listener ;
    private EditText donationEditText;
    private Button okBtn, cancelBtn;
    private final Context con;
    private long donationNum;

    public DonationDialog(Context context, long dNum) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_donation);

        con=context;
        donationNum = dNum;
        donationEditText = (EditText)findViewById(R.id.edit_donation_point);
        okBtn = (Button)findViewById(R.id.button_donation_ok);
        okBtn.setOnClickListener(this);
        cancelBtn = (Button)findViewById(R.id.button_donation_cancel);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==okBtn) {
            DbAdapter.getInstance().addDonationPoint(donationNum, Integer.parseInt(donationEditText.getText().toString()));
        }
        cancel();
    }
}
