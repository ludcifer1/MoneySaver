package com.example.admin.asm_zlud;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.example.admin.asm_zlud.fragment.fragmentIncome.fragmentIncome;
import com.example.admin.asm_zlud.fragment.fragmentOutcome.fragmentOutCome;
import com.example.admin.asm_zlud.fragment.fragmentStatic;
import com.example.admin.asm_zlud.service.AddActivity;

public class Mainager extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private FrameLayout frameLayout;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainager);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navview);
        frameLayout = findViewById(R.id.framelayout);
        toolbar = findViewById(R.id.toolbar);
        //Floating Action Button Color on Pressed
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_room);
        ColorStateList rippleColor = ContextCompat.getColorStateList(getBaseContext(), R.color.fab_ribble_color);
        fab.setBackgroundTintList(rippleColor);
        fab.setOnClickListener(this);
        //

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //Add Toolbar
        setSupportActionBar(toolbar);
        //Create the Menu Button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        //Set Static Fragment as one of the Startup Screen after Login
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = fragmentStatic.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
    }

    //Set On Menu Button Tapped
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Choose Fragment on Nav Item Tapped
    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case (R.id.nav_A):
                fragmentClass = fragmentIncome.class;
                break;
            case (R.id.nav_B):
                fragmentClass = fragmentOutCome.class;
                break;
            case (R.id.nav_C):
                fragmentClass = fragmentStatic.class;
                break;
            case (R.id.LogOut):
                System.exit(1);
            default:
                fragmentClass = fragmentStatic.class;
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Replace the fragment on Screen
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout, fragment, "Fragment").commit();
        //HighLight selected Item
        menuItem.setChecked(true);
        //Set Action Bar Tit
        setTitle(menuItem.getTitle());
        //Close the Nav Drawer
        drawerLayout.closeDrawers();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //ADD INCOME-OUTCOME by Pressing the ADd Button
            case (R.id.fab_add_room):
                Intent intent = new Intent(Mainager.this, AddActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 10001);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == 10001)) {
            fragmentStatic fragment = new fragmentStatic();
//            Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentByTag("Fragment");

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.framelayout, fragment);
            fragmentTransaction.commit();

        }
    }
}
