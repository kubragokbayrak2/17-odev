package org.example;

import org.jdbi.v3.core.Jdbi;

public class Main {
    public static void main(String[] args) {

        //create table user
        //(
        //    id      int auto_increment
        //        primary key,
        //    name    varchar(255) null,
        //    surname varchar(255) null
        //);


        // Veritabanı bağlantı bilgileri
        String url = "jdbc:mysql://127.0.0.1:3306/db_1";
        String username = "root";
        String password = "123456";

        // Jdbi nesnesi oluştur
        Jdbi jdbi = Jdbi.create(url, username, password);

        // INSERT işlemi
        jdbi.useHandle(handle -> {
            handle.execute("INSERT INTO user (name, surname) VALUES (?, ?)", "Kübra", "Gökbayrak");
        });
        jdbi.useHandle(handle -> {
            handle.execute("INSERT INTO user (name, surname) VALUES (?, ?)", "Esra", "Gökbayrak");
        });

        // SELECT işlemi
        jdbi.useHandle(handle -> {
            handle.createQuery("SELECT name, surname FROM user")
                    .mapToMap()
                    .list()
                    .forEach(System.out::println);
        });

        // UPDATE işlemi
        jdbi.useHandle(handle -> {
            handle.createUpdate("UPDATE user SET name = :newName, surname = :newSurname WHERE id = :userId")
                    .bind("newName", "Ayşe")
                    .bind("newSurname", "AYDIN")
                    .bind("userId", 3)
                    .execute();
        });

        // DELETE işlemi
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM user WHERE id = :userId")
                    .bind("userId", 2)
                    .execute();
        });
    }
}