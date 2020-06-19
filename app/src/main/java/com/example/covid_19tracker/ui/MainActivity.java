package com.example.covid_19tracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.covid_19tracker.R;
import com.google.android.material.navigation.NavigationView;

import javax.net.ssl.HandshakeCompletedEvent;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;
    private Toast toast;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme_NoActionBar); // Changing Activity Theme from SplashTheme to AppTheme.NoActionBar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.mainToolbar);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);



        //setting up toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


/*
        //We Don't need to animate drawer toggle button(Hambargar Icon) .
        // Because NavigationUI takes care of it when we set up drawerlayout with navigation controller
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();*/

        // Changing Text Style of Navigation drawer hading(Ex:"Covid-19 Info","Others")
        Menu menu = navigationView.getMenu();
        MenuItem itemHeading1 = menu.findItem(R.id.navTitle1);
        MenuItem itemHeading2 = menu.findItem(R.id.navTitle2);
        SpannableString spannableString1 = new SpannableString(itemHeading1.getTitle());
        SpannableString spannableString2 = new SpannableString(itemHeading2.getTitle());
        spannableString1.setSpan(new TextAppearanceSpan(this, R.style.NavigationDrawerText), 0, spannableString1.length(), 0);
        spannableString2.setSpan(new TextAppearanceSpan(this, R.style.NavigationDrawerText), 0, spannableString2.length(), 0);
        itemHeading1.setTitle(spannableString1);
        itemHeading2.setTitle(spannableString2);


        //Setting Up Navigation Controller With drawer layout and navigation view
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else {

            if (navController.getCurrentDestination().getId()==R.id.globalDataFragment){

                // Back press twice to exit the app
                if (backPressedTime + 2000 > System.currentTimeMillis()) {
                    toast.cancel();
                    super.onBackPressed();
                    return;

                } else {
                    toast = Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_SHORT);
                    toast.show();
                }

                backPressedTime = System.currentTimeMillis();

            }else{
                super.onBackPressed();
            }

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawers();
        item.setChecked(true);

        switch (item.getItemId()) {

            case R.id.globalDataFragment:

                if (navController.getCurrentDestination().getId()==R.id.globalDataFragment)
                    return false;
                else {
                    navController.navigate(R.id.globalDataFragment);
                    return true;
                }


            case R.id.countriesFragment:

                if (navController.getCurrentDestination().getId()==R.id.countriesFragment)
                    return false;
                else{
                    navController.navigate(R.id.countriesFragment);
                    return true;
                }


            case R.id.indiaStatFragment:

                if (navController.getCurrentDestination().getId()==R.id.indiaStatFragment)
                    return false;
                else {
                    navController.navigate(R.id.indiaStatFragment);
                    return true;
                }


            case R.id.aboutAppFragment:

                if (navController.getCurrentDestination().getId()==R.id.aboutAppFragment)
                    return false;
                else{
                    navController.navigate(R.id.aboutAppFragment);
                    return true;
                }

            case R.id.shareApp:

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Covid Tracker App");
                    String shareMessage = "\nHey,I am sharing this awesome app with you !! Download it from below link.\n\n";
                    shareMessage = shareMessage + "https://drive.google.com/drive/folders/1XhIiMM9lhPRJagxfue9Pwd--cGdRS3vV?usp=sharing";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "Share Using"));
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
                return true;

            default: return false;

        }


    }
}
