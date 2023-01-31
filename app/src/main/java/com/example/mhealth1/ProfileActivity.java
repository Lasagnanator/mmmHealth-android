package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.sql.SQLException;

public class ProfileActivity extends AppCompatActivity {

    TextView name;
    TextView  lastname;
    TextView  sex;
    TextView  birthdate;
    TextView  height;
    TextView  lastVisit;
    TextView  lastReport;
    ImageView imgAvatar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name       = findViewById(R.id.name);
        lastname   = findViewById(R.id.lastname);
        birthdate  = findViewById(R.id.birthdate);
        sex        = findViewById(R.id.sex);
        height     = findViewById(R.id.height);
        lastVisit  = findViewById(R.id.lastVisit);
        lastReport = findViewById(R.id.lastReport);
        imgAvatar  = findViewById(R.id.imgAvatar);

        try {
            setUserData(getIntent().getExtras().getString("id"));
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "No user Found", Toast.LENGTH_SHORT).show();
        }
    }

    //#############################################################################################
    //##                                                                                         ##
    //##           Tutta parte da implementare con backend o array di users                      ##
    //##                                                                                         ##
    //#############################################################################################

    /**
     * cerca i paramentri dell'utente in base all'id e setta tutti i dati
     * @param id String REQUIRE not null
     */
    public void setUserData(String id) throws SQLException {
        name.setText(DbUtility.getUserName(id));
        lastname.setText(DbUtility.getUserLastName(id));
        birthdate.setText(DbUtility.getUserBirthday(id));
        sex.setText(DbUtility.getUserSex(id));
        height.setText(DbUtility.GetUserHeight(id));
        lastVisit.setText(DbUtility.getUserLastVisit(id));
        lastReport.setText(DbUtility.getUserLastReport(id));
        //RIVEDERE IF DOPO IMPLEMENTAZIONE BACKEND
        if ((sex.getText()).equals("Male")) {
            imgAvatar.setImageResource(R.drawable.maleavatar);
        } else {
            imgAvatar.setImageResource(R.drawable.femaleavatar);
        }

    }
}// END ACTIVITY