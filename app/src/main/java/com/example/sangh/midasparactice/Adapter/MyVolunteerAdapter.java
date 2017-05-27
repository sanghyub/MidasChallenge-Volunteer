package com.example.sangh.midasparactice.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.midasparactice.Holder.MyVolunteerHolder;
import com.example.sangh.midasparactice.Holder.VolunteerHolder;
import com.example.sangh.midasparactice.Model.Volunteer;
import com.example.sangh.midasparactice.R;

import java.util.ArrayList;

/**
 * Created by sangh on 2017-05-28.
 */

public class MyVolunteerAdapter extends RecyclerView.Adapter<MyVolunteerHolder> {
    Context mcon;
    ArrayList<Volunteer> mVolunteerArrayList;

    public MyVolunteerAdapter(Context mcon, ArrayList<Volunteer> volunteerArrayList) {
        this.mcon = mcon;
        this.mVolunteerArrayList = volunteerArrayList;
    }

    @Override
    public MyVolunteerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcon);
        View view = layoutInflater.inflate(R.layout.item_my_volunteer, parent, false);
        return new MyVolunteerHolder(mcon, view);
    }

    @Override
    public void onBindViewHolder(MyVolunteerHolder holder, int position) {
        Volunteer volunteer = mVolunteerArrayList.get(position);
        holder.onBindView(volunteer);
    }

    @Override
    public int getItemCount() {
        return mVolunteerArrayList.size();
    }

}
