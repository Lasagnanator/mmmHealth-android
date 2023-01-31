package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

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
        int feelings   = getIntent().getExtras().getInt("feelings");
        int weight     = getIntent().getExtras().getInt("weight");
        String notes   = getIntent().getExtras().getString("notes");


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DbUtility.submitData1(userId,
                                            feelings,
                                            weight,
                                            notes,
                                            Integer.parseInt(String.valueOf(edtSYS.getText())),
                                            Integer.parseInt(String.valueOf(edtDIA.getText())),
                                            Integer.parseInt(String.valueOf(edtBPM.getText())),
                                            Integer.parseInt(String.valueOf(edtSPO2.getText())) );
                    Toast.makeText(getApplicationContext(), "Thanks for your report! :)", Toast.LENGTH_SHORT).show();
                    launchMainActivity(userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "An error occured! :(", Toast.LENGTH_SHORT).show();
                }
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