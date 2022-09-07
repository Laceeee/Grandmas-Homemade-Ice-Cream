package com.laci.grandmasicecream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ExtrasActivity extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView RecyclerViewCone;
    private androidx.recyclerview.widget.RecyclerView RecyclerViewOthers;
    private androidx.recyclerview.widget.RecyclerView RecyclerViewSyrup;
    private ArrayList<ExtrasModel> ConeList;
    private ArrayList<ExtrasModel> OthersList;
    private ArrayList<ExtrasModel> SyrupList;
    private ExtrasItemAdapter ConeExtrasItemAdapter;
    private ExtrasItemAdapter OthersExtrasItemAdapter;
    private ExtrasItemAdapter SyrupExtrasItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);

        RecyclerViewCone = findViewById(R.id.recyclerViewCone);
        RecyclerViewCone.setLayoutManager(new GridLayoutManager(this, 1));
        ConeList = new ArrayList<>();

        ConeExtrasItemAdapter = new ExtrasItemAdapter(this, ConeList);
        RecyclerViewCone.setAdapter(ConeExtrasItemAdapter);


        RecyclerViewOthers = findViewById(R.id.recyclerViewOther);
        RecyclerViewOthers.setLayoutManager(new GridLayoutManager(this, 1));
        OthersList = new ArrayList<>();

        OthersExtrasItemAdapter = new ExtrasItemAdapter(this, OthersList);
        RecyclerViewOthers.setAdapter(OthersExtrasItemAdapter);


        RecyclerViewSyrup = findViewById(R.id.recyclerViewSyrup);
        RecyclerViewSyrup.setLayoutManager(new GridLayoutManager(this, 1));
        SyrupList = new ArrayList<>();

        SyrupExtrasItemAdapter = new ExtrasItemAdapter(this, SyrupList);
        RecyclerViewSyrup.setAdapter(SyrupExtrasItemAdapter);

        initializeData();
    }

    private void initializeData() {
        ConeList.clear();
        OthersList.clear();
        SyrupList.clear();

        try {
            JSONArray outerArray = new JSONArray(loadJSONFromAsset());

            for (int i = 0; i < outerArray.length(); i++) {
                JSONObject firstLayer = outerArray.getJSONObject(i);
                boolean required = firstLayer.optBoolean("required");
                String type = firstLayer.getString("type");
                JSONArray innerArray = firstLayer.getJSONArray("items");
                double[] price = new double[innerArray.length()];
                String[] name = new String[innerArray.length()];
                long[] id = new long[innerArray.length()];
                for (int j = 0; j < innerArray.length(); j++) {
                    JSONObject secondLayer = innerArray.getJSONObject(j);
                    price[j] = secondLayer.getDouble("price");
                    name[j] = secondLayer.getString("name");
                    id[j] = secondLayer.getLong("id");
                }

                switch (i) {
                    case 0:
                        for (int j = 0; j < innerArray.length(); j++) {
                            ConeList.add(new ExtrasModel(required, type, id[j], name[j], price[j]));
                        }
                    case 1:
                        for (int j = 0; j < innerArray.length(); j++) {
                            OthersList.add(new ExtrasModel(required, type, id[j], name[j], price[j]));
                        }
                    case 2:
                        for (int j = 0; j < innerArray.length(); j++) {
                            SyrupList.add(new ExtrasModel(required, type, id[j], name[j], price[j]));
                        }
                    default:
                        break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ConeExtrasItemAdapter.notifyDataSetChanged();
        OthersExtrasItemAdapter.notifyDataSetChanged();
        SyrupExtrasItemAdapter.notifyDataSetChanged();

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("extras.json");
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
}