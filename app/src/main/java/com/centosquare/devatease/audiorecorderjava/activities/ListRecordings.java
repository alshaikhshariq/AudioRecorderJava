package com.centosquare.devatease.audiorecorderjava.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.centosquare.devatease.audiorecorderjava.R;
import com.centosquare.devatease.audiorecorderjava.adapters.RecordingAdapter;
import com.centosquare.devatease.audiorecorderjava.models.models;

import java.io.File;
import java.util.ArrayList;

public class ListRecordings extends AppCompatActivity
{
    private RecyclerView recyclerViewRecordings;
    private ArrayList<models>    recordingArraylist;
    private RecordingAdapter recordingAdapter;
    private TextView textViewNoRecordings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recordings);

        //Function for initializing all the views
        initViewComponents();

        //Function for calling all the views
        callViewComponents();

        //Function for fetching the recordings from device
        fetchRecordings();
    }

    private void initViewComponents()
    {
        recordingArraylist  =   new ArrayList<models>();

        /* enabling back button */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*setting up recyclerView*/
        recyclerViewRecordings  =   findViewById(R.id.recyclerViewRecordings);
        textViewNoRecordings    =   findViewById(R.id.textViewNoRecordings);
    }

    @SuppressLint("WrongConstant")
    private void callViewComponents()
    {
        recyclerViewRecordings.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        recyclerViewRecordings.setHasFixedSize(true);
    }

    private void fetchRecordings()
    {
        //Creating the environment variable for file
        File root = android.os.Environment.getExternalStorageDirectory();

        //Setting the file path of device
        String path = root.getAbsolutePath() + "/VoiceRecorder/Audios";
        Log.d("Files", "Path: " + path);

        //Creating the fileDirectory
        File directory = new File(path);

        //Making array list of all the files.
        File[] files = directory.listFiles();
        Log.d("Files", "Size: "+ files.length);


        if( files!=null )
        {
            //Getting all the files in an array list
            for (int i = 0; i < files.length; i++)
            {
                Log.d("Files", "FileName:" + files[i].getName());
                String fileName = files[i].getName();
                String recordingUri = root.getAbsolutePath() + "/VoiceRecorder/Audios/" + fileName;

                models recording = new models(recordingUri,fileName,false);
                recordingArraylist.add(recording);
            }

            textViewNoRecordings.setVisibility(View.GONE);
            recyclerViewRecordings.setVisibility(View.VISIBLE);

            //Calling the Function for Recycler View Adapter
            setAdaptertoRecyclerView();

        }
        else
        {
            textViewNoRecordings.setVisibility(View.VISIBLE);
            recyclerViewRecordings.setVisibility(View.GONE);
        }
    }

    private void setAdaptertoRecyclerView()
    {
        recordingAdapter = new RecordingAdapter(recordingArraylist, getApplicationContext());
        recyclerViewRecordings.setAdapter(recordingAdapter);
    }

}
