package com.akhil.galaria;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GalaryActivity extends AppCompatActivity {

    private String mImageDescription[];
    private TypedArray mImageIcon;

    private ArrayList<ImageData> mImageData;

    private RecyclerView mRecyclerView;

    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galary);

        mImageDescription = getResources().getStringArray(R.array.image_description_array);
        mImageIcon = getResources().obtainTypedArray(R.array.image_icon_array);

        setupData(mImageDescription,mImageIcon);

        mAdapter = new ImageAdapter(this,mImageData);

        setUpRecyclerView();
    }

    public  void setupData(String description[], TypedArray icon){
        mImageData = new ArrayList<ImageData>();

        for (int i = 0; i < description.length; i++){
            ImageData instance = new ImageData();
            instance.setImageDescription(description[i]);
            instance.setImageIcon(icon.getResourceId(i, 0));
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
}
