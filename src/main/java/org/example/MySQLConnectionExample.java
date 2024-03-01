package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mysql_container";
        String kullaniciAdi = "root";
        String sifre = "esc1453567";

        try {
            Connection connection = DriverManager.getConnection(url, kullaniciAdi, sifre);
            if (connection != null) {
                System.out.println("MySQL veritabanına bağlantı başarılı.");
                connection.close();
            }
        }catch (SQLException e){
            System.out.println("Bağlantı Hatası: " + e.getMessage());
        }
    }
}
