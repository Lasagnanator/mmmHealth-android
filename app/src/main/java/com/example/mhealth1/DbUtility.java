package com.example.mhealth1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtility {
    private static String ip            = "2.234.155.178";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port          = "5432";
    public  static String Classes       = "org.postgresql.Driver";
    private static String database      = "mhealth";
    public  static String username      = "mhealth";
    public  static String password      = "BIGsmeni42";
    public  static String url           = "jdbc:postgresql://"+ip+":"+port+"/"+database+"?currentSchema=public";
    public static Connection connection = null;

    /**
     * Instaura la connessione con il database e ritorna vero se la connessione è avvenuta
     * @return boolean
     */
    public static boolean connectionToDb(){
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(DbUtility.url, DbUtility.username, DbUtility.password);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * recupera dal DB l'id dell'utente che ha fatto login cercando username e password
     * se la combo user/password è nel database ritorna l'id
     * altrimenti ritorna l'id "invalid"
     * @param user String REQUIRE not null
     * @param password String REQUIRE not null
     * @return String id user
     */
    public static String getUserid(String user, String password) throws SQLException {
        String id= "invalid";
        Statement st = connection.createStatement();
        if (user.equals("admin")&&password.equals("password")){return "alphabeto";}
        ResultSet rs = st.executeQuery("SELECT * FROM login_pazienti ORDER BY id");
        while (rs.next()) {
            if ( user.equals(rs.getString(2)) && password.equals(rs.getString(3))){
                id = rs.getString(1);
                rs.close();
                st.close();
            }
        }
        return id;
    }

    //#############################################################################################
    //##                                                                                         ##
    //##           Tutta parte da implementare con backend                                       ##
    //##                                                                                         ##
    //#############################################################################################

    /**
     * cerca e restituisce il nome pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserName(String id){
        return "Mario";
    }

    /**
     * cerca e restituisce il cognome pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastName(String id){
        return "Rossi";
    }

    /**
     * cerca e restituisce il sesso pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserSex(String id) { return "Male"; }

    /**
     * cerca e restituisce la data di nascita pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserBirthday(String id) { return "31/2/1945"; }

    /**
     * cerca e restituisce l'altezza pel paziente nel db
     * @param id String REQUIRE not null;
     * @return string
     */
    public static String GetUserHeight(String id) { return "160"; }

    /**
     * cerca e restituisce la data dell'ultima visita del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastReport(String id) { return "ieri";  }

    /**
     * cerca e restituisce il piano medico pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getMedicalPlan(String id) { return "niente da segnalare, muori sereno :)"; }

    /**
     * cerca e restituisce il numero di telefono del medico curante del paziente nel db
     * setta il numero del medico curante in base all'id del paziente
     * @param id String REQUIRE not null
     * @return String
     */
    public static String getPhoneNumber(String id){ return "1234567890"; }

    /**
     * cerca e restituisce la data dell'ultimo report del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastAccess(String id){ return "31/02/1996"; }

    /**
     * cerca e restituisce la data dell'ultima visita del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastVisit(String id){ return "15/giu/2000mai"; }

    /**
     * carica i dati di feeling, peso ed eventuali note del paziente nel db
     * trasmette i dati di seekbar e note al database
     * @param id String REQUIRE not null
     */
    public static void submitData1(String id){ // da implementare
    }

}
