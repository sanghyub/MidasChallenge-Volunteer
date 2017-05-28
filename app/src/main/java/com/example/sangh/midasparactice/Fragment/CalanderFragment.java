package com.example.sangh.midasparactice.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sangh.midasparactice.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import static android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.SELECTION_MODE_MULTIPLE;
import static com.example.sangh.midasparactice.R.id.calendarView;


/**
 * Created by sangh on 2017-05-28.
 */

public class CalanderFragment extends Fragment{
    private MaterialCalendarView calendarView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calender , container, false);
        calendarView = (MaterialCalendarView) v.findViewById(R.id.calendarView);
        return v;
    }

    public  void setCalendar(int year, int month, int day) {
        calendarView.setSelectionMode(SELECTION_MODE_MULTIPLE);
        calendarView.setDateSelected(CalendarDay.from(year, month-1, day), true);
    }
}
