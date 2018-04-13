package com.jitooi.multipleactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
>>>>>>> e0e58fe... refresh app, set up listview with list of names

import java.util.ArrayList;

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
public class MainActivity extends AppCompatActivity {
/*
>>>>>>> e0e58fe... refresh app, set up listview with list of names
    public void goToNext( View view){
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("age", 28);
        startActivity(intent);
    }
<<<<<<< HEAD

>>>>>>> 948fd7e... pass values from one activity to another using intent
=======
*/
>>>>>>> e0e58fe... refresh app, set up listview with list of names
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        final ArrayList<String> friends = new ArrayList<String>();

        friends.add("Zack");
        friends.add("Yves");
        friends.add("Xavier");
        friends.add("Wu");
        friends.add("Venedict");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, friends);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("name", friends.get(i));
                startActivity(intent);
            }
        });

    }
}
