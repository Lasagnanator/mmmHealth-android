package com.example.mhealth1;

import static com.example.mhealth1.DbUtility.Classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    TextView  txtAccesso;
    EditText  edtUserName;
    EditText  edtPassword;
    Button    btnLogin;
    String    defaultUser     = "admin";
    String    defaultPassword = "password";
    String    userId;
    ImageView logoApp;


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
    }

    /**
     * controlla le credenziali, se sono corrette lancia la main activity e instaura una connessione con il db
     * @param v
     */
    public void effettuaAccesso (View v)  {
        String user = String.valueOf(edtUserName.getText());
        String password = String.valueOf(edtPassword.getText());

        if (user.equals(defaultUser) && password.equals(defaultPassword)) {
            txtAccesso.setText("Accesso effettuato!");
            txtAccesso.setTextColor(Color.parseColor("green"));
            txtAccesso.setVisibility(View.VISIBLE);
            userId = getUserid(user, password);
            // add delay
            connectionToDb();
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
    }

    /** ### DA IMPLEMENTARE
     * recupera dal DB l'id dell'utente che ha fatto login
     * @param user String REQUIRE not null
     * @param password String REQUIRE not null
     * @return String id user
     */
    public String getUserid(String user, String password){
        return "testid";
    }

    /**
     * Instaura la connessione con il database
     */
    public void connectionToDb(){
        Connection connection = null;
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(DbUtility.url, DbUtility.username, DbUtility.password);
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Class fail", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Connected no", Toast.LENGTH_SHORT).show();
        }
    }
}

