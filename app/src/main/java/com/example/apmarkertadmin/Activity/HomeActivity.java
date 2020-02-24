package com.example.apmarkertadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apmarkertadmin.Fragment.AddDetailFragment;
import com.example.apmarkertadmin.Fragment.HomeFragment;
import com.example.apmarkertadmin.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class HomeActivity extends AppCompatActivity {
    String Ename;
   public static boolean f ;
   public static String loca;
    private FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        client = LocationServices.getFusedLocationProviderClient(this);
       requestPermission();
        new saveLocation().execute();
        f = getIntent().hasExtra("NAME");
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
    class saveLocation extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if(ActivityCompat.checkSelfPermission(HomeActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
               requestPermission();
                Toast.makeText(HomeActivity.this,"Please Open location and access permission", Toast.LENGTH_LONG).show();
            }
            else {
                client.getLastLocation().addOnSuccessListener(HomeActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
//                            Toast.makeText(getContext(), "Wait a few second to get location address", Toast.LENGTH_SHORT).show();
                            loca =(location.getLatitude() + "," + location.getLongitude());

                        }
                        else {
                            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{ACCESS_FINE_LOCATION}, 1);
                            Toast.makeText(HomeActivity.this, "Open location and Internet", Toast.LENGTH_SHORT).show();
                            // LocationDa.setText(location.getLatitude() + "," + location.getLongitude());
                        }
                    }
                });

            }
            return null;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        f = false;
        //AddDetailFragment.Name.setText(null);
        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).addToBackStack("home").commit();

    }
}
