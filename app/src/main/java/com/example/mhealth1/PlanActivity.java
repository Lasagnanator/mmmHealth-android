package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class PlanActivity extends AppCompatActivity {

    TextView txtNotes;
    Button   btnCallMedico;
    String   PHONENUMBER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        txtNotes      = findViewById(R.id.txtNotes);
        btnCallMedico = findViewById(R.id.btnCallMedico);
        txtNotes.setHeight(3000);
        txtNotes.setBackgroundColor(getResources().getColor(R.color.white));
        txtNotes.setTextColor(getResources().getColor(R.color.black));
        try {
            setUserMedicalPlan(getIntent().getExtras().getString("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show();
        }

        btnCallMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPhoneApplication(getIntent().getExtras().getString("id"));
            }
        });
    }

    /**
     * apre il dialer e compone in automatico il numero di telefono
     * @param id String REQUIRE not null
     */
    public void launchPhoneApplication(String id) {
        Intent phone = new Intent(Intent.ACTION_DIAL);
        phone.setData(Uri.parse("tel:"+PHONENUMBER));
        startActivity(phone);
    }

    /**
     * inserisce il piano medico nella textview
     * @param id String REQUIRE not null
     */
    public void setUserMedicalPlan(String id) throws SQLException {
        String medicalPlan = DbUtility.getMedicalPlan(id);
        txtNotes.setText(medicalPlan);
        PHONENUMBER = DbUtility.getPhoneNumber(id);
    }
}// END ACTIVITY