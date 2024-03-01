package org.example;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;

import java.util.List;
import java.util.Map;

public class DatabaseOperations {

    private final Jdbi jdbi;

    public DatabaseOperations(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void insertUser(String name, String surname) {
        jdbi.useHandle(handle -> {
            handle.execute("INSERT INTO user (name, surname) VALUES (?, ?)", name, surname);
        });
    }

    public List<Map<String, Object>> selectUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT name, surname FROM user")
                        .mapToMap()
                        .list()
        );
    }

    public void updateUser(int userId, String newName, String newSurname) {
        jdbi.useHandle(handle -> {
            Update update = handle.createUpdate("UPDATE user SET name = :newName, surname = :newSurname WHERE id = :userId")
                    .bind("newName", newName)
                    .bind("newSurname", newSurname)
                    .bind("userId", userId);
            update.execute();
        });
    }

    public void deleteUser(int userId) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM user WHERE id = :userId")
                    .bind("userId", userId)
                    .execute();
        });
    }
}
