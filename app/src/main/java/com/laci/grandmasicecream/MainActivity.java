package com.laci.grandmasicecream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private ArrayList<IceCreamModel> IceCreamList;
    private IceCreamItemAdapeter IceCreamItemAdapeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        IceCreamList = new ArrayList<>();

        IceCreamItemAdapeter = new IceCreamItemAdapeter(this, IceCreamList);
        RecyclerView.setAdapter(IceCreamItemAdapeter);
        
        initializeData();
    }


    private void initializeData() {
        IceCreamList.clear();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            double price = obj.getDouble("basePrice");
            JSONArray m_jArry = obj.getJSONArray("iceCreams");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                int id = jo_inside.getInt("id");
                String name = jo_inside.getString("name");
                String status = jo_inside.getString("status");
                String imageUrl = jo_inside.optString("imageUrl");

                IceCreamList.add(new IceCreamModel(price, id, name, status, imageUrl));


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        IceCreamItemAdapeter.notifyDataSetChanged();

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("icecreams.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.extras) {
            Intent intent = new Intent(this, ExtrasActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

}