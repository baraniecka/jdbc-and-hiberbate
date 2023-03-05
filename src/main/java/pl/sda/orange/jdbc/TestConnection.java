package pl.sda.orange.jdbc;

import pl.sda.orange.jdbc.config.H2Config;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class TestConnection {

    public static void main(String[] args) {
        String query = """ 
                SELECT ID, NAME
                FROM TEST
                """;


        //klasa umożliwiająca stworzenie połączenia z bazą danych
        try {
            //rura / pipe
            //var zastępuje długą nazwę zmiennej - tutaj zamiast Connection
            //typ wyciągany z przypisania, nie można zrobić niezainicjalizowanego vara
            //działa do zmiennych lokalnych, nie działa do klasowych

            //we need jdbc driver (h2 dependency at pom)
            //connection gave us access to existing db
            var h2Connection = DriverManager.getConnection(H2Config.DB_URL,
                    H2Config.USER,
                    H2Config.PASSWORD);
            System.out.println("got connection: " + (h2Connection != null));

            //pojedyncze zapytanie które wysyłam w obrębie połączenia
            //statement
            //pojedyncze zapytanie do bazy danych
            //statement is used to send queries to db with existing connection
           Statement queryStatement = h2Connection.createStatement();

           //zapytanie sql
            //ResultSet - tabelka odpowiedzi
            //resultSet contains query result data as simple table
            //we need to iterate over result to got data
           ResultSet queryResult = queryStatement.executeQuery(query);
           while (queryResult.next()) {
               System.out.println("id: " + queryResult.getInt(1));
               System.out.println("name: " + queryResult.getString(2));
           }
        } catch (SQLException e) {
            System.out.println("got exception: " + e);
            e.printStackTrace();
        }
    }
}


