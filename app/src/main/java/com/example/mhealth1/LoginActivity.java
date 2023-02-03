package com.example.mhealth1;

import static com.example.mhealth1.DbUtility.Classes;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    TextView   txtAccesso;
    EditText   edtUserName;
    EditText   edtPassword;
    Button     btnLogin;
    String     userId;
    ImageView  logoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtAccesso  = findViewById(R.id.txtResult);
        edtUserName = findViewById(R.id.edtUsrName);
        edtPassword = findViewById(R.id.edtPasswd); //aggiungere funzione di hash per la pw
        btnLogin    = findViewById(R.id.btnEnter);
        logoApp     = findViewById(R.id.logoApp);
        logoApp.setImageResource(R.drawable.itsvolta);

        //connect the app to de online database and report the state
        if (DbUtility.connectionToDb()){
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Connected no", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * ricerca l'id nel database, se l'id utente è valido lancia la main activity,
     * altrimenti nega l'accesso
     * @param v
     */
    public void effettuaAccesso (View v) throws SQLException, NoSuchAlgorithmException {
        String user = String.valueOf(edtUserName.getText());
        String password = String.valueOf(edtPassword.getText());
        userId = DbUtility.getUserid(user, password);

        if (!userId.equals("invalid")) {
            txtAccesso.setText("Accesso effettuato!");
            txtAccesso.setTextColor(Color.parseColor("green"));
            txtAccesso.setVisibility(View.VISIBLE);
            // add delay
            launchMainActivity(userId);
        } else {
            txtAccesso.setText("Accesso negato!");
            txtAccesso.setTextColor(Color.parseColor("red"));
            txtAccesso.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Lancia la Pagina principale passando l'id dell'utente che ha effettuato l'accesso;
     * @param id String REQUIRE not null
     */
    public void launchMainActivity(String id){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("id", id);
        startActivity(i);
        finish();
    }

}// END ACTIVITY

