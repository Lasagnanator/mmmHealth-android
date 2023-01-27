package com.example.mhealth1;

import java.sql.Connection;

public class DbUtility {
    private static String ip = "2.234.155.178";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port = "5432";
    static String Classes = "org.postgresql.Driver";
    private static String database = "mhealth";
    public static String username = "mhealth";
    public static String password = "BIGsmeni42";
    public static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database+"?currentSchema=public";

    //private Connection connection = null;


}
