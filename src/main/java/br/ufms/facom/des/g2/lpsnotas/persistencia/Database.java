package br.ufms.facom.des.g2.lpsnotas.persistencia;

public class Database {

    private static String userName = "root";
    private static String passord = "mysql";
    private static String dbName   = "lpsnotas";
    private static String driver   = "com.mysql.cj.jdbc.Driver";
    private static String URL      = "";

    public static void setProperties(String userName, String password, String dbName) {
        Database.userName = userName;
        Database.passord = password;
        Database.dbName = dbName;
        Database.URL = String.format("jdbc:mysql://localhost:3306/%s?createDatabaseIfNotExist=true", dbName);
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassord() {
        return passord;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getURL() {
        return URL;
    }
}
