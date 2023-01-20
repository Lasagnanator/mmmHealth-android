package com.example.mhealth1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView txtAccesso;
    EditText edtUserName;
    EditText edtPassword;
    Button   btnLogin;
    String   defaultUser     = "admin";
    String   defaultPassword = "password";
    String   userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtAccesso  = findViewById(R.id.txtAccesso);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword); //aggiungere funzione di hash per la pw
        btnLogin    = findViewById(R.id.btnEntra);
    }

    public void effettuaAccesso (View v)  {

        String user = String.valueOf(edtUserName.getText());
        String password = String.valueOf(edtPassword.getText());

        /* Nasconde la tastiera */
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        /************************/

        if (user.equals(defaultUser) && password.equals(defaultPassword)) {
            txtAccesso.setText("Accesso effettuato!");
            txtAccesso.setTextColor(Color.parseColor("green"));
            txtAccesso.setVisibility(View.VISIBLE);
            userId = getUserid(user, password);
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
}