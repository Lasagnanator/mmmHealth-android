package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

        //String userId  = getIntent().getExtras().getString("id");
        String userId = "testid";
        setUserInformations(userId);

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
    public void setUserInformations(String id) {
        //search user by id
        txtName.setText("Name: " + getUserName(id));
        txtLastName.setText("Lastname: " + getUserLastName(id));
        txtLastAccess.setText(("Last Access: " + getUserLastAccess(id)));
        setUserImage(id);
    }

    //#############################################################################################
    //##                                                                                         ##
    //##           Tutta parte da implementare con backend o array di users                      ##
    //##                                                                                         ##
    //#############################################################################################
    /**
     * @param id String REQUIRE not null;
     * @return String
     */
    public String getUserName(String id){
        return "Mario";
    }

    /**
     * @param id String REQUIRE not null;
     * @return String
     */
    public String getUserLastName(String id){
        return "Rossi";
    }

    /**
     * @param id String REQUIRE not null;
     * @return String
     */
    public String getUserAge(String id){
        return "26";
    }
    /**
     * @param id String REQUIRE not null;
     * @return String
     */
    public String getUserLastAccess(String id){
        return "31/02/1996";
    }

    public void setUserImage(String id) {
        // Da implementare
        String userSex = "Male";

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
}