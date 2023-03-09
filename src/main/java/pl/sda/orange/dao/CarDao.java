package pl.sda.orange.dao;

import pl.sda.orange.entity.Car;

import java.util.List;

public class CarDao implements DataAccess<Car, Long>{

    @Override
    public void save(Car car){
    }

    @Override
    public List<Car> findAll(){
        return List.of(); //unikamy zwracania nulli
    }

    @Override
    public Car findById(Long id){
        return null;
    }

    @Override
    public void deleteById(Long id){
    }
}
