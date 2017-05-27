package com.example.sangh.midasparactice.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sangh on 2017-05-27.
 */


public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView){
        super(itemView);
    }
    public abstract void onBindView(ITEM item);
}
