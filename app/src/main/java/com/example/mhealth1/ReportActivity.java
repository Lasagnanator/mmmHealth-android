package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity {

    SeekBar  skbUmore;
    EditText edtNotes;
    TextView txtReportNotes;
    EditText edtWeight;
    Button   btnManual;
    Button   btnWearable;
    String   userId;

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
        userId          = getIntent().getExtras().getString("id");

        // define default seekbar values
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
                try {
                    launchDataActivity(userId);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Assicurati di non aver lasciato campi vuoti", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnWearable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               launchWeareble(userId);
            }
        });
    }

    /**
     * launch data activity to insert other data manually
     * @param id String REQUIRE not null
     */
    public void launchDataActivity (String id) {

        String notes = "'"+edtNotes.getText() +"'";
        Intent i = new Intent(getApplicationContext(), DataActivity.class);
        i.putExtra("id", userId);
        i.putExtra("feelings", skbUmore.getProgress());
        i.putExtra("weight", Integer.parseInt(String.valueOf(edtWeight.getText())));
        i.putExtra("notes", notes);
        startActivity(i);
    }

    /**
     * launch wearable app
     * @param id String REQUIRE not null
     */
    public void launchWeareble (String id) {
        // da implementare
    }

}// END ACTIVITY