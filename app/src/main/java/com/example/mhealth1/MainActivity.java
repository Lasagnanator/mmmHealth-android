package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imgUser;
    TextView txtName;
    TextView txtLastName;
    TextView txtAge;
    TextView txtLastAccess;
    TextView txtUserProfile;
    TextView txtMedicalPlan;
    TextView txtDaily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgUser         = findViewById(R.id.imgUser);
        txtName         = findViewById(R.id.txtName);
        txtLastName     = findViewById(R.id.txtLastName);
        txtAge          = findViewById(R.id.txtAge);
        txtLastAccess   = findViewById(R.id.txtLastAccess);

        txtUserProfile  = findViewById(R.id.txtUserProfile);
        txtMedicalPlan  = findViewById(R.id.txtMedicalPlan);
        txtDaily        = findViewById(R.id.txtDaily);

        String userId  = getIntent().getExtras().getString("id");
        setUserInformations(userId);

    }

    /**
     * Update user informations on main activity
     * @param id String REQUIRE not null
     */
    public void setUserInformations(String id) {
        //search user by id
        txtName.setText("Name: " + getUserName(id));
        txtLastName.setText("Lastname: " + getUserLastName(id));
        txtAge.setText("Age: " + getUserAge(id));
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
        String userSex = "male";

        if (userSex.equals("male")) {
            imgUser.getResources().getDrawable(R.drawable.maleavatar);
        } else {
            imgUser.getResources().getDrawable(R.drawable.femaleavatar);
        }
    }
}