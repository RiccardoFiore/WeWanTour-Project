package com.example.wewantour9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class TotalRegister extends AppCompatActivity {

    DrawerLayout drawer;
    //NavigationView nav_view;
    //ActionBarDrawerToggle toggle;

    ViewPager viewpager;
    TabLayout tab;
    TabItem firstitem;
    TabItem seconditem;

    PagerAdapter pageradapter;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TotalRegister.this, MainActivity.class));
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        viewpager = findViewById(R.id.viewpager);
        tab = findViewById(R.id.tabLayout);
        firstitem = findViewById(R.id.firstitem);
        seconditem = findViewById(R.id.seconditem);

        drawer = findViewById(R.id.drawer);
        //nav_view = findViewById(R.id.nav_view);

        //toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);

        //drawer.addDrawerListener(toggle);
        //toggle.setDrawerIndicatorEnabled(true);
        //toggle.syncState();

        pageradapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tab.getTabCount());
        viewpager.setAdapter(pageradapter);



        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                int g = tab.getPosition();
                String l = Integer.toString(g);
/*
                ViewPager.LayoutParams layoutparams = new ViewPager.LayoutParams();
                layoutparams.height = 500;
                ViewPager.LayoutParams layoutparams2 = new ViewPager.LayoutParams();
                layoutparams2.height = 1000;

                Log.d("ue", l);
                if(g == 0){
                    viewpager.setLayoutParams(layoutparams);
                }
                else{
                    viewpager.setLayoutParams(layoutparams2);

                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int res_id = item.getItemId();
        if(res_id == R.id.menu_info){
            AlertDialog.Builder info = new AlertDialog.Builder(TotalRegister.this);
            info.setMessage("Se sei utente te registri come utente se sei operator te registri come operator! " +
                    "Non sbagliare se no Andrea me mena. Ora sto scrivendo parole a caso per vedere se l' Alert Dialog quanto" +
                    "spazio prende. Ciao");


           info.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            });

            info.create().show();
        }

        else{
            startActivity(new Intent(TotalRegister.this, MainActivity.class));

        }


        return true;
    }




}