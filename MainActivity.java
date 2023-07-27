package com.example.networkanalyzer20;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private TextView ssidTextView;
    ImageView linkedin, sigma;

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ssidTextView = findViewById(R.id.ssidTextView);
        linkedin = findViewById(R.id.linkedin);
        sigma = findViewById(R.id.sigma);
        Button Check_Button = (Button) findViewById(R.id.Check_Button);
        Check_Button.setOnClickListener(view -> connectivityStatus());
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(view -> openActivity2());
        linkedin.setOnClickListener(view -> gotoURL("https://www.linkedin.com/in/anish-puranik/"));
        sigma.setOnClickListener(view -> gotoURL("https://www.sigmaconnectivity.com/"));

        // Request necessary permissions at runtime
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        PERMISSION_REQUEST_CODE);
            } else {
                displayWifiSSID();
            }
        } else {
            displayWifiSSID();
        }
        Window window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.bluish_white));
    }

    private void gotoURL(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public void openActivity2(){
        boolean condition = isConnected();
        if(condition){
            Intent intent = new Intent(this, Analyze.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Connect to the Internet and try again!",Toast.LENGTH_SHORT).show();
            }
        }

    public void connectivityStatus(){
        if(isConnected())
            Toast.makeText(getApplicationContext(), "Connected to the internet",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            return networkInfo.isConnectedOrConnecting();
        } else
            return false;
    }

    @SuppressLint("SetTextI18n")
    private void displayWifiSSID() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            ssidTextView.setText("You are Connected to: " + "\n       " +ssid);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                displayWifiSSID();
            }
        }
    }

}
