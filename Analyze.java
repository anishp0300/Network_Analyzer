package com.example.networkanalyzer20;

import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Analyze extends AppCompatActivity {
    public static final String TAG = "Analyze";
    Button buttonSave, buttonClear;
    private ArrayAdapter<String> adapter;
    private Handler handler;
    private long previousRxBytes;
    private long previousTxBytes;
    private List<String> packetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);
        ListView packetListView = findViewById(R.id.packetListView);
        buttonClear = findViewById(R.id.buttonClear);
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(view -> startCapture());
        buttonClear.setOnClickListener(view -> clearData());
        packetList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, packetList);
        packetListView.setAdapter(adapter);
        handler = new Handler(Looper.getMainLooper());
        startCapture();

        Window window=this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.bluish_white));
    }

    private void startCapture(){
        previousRxBytes = TrafficStats.getTotalRxBytes();
        previousTxBytes = TrafficStats.getTotalTxBytes();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long currentRxBytes = TrafficStats.getTotalRxBytes();
                long currentTxBytes = TrafficStats.getTotalTxBytes();

                long rxBytes = currentRxBytes - previousRxBytes;
                long txBytes = currentTxBytes - previousTxBytes;

                previousRxBytes = currentRxBytes;
                previousTxBytes = currentTxBytes;
                Date currentTime = Calendar.getInstance().getTime();
                String packetInfo = ">>" + DateFormat.getTimeInstance(DateFormat.LONG).format(currentTime) + " - Received bytes: " + rxBytes/1024 + "kb" + ", Transmitted bytes: " + txBytes/1024 + "kb";

                //new
                packetList.add(packetInfo);
                handler = new Handler();
                saveToFile();
                adapter.notifyDataSetChanged();
                // Schedule the next capture after this interval
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void saveToFile() {
        //Create a file and directory
        File directory = new File(Environment.getExternalStorageDirectory().getPath()+ "/Documents");
        if(!directory.exists()){
            directory.mkdirs();
            if(!directory.mkdir()){
                //If creation of directory is failed, user shown the error!
                Log.e(TAG,"Failed to create directory");
                Toast.makeText(getApplicationContext(), "Save Failed",Toast.LENGTH_SHORT).show();
            }
        }
        File file = new File(directory, "Sent_Received_Data.txt");
        Log.d(TAG, file.getPath());
        try {
            //Write array list contents to this file
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String item : packetList){
                bufferedWriter.write(item);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void clearData(){
        packetList.clear();
        adapter.notifyDataSetChanged();
    }

    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

    }
}
