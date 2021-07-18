package com.example.bead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {


    private CollectionReference mItems;
    private FirebaseFirestore mFirestore;
    private ArrayList<SalesLead> mItemsData;

    private RecyclerView mRecyclerView;
    private ShopAdapter mAdapter;

    private int gridNumber = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shop);
        setContentView(R.layout.shop_list);
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");

       // name = findViewById(R.id.name);


        // recycle view
        mRecyclerView = findViewById(R.id.recyclerView);
        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(
                this, gridNumber));
        // Initialize the ArrayList that will contain the data.
        mItemsData = new ArrayList<>();
        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new ShopAdapter(this, mItemsData);
        mRecyclerView.setAdapter(mAdapter);



        initializeData();
    }
    

    private void initializeData() {
        String[] salesNames = getResources()
                .getStringArray(R.array.sales_names);
        String[] salesDescription = getResources()
                .getStringArray(R.array.sales_description);
        String[] salesHref = getResources()
                .getStringArray(R.array.sales_href);


        mItemsData.clear();

        for (int i = 0; i < salesNames.length; i++) {
            mItems.add(new SalesLead(
                    i+1,
                    salesHref[i],
                    salesDescription[i],
                    salesNames[i]));
        }
        // Recycle the typed array.
        //itemsImageResources.recycle();



        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();



    }


}