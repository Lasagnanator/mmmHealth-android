package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class ReportActivity extends AppCompatActivity {

    SeekBar  skbUmore;
    EditText edtNotes;
    TextView txtReportNotes;
    EditText edtWeight;
    Button   btnManual;
    Button   btnWearable;
    String userId = "testid"; //getIntent().getExtras().getString("id"); non funzia.... why?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        skbUmore        = findViewById(R.id.skbUmore);
        edtNotes        = findViewById(R.id.edtNotes);
        txtReportNotes  = findViewById(R.id.txtReportNotes);
        edtWeight       = findViewById(R.id.edtWeight);
        btnManual       = findViewById(R.id.btnManual);
        btnWearable     = findViewById(R.id.btnWearable);

        skbUmore.setProgress(51);
        skbUmore.incrementProgressBy(10);
        skbUmore.setMax(100);

        skbUmore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i <=50){
                    edtNotes.setVisibility(View.VISIBLE);
                    txtReportNotes.setVisibility(View.VISIBLE);
                } else {
                    edtNotes.setVisibility(View.INVISIBLE);
                    txtReportNotes.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DataActivity.class);
                i.putExtra("id", userId);
                startActivity(i);
            }
        });

        btnWearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //launch wearable app
            }
        });
    }
}