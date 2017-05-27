package com.example.sangh.midasparactice;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sangh.midasparactice.Adapter.DbAdapter;
import com.example.sangh.midasparactice.Adapter.TabPagerAdapter;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout ;
    private TextView mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Adding Toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);


        mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);


        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Volunteer"));
        tabLayout.addTab(tabLayout.newTab().setText("Donation"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab Three"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        dummy();
        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id==android.R.id.home){
            mDrawerLayout.openDrawer(Gravity.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_vRecord) {
           Intent intent =new Intent(getApplicationContext(), MyVolunteerListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_dRecord) {
            Intent intent = new Intent(getApplicationContext(), MyDonationListActivitiy.class);
            startActivity(intent);
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dummy(){
        DbAdapter.getInstance(this).open();
        DbAdapter.getInstance().createVolunteer(Dummy.vol1_title, BitmapFactory.decodeResource(this.getResources(), R.drawable.vol1), 1200, Dummy.vol1_comments, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );
        DbAdapter.getInstance().createVolunteer(Dummy.vol2_title, BitmapFactory.decodeResource(this.getResources(), R.drawable.vol2), 2000, Dummy.vol2_comments, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );
        DbAdapter.getInstance().createVolunteer(Dummy.vol3_title, BitmapFactory.decodeResource(this.getResources(), R.drawable.vol3), 2000, Dummy.vol3_comments, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );
        DbAdapter.getInstance().createVolunteer(Dummy.vol4_title, BitmapFactory.decodeResource(this.getResources(), R.drawable.vol4), 1500, Dummy.vol4_comments, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );
        DbAdapter.getInstance().createVolunteer(Dummy.vol5_title, BitmapFactory.decodeResource(this.getResources(), R.drawable.vol5), 1300, Dummy.vol5_comments, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()) );

        DbAdapter.getInstance().createDonation(Dummy.don1_title,0, 200, Dummy.don1_contents, Dummy.don1_hisory1);
        DbAdapter.getInstance().createDonation(Dummy.don2_title,0, 400, Dummy.don2_contents, Dummy.don1_hisory2);
        DbAdapter.getInstance().createDonation(Dummy.don3_title,0, 500, Dummy.don3_contents, Dummy.don1_hisory3);
        DbAdapter.getInstance().createDonation(Dummy.don4_title,0, 400, Dummy.don4_contents, Dummy.don1_hisory4);
        DbAdapter.getInstance().createDonation(Dummy.don5_title,0, 500, Dummy.don5_contents, Dummy.don1_hisory5);

        //DbAdapter.getInstance().createUserInfo("이상협",0);
    }
}


