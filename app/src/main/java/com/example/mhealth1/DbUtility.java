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
    //##                         Tutta parte da implementare con backend,                        ##
    //##              query provvisorie e colonne risultato sicuramente sbagliate                ##
    //##                                                                                         ##
    //#############################################################################################

    /**
     * cerca e restituisce il nome pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserName(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "AnGiMa";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT name FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce il cognome pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastName(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "CaDiLa";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT lastname FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce il sesso pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserSex(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "Male";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT sex FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce la data di nascita pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserBirthday(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "21/11/2022";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT birthdate FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce l'altezza pel paziente nel db
     * @param id String REQUIRE not null;
     * @return string
     */
    public static String GetUserHeight(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "nella media";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT height FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce la data dell'ultima visita del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastReport(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "manca la parte del report :(";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT last_report FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce il piano medico pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getMedicalPlan(String id) throws SQLException {
        if (id.equals("alphabeto")){ return "rilassatevi, il lavoro è ancora lungo :) \n consiglio due caffè al giorno \n consiglio la meditazione ";} else {

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT medical_plan FROM pazienti WHERE id = " + id);
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
        }
    }

    /**
     * cerca e restituisce il numero di telefono del medico curante del paziente nel db
     * setta il numero del medico curante in base all'id del paziente
     * @param id String REQUIRE not null
     * @return String
     */
    public static String getPhoneNumber(String id) throws SQLException {
            if (id.equals("alphabeto")){  return "1234567890"; } else {

                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT phone_number FROM pazienti LEFT JOIN doctor ON doctor_id = doctor_id WHERE patient_id = " + id);
                String result = rs.getString(1);
                rs.close();
                st.close();
                return result;
            }
    }

    /**
     * cerca e restituisce la data dell'ultimo report del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastAccess(String id) throws SQLException {
                if (id.equals("alphabeto")){ return "probabilmente oggi"; } else {

                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery("SELECT last_access FROM pazienti WHERE id = " + id);
                    String result = rs.getString(1);
                    rs.close();
                    st.close();
                    return result;
                }
    }

    /**
     * cerca e restituisce la data dell'ultima visita del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastVisit(String id) throws SQLException {
                    if (id.equals("alphabeto")){ return "mai";} else {

                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT last_visit FROM pazienti WHERE id = " + id);
                        String result = rs.getString(1);
                        rs.close();
                        st.close();
                        return result;
                    }
    }

    /**
     * carica i dati di feeling, peso ed eventuali note del paziente nel db
     * trasmette i dati di seekbar e note al database
     * @param id String REQUIRE not null
     */
    public static void submitData1(String id){ // da implementare
    }

}
