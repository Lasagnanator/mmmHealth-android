package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {
    ListView lstPressioneMin;
    Integer[] valori = {40, 50, 60, 70, 80, 90, 100};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        lstPressioneMin = findViewById(R.id.lstPressioneMin);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, valori);
        lstPressioneMin.setAdapter(adapter);
        lstPressioneMin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(
                        getApplicationContext(),
                        "Hai selezionato l'elemento " + valori[position],
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}