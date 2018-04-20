package com.example.emmym.androidbt;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
public class NavbarActivity extends AppCompatActivity{
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle mToggle;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_navbar);
            mDrawerLayout = findViewById(R.id.navbar);
            mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open,R.string.close);
            mDrawerLayout.addDrawerListener(mToggle);
            mToggle.syncState();
            NavigationView navDrawer = findViewById(R.id.entire_navbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setUpDrawerContent(navDrawer);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(mToggle.onOptionsItemSelected(item)) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    //select NavBarMenu
    public void selectNavBarMenuOptions(MenuItem menuItem){
        android.support.v4.app.Fragment myFragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()){
            case R.id.bluetoothMenu:
                fragmentClass = BluetoothMenu.class;
               Intent bluetoothIntent = new Intent(NavbarActivity.this, BluetoothActivity.class);
               startActivity(bluetoothIntent);
                break;
            case R.id.map:
                fragmentClass = MapMenu.class;
                Intent mapIntent = new Intent( NavbarActivity.this,TouchScreenActivity.class );
                startActivity(mapIntent);
                break;
            default:
                fragmentClass = BluetoothMenu.class;
                bluetoothIntent = new Intent(NavbarActivity.this, BluetoothActivity.class);
                startActivity(bluetoothIntent);
                break;
        }
        try{
            myFragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContent, myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }
    private void setUpDrawerContent(NavigationView navigationView ){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectNavBarMenuOptions(item);
                return true;
            }
        });
    }
}
