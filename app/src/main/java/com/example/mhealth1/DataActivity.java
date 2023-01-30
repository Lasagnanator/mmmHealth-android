package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DataActivity extends AppCompatActivity {
   EditText edtSYS;
   EditText edtDIA;
   EditText edtBPM;
   EditText edtSPO2;
   Button   btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        edtBPM    = findViewById(R.id.edtBPM);
        edtDIA    = findViewById(R.id.edtDIA);
        edtSYS    = findViewById(R.id.edtSYS);
        edtSPO2   = findViewById(R.id.edtSPO2);
        btnSubmit = findViewById(R.id.btnSubmit);
        String userId  = getIntent().getExtras().getString("id");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUtility.submitData2(userId);
                Toast.makeText(getApplicationContext(), "Thanks for your report! :)", Toast.LENGTH_SHORT).show();
                launchMainActivity(userId);
            }
        });
    }

    public void launchMainActivity(String id){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }

}