package com.example.networkanalyzer20;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //networkUsageTextView = findViewById(R.id.networkUsageTextView);
        Button Check_Button = (Button) findViewById(R.id.Check_Button);
        Check_Button.setOnClickListener(view -> connectivityStatus());
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(view -> openActivity2());
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
}
