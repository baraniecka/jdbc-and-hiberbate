package pl.sda.orange.dao;

import pl.sda.orange.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements DataAccess<Car, Long> {

    private final Connection dbConnection;

    //CREATE TABLE CARS (
    //ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    //COLOUR VARCHAR(255),
    //BRAND VARCHAR(255),
    //MODEL VARCHAR(255)
    //);

    public CarDao(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void save(Car car) {
    }

    @Override
    public List<Car> findAll() {
        //var is the same as List<Car> result = new ArrayList<Car>();
        var cars = new ArrayList<Car>();

        //tworzymy query

        //TODO: validate query
        String allCarsQuery = """
                SELECT ID, COLOUR, BRAND, MODEL
                FROM CARS
                """;
        try {
            Statement queryStatement = dbConnection.createStatement();
            //tabelka z odpowiedzią z bazy danych
            ResultSet queryResult = queryStatement.executeQuery(allCarsQuery);

            while (queryResult.next()) {
                // zapytanie po indeksach bardziej wydajne niż po nazwach
                Long id = queryResult.getLong(1);
                String colour = queryResult.getString(2);
                String brand = queryResult.getString(3);
                String model = queryResult.getString(4);

                Car carFromDb = new Car(id, colour, brand, model);
                cars.add(carFromDb);
            }
        } catch (SQLException e) {
            System.out.println("Unexpected SQL exception occurred");
            e.printStackTrace();
        }

        return cars; //unikamy zwracania nulli
    }

    @Override
    public Car findById(Long id) {
// w kotlinie można napisać Car? i wtedy może zwrócić nulla? albo nie?
        Car result = null;
        String carByIdQuery = """
                SELECT ID, COLOUR, BRAND, MODEL
                FROM CARS
                WHERE ID = ? 
                """;
        // nie sklejamy stringów w zapytaniu sql

        //prepareStatement możemy sparametryzować
        //query statement wymaga argumentu,potem w result set już nie muszę podawać
        try {
           PreparedStatement queryStatement = dbConnection.prepareStatement(carByIdQuery);
            queryStatement.setLong(1, id);
            ResultSet queryResult = queryStatement.executeQuery();

            if (queryResult.next()){
            result = new Car(queryResult.getLong("ID"),
                    queryResult.getString("COLOUR"),
                    queryResult.getString("BRAND"),
                    queryResult.getString("MODEL"));
            }

        } catch (SQLException e) {
            System.out.println("Unexpected SQL exception occurred");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {

        String deleteCarByIdQuery = """
                DELETE
                FROM CARS
                WHERE ID = ? 
                """;

        try {
            PreparedStatement queryStatement = dbConnection.prepareStatement(deleteCarByIdQuery);
            queryStatement.setLong(1, id);
            int numberOfTouchedRecords = queryStatement.executeUpdate();
            System.out.println("Number of touched records: " + numberOfTouchedRecords);



        } catch (SQLException e) {
            System.out.println("Unexpected SQL exception occurred");
            e.printStackTrace();
        }

    }
}
