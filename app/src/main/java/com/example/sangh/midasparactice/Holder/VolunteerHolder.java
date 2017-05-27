package com.example.sangh.midasparactice.Holder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Model.Volunteer;
import com.example.sangh.midasparactice.R;
import com.example.sangh.midasparactice.VolunteerDetailActivity;

import org.w3c.dom.Text;

/**
 * Created by sangh on 2017-05-27.
 */

public class VolunteerHolder  extends BaseViewHolder<Volunteer> implements View.OnClickListener{
    private Volunteer mVolunteer;
    private Context mContext;
    private TextView title;
    private TextView date;
    private TextView contents;
    private Button mButton;
    private ImageView img;


    public VolunteerHolder(Context context, View itemView) {
        super(itemView);
        this.mContext =context;
        itemView.setOnClickListener(this);
        title = (TextView)itemView.findViewById(R.id.volunteer_title);
        img = (ImageView) itemView.findViewById(R.id.volunteer_image);
        date = (TextView) itemView.findViewById(R.id.volunteer_date);
        mButton =(Button) itemView.findViewById(R.id.volunteer_btn);
    }

    @Override
    public void onBindView(final Volunteer volunteer) {
        mVolunteer=volunteer;
        title.setText(volunteer.getTitle());
        //date.setText(DbAdapter.getInstance().DateToString(volunteer.getStartDate()) +" ~ " + DbAdapter.getInstance().DateToString(volunteer.getEndDate()));
        date.setText("2017-05-24"+ " ~ " + "2017-05-27");
        //img.setImageBitmap(volunteer.getImg());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "신청하기", Toast.LENGTH_SHORT).show();
                volunteer.setJoin(true);
            }
        });

        if(volunteer.isJoin()){
            mButton.setText("참여중");
            mButton.setBackgroundColor(Color.rgb(0,86,255));
            mButton.setEnabled(false);
        }
        else{
            mButton.setText("신청하기");
            mButton.setEnabled(true);
            volunteer.setJoin(false);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(mContext, VolunteerDetailActivity.class);
        intent.putExtra("title", mVolunteer.getTitle());
        intent.putExtra("img", mVolunteer.getImg());
        intent.putExtra("startDate", mVolunteer.getStartDate());
        intent.putExtra("endDate", mVolunteer.getEndDate());
        intent.putExtra("contents", mVolunteer.getContents());
        intent.putExtra("point", mVolunteer.getPoint());
        mContext.startActivity(intent);
    }
}
