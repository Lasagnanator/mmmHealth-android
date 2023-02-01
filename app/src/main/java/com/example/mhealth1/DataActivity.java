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
   int sys;
   int dia;
   int bpm;
   int spo2;

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
                setDataValues();
                if (validateData()) {
                    try {
                        DbUtility.submitData1(userId, feelings, weight, notes, sys, dia, bpm, spo2);
                        Toast.makeText(getApplicationContext(), "Thanks for your report! :)", Toast.LENGTH_SHORT).show();
                        launchMainActivity(userId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Controlla i dati inseriti, ci sono valori non validi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * if no data is insert set to 0 to avoid type error also set editText values
     */
    public void setDataValues(){
        if (String.valueOf(edtSYS.getText()).equals(""))  { sys=0;}  else { sys  = Integer.parseInt(String.valueOf(edtSYS.getText())); }
        if (String.valueOf(edtDIA.getText()).equals(""))  { dia=0;}  else { dia  = Integer.parseInt(String.valueOf(edtDIA.getText())); }
        if (String.valueOf(edtBPM.getText()).equals(""))  { bpm=0;}  else { bpm  = Integer.parseInt(String.valueOf(edtBPM.getText())); }
        if (String.valueOf(edtSPO2.getText()).equals("")) { spo2=0;} else { spo2 = Integer.parseInt(String.valueOf(edtSPO2.getText())); }
    }

    /**
     * check if data insert in form is valid, return true if ok
     * return false if one of them is invalid
     * @return boolean
     */
    public boolean validateData(){
        if ((100 > sys || sys > 200) ||
                (50 > dia || dia > 100) ||
                (40> bpm || bpm >200) ||
                (60> spo2 || spo2 > 100)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * launch main activity and kill de current one
     * @param id
     */
    public void launchMainActivity(String id){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }
}// END ACTIVITY