package com.example.sangh.midasparactice.Holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Model.Volunteer;
import com.example.sangh.midasparactice.R;
import com.example.sangh.midasparactice.VolunteerDetailActivity;

/**
 * Created by sangh on 2017-05-28.
 */


public class MyVolunteerHolder  extends BaseViewHolder<Volunteer> {
    private Volunteer mVolunteer;
    private Context mContext;
    private TextView title;
    private TextView date;
    private ImageView img;


    public MyVolunteerHolder(Context context, View itemView) {
        super(itemView);
        this.mContext =context;
        title = (TextView)itemView.findViewById(R.id.my_volunteer_title);
        img = (ImageView) itemView.findViewById(R.id.my_volunteer_image);
        date = (TextView) itemView.findViewById(R.id.my_volunteer_date);
    }

    @Override
    public void onBindView(final Volunteer volunteer) {
        mVolunteer=volunteer;
        title.setText(volunteer.getTitle());
        date.setText(DbAdapter.getInstance().DateToString(volunteer.getStartDate()) +" ~ " + DbAdapter.getInstance().DateToString(volunteer.getEndDate()));
        img.setImageBitmap(volunteer.getImg());
    }
}
