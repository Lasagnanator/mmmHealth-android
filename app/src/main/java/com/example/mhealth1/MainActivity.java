package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    ImageView imgUser;
    TextView  txtName;
    TextView  txtLastName;
    TextView  txtLastAccess;
    TextView  txtUserProfile;
    TextView  txtMedicalPlan;
    TextView  txtDaily;
    ImageView imgProfileButton;
    ImageView imgPlanButton;
    ImageView imgReportButton;
    String userSex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgUser          = findViewById(R.id.imgUser);
        txtName          = findViewById(R.id.txtName);
        txtLastName      = findViewById(R.id.txtLastName);
        txtLastAccess    = findViewById(R.id.txtLastAccess);
        txtUserProfile   = findViewById(R.id.txtUserProfile);
        txtMedicalPlan   = findViewById(R.id.txtMedicalPlan);
        txtDaily         = findViewById(R.id.txtDaily);
        imgProfileButton = findViewById(R.id.imgProfileButton);
        imgPlanButton    = findViewById(R.id.imgPlanButton);
        imgReportButton  = findViewById(R.id.imgReportButton);

        String userId  = getIntent().getExtras().getString("id");
        try {
            setUserInformations(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "No user found", Toast.LENGTH_SHORT).show();
        }

        imgProfileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                launchProfileActivity(userId);
                return false;
            }
        });

        imgReportButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                launchReportActivity(userId);
                return false;
            }
        });

        imgPlanButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                launchPlanActivity(userId);
                return false;
            }
        });
    }

    /**
     * Update user informations on main activity
     * @param id String REQUIRE not null
     */
    public void setUserInformations(String id) throws SQLException {
        //search user by id
        txtName.setText("Name: " + DbUtility.getUserName(id));
        txtLastName.setText("Lastname: " + DbUtility.getUserLastName(id));
        txtLastAccess.setText(("Last Access: " + DbUtility.getUserLastAccess(id)));
        userSex = DbUtility.getUserSex(id);
        setUserImage(id);
    }

    /**
     * Setta l'immagine di avata in base al sesso del paziente
     * @param id String REQUIRE not null
     */
    public void setUserImage(String id) {
        if (userSex.equals("Male")) {
            imgUser.setImageResource(R.drawable.maleavatar);
        } else {
            imgUser.setImageResource(R.drawable.femaleavatar);
        }
    }

    /**
     * Lancia la Pagina del profilo utente passando l'id dell'utente che ha effettuato l'accesso;
     * @param id String REQUIRE not null
     */
    public void launchProfileActivity(String id){
        Intent i = new Intent(this, ProfileActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    /**
     * Lancia la Pagina del piano medico passando l'id dell'utente che ha effettuato l'accesso;
     * @param id String REQUIRE not null
     */
    public void launchPlanActivity(String id){
        Intent i = new Intent(this, PlanActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    /**
     * Lancia la Pagina del report giornaliero passando l'id dell'utente che ha effettuato l'accesso;
     * @param id String REQUIRE not null
     */
    public void launchReportActivity(String id){
        Intent i = new Intent(this, ReportActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }

    /**
     * dalla main activity non fare niente con il tasto indietro
     */
    @Override
    public void onBackPressed() {
        //do nothing
    }
}// END ACTIVITY