package com.akhil.galaria;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GalaryActivity extends AppCompatActivity {

    private String mImageDescription[];
    private TypedArray mImageIcon;
    private boolean mLikedStatus;

    private ArrayList<ImageData> mImageData;

    private RecyclerView mRecyclerView;

    private ImageAdapter mAdapter;

    private static final String STAMP_KEY="save_key";
    private SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galary);

        mImageDescription = getResources().getStringArray(R.array.image_description_array);
        mImageIcon = getResources().obtainTypedArray(R.array.image_icon_array);
        mLikedStatus = false;

        mSharedPref = getPreferences(MODE_PRIVATE);
        mImageData = loadImages();

        if(mImageData.size() == 0){
            setupData(mImageDescription,mImageIcon, mLikedStatus);
        }

        mAdapter = new ImageAdapter(this,mImageData);

        setUpRecyclerView();
    }

    public  void setupData(String description[], TypedArray icon, boolean likedStatus){
        mImageData = new ArrayList<ImageData>();

        for (int i = 0; i < description.length; i++){
            ImageData instance = new ImageData();
            instance.setImageDescription(description[i]);
            instance.setImageIcon(icon.getResourceId(i, 0));
            instance.setLikedStatus(likedStatus);
            mImageData.add(instance);
        }
    }

    private void setUpRecyclerView(){
        //Initialize RecyclerView object
        mRecyclerView = findViewById(R.id.mRecyclerView);

        //Set up a line after each row, so it looks like a list
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this,DividerItemDecoration.VERTICAL));

        //Set up the LayoutManager for RecyclerView
        mRecyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        //Attach adapter object with RecyclerView
        mRecyclerView.setAdapter(mAdapter);
    }

    private void saveStamps(){
        //Editor object to save and update data
        SharedPreferences.Editor editor = mSharedPref.edit();
        //Convert data into JSON string
        Gson gson = new Gson();
        String jsonImages = gson.toJson(mImageData);

        //save data inside the sharedPreference using putString
        editor.putString(STAMP_KEY, jsonImages);
        //confirm the changes
        editor.apply();
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveStamps();
    }

    private ArrayList<ImageData> loadImages(){
        //fetch the data from SharedPreference object
        String jsonImageString = mSharedPref.getString(STAMP_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<List<ImageData>>(){}.getType();
        ArrayList<ImageData> loadData = gson.fromJson(jsonImageString, type);

        if (loadData == null){
            loadData = new ArrayList<>();
        }
        return  loadData;
    }
}
