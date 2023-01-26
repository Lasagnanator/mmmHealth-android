package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ReportActivity extends AppCompatActivity {

    SeekBar  skbUmore;
    EditText edtNotes;
    Button   btnManual;
    Button   btnWearable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        skbUmore = findViewById(R.id.skbUmore);
        edtNotes = findViewById(R.id.edtNotes);
        btnManual = findViewById(R.id.btnManual);
        btnWearable = findViewById(R.id.btnWearable);

    }
}