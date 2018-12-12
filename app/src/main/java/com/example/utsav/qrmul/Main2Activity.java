package com.example.utsav.qrmul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView= (ListView) findViewById(R.id.listView);
        arrayList=new ExampleDB(this).getData();
        CustomAdapter adapter=new CustomAdapter(this,arrayList);
        listView.setAdapter( adapter);

    }
}
