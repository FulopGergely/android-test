package com.example.bead;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Shop extends AppCompatActivity {

    private CollectionReference mItems;
    private FirebaseFirestore mFirestore;
    private ArrayList<SalesLead> mItemsData;

    private RecyclerView mRecyclerView;
    private ShopAdapter mAdapter;

    EditText editMessage;
    EditText editMessage2;
    EditText editMessage3;

    TextView name;
    TextView name2;
    TextView name3;
    TextView href;
    TextView href2;
    TextView href3;
    TextView description;
    TextView description2;
    TextView description3;
    boolean t = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop2);
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");



        editMessage = findViewById(R.id.editMessage);
        editMessage2 = findViewById(R.id.editMessage2);
        editMessage3 = findViewById(R.id.editMessage3);
        name = findViewById(R.id.name);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        href = findViewById(R.id.href);
        href2 = findViewById(R.id.href2);
        href3 = findViewById(R.id.href3);
        description = findViewById(R.id.description);
        description2 = findViewById(R.id.description2);
        description3 = findViewById(R.id.description3);
        if (t == true){
            initializeData();
            t = false;
        }

    }


    private void initializeData() {
        String[] salesNames = getResources()
                .getStringArray(R.array.sales_names);
        String[] salesDescription = getResources()
                .getStringArray(R.array.sales_description);
        String[] salesHref = getResources()
                .getStringArray(R.array.sales_href);


        //mItemsData.clear();

        for (int i = 0; i < salesNames.length; i++) {
            mItems.add(new SalesLead(
                    i,
                    salesHref[i],
                    salesDescription[i],
                    salesNames[i]));
        }

        name.setText(salesNames[0]);
        name2.setText(salesNames[1]);
        name3.setText(salesNames[2]);
        href.setText(salesHref[0]);
        href2.setText(salesHref[1]);
        href3.setText(salesHref[2]);
        description.setText(salesDescription[0]);
        description2.setText(salesDescription[1]);
        description3.setText(salesDescription[2]);


    }
    public void message(View view) {
        String message = editMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"furgegepp@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "care Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Send Email"));


    }
    public void message2(View view) {
        String message = editMessage2.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fogaskerek-kft@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "care Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
    public void message3(View view) {
        String message = editMessage3.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"JozsefTibor@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "care Feedback");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    public void website(View view) {

        String url = "https://www.furge-gep.hu/";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}