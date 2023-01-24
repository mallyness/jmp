package com.example.jmp.cloud.service.database;

import com.example.jmp.dto.User;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class H2Database {

    private static final String JDBC_URL = "jdbc:h2:mem:jmp";

    private static final Logger LOGGER = LogManager.getLogger(H2Database.class);
    public static final String DATABASE_SCHEMA_PATH = "cloud-service-impl/src/main/resources/database_schema.sql";


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String initDatabase = Files.readString(Path.of(DATABASE_SCHEMA_PATH));
            Statement statement = connection.createStatement();
            statement.execute(initDatabase);
            LOGGER.info("Created tables");

            String sql = "insert into PUBLIC.USERS (NAME, SURNAME) values ('Bob', 'Smith');";
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                LOGGER.info("Inserted a new row.");
            }

            sql = "select * FROM USERS";
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                users.add(user);
            }
            users.forEach(LOGGER::info);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
