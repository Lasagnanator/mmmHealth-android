package com.example.mhealth1;

import android.os.StrictMode;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DbUtility {
    private static String ip             = "2.234.155.178";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port           = "5432";
    public  static String Classes        = "org.postgresql.Driver";
    private static String database       = "mhealth";
    public  static String username       = "mhealth";
    public  static String password       = "BIGsmeni42";
    public  static String url            = "jdbc:postgresql://"+ip+":"+port+"/"+database+"?currentSchema=public";
    public  static Connection connection = null;

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
    public static String getUserid(String user, String password) throws SQLException, NoSuchAlgorithmException {
        String id= "invalid";
        String hashedpwd = hashPassword(password);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from login_patient lp order by lp.patient_id ");

        while (rs.next()) {
            String dbUsername = rs.getString(1);
            String dbPassword = rs.getString(2);
            if ( user.equals(dbUsername) && hashedpwd.equals(dbPassword)){
                id = rs.getString(3);
                return id;
            }
        }
        rs.close();
        st.close();
        return id;
    }

    /**
     * cerca e restituisce il nome pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserName(String id) throws SQLException {
        String query = "select name from patient p where p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce il cognome pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastName(String id) throws SQLException {
        String query = "select lastname from patient p where p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce il sesso pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserSex(String id) throws SQLException {
        String query = "select sex from patient p where p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce la data di nascita pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserBirthday(String id) throws SQLException {
       String query = "select birthday from patient p where p.patient_id = " + id;
       Statement st = connection.createStatement();
       ResultSet rs = st.executeQuery(query);
       rs.next();
       String result = rs.getString(1);
       rs.close();
       st.close();
       return result;
    }

    /**
     * cerca e restituisce l'altezza pel paziente nel db
     * @param id String REQUIRE not null;
     * @return string
     */
    public static String GetUserHeight(String id) throws SQLException {
        String query = "select height from patient p where p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce la data dell'ultima visita del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastReport(String id) throws SQLException {
        String query = "select r.date  from patient p inner join report r  \n" +
                "on p.patient_id  = r.patient_id \n" +
                "where p.patient_id  = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce il piano medico pel paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getMedicalPlan(String id) throws SQLException {
        String query = "select medical_plan from patient p where p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce il numero di telefono del medico curante del paziente nel db
     * setta il numero del medico curante in base all'id del paziente
     * @param id String REQUIRE not null
     * @return String
     */
    public static String getPhoneNumber(String id) throws SQLException {
            String query = "select phonenumber  from patient p inner join doctor d \n" +
                        "on p.doctor_id = d.doctor_id \n" +
                        "where p.patient_id = " + id;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String result = rs.getString(1);
            rs.close();
            st.close();
            return result;
    }

    /**
     * cerca e restituisce la data dell'ultimo report del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastAccess(String id) throws SQLException {
        String query ="select last_access from patient p where  p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        return result;
    }

    /**
     * cerca e restituisce la data dell'ultima visita del paziente nel db
     * @param id String REQUIRE not null;
     * @return String
     */
    public static String getUserLastVisit(String id) throws SQLException {
        String query = "select last_visit from patient p where  p.patient_id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String result = rs.getString(1);
        rs.close();
        st.close();
        if (result == null) {
            result= "ancora nessuna visita";
        }
        return result;
    }

    /**
     * carica i dati di feeling, peso, eventuali note, pressione, ecg e spo2 del paziente nel db
     * @param id String REQUIRE not null
     */
    public static void submitData1(String id, int feelings, int weight, String notes,  int SYS, int DIA, int BPM, int SpO2) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());

        String query =  "insert into report (feelings, weight, notes, sys, dia, bpm, spo2, date, patient_id) values ("+feelings+", "+weight+", "+notes+", "+SYS+", "+DIA+", "+BPM+", "+SpO2+", '"+date+"', "+id+")";
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        st.close();
    }

    /**
     * carica i dati di pressione, ecg e spo2 del paziente nel db
     * @param id String REQUIRE not null
     */
    public static void submitDataWearable(String id){ // da implementare
    }

    /**
     * setta l'ultimo accesso alla data odierna
     * @param id String REQUIRE not null
     */
    public static void setUserLastAccess(String id) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String date = sdf.format(c.getTime());

        String query = "update patient set last_access = '"+date+"' where patient_id = " + id;
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        st.close();
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        md.update(password.getBytes());
        byte[] mdArray = md.digest();
        StringBuilder sb = new StringBuilder(mdArray.length * 2);
        for(byte b : mdArray) {
            int v = b & 0xff;
            if(v < 16)
                sb.append('0');
            sb.append(Integer.toHexString(v));
        }

        return sb.toString();
    }

}
