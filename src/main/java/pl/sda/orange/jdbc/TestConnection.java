package pl.sda.orange.jdbc;

import pl.sda.orange.jdbc.config.H2Config;

import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class TestConnection {

    public static void main(String[] args) {
        //klasa umożliwiająca stworzenie połączenia z bazą danych
        try {
            var h2Connection = DriverManager.getConnection(H2Config.DB_URL,
                    H2Config.USER,
                    H2Config.PASSWORD);
            System.out.println("got connection: " + (h2Connection != null));
        } catch (SQLException e) {
            System.out.println("got exception: " + e);
        }
    }
}


