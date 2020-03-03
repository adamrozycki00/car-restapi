package pl.adaroz.springboot2.homework3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adaroz.springboot2.homework3.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarApi {

    private List<Car> listOfCars;

    public CarApi() {
        this.listOfCars = new ArrayList<>();
        listOfCars.add(new Car(1L, "Toyota", "Avensis", "silver"));
        listOfCars.add(new Car(2L, "Porsche", "911", "blue"));
        listOfCars.add(new Car(3L, "BMW", "GT", "black"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(listOfCars, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == id).findFirst();
        if (optCar.isPresent())
            return new ResponseEntity<>(optCar.get(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color) {
        List<Car> carsOfColor = listOfCars.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toList());
        if (!carsOfColor.isEmpty())
            return new ResponseEntity<>(carsOfColor, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        boolean added = listOfCars.add(car);
        if (added)
            return new ResponseEntity(HttpStatus.CREATED);
        else
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity modCar(@RequestBody Car newCar) {
        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if (optCar.isPresent()) {
            listOfCars.remove(optCar.get());
            listOfCars.add(newCar);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PatchMapping
    public ResponseEntity modColor(@RequestHeader long id,
                                   @RequestHeader("color") String newColor) {
        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == id).findFirst();
        if (optCar.isPresent()) {
            optCar.get().setColor(newColor);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Car> removeCar(@PathVariable long id) {
        Optional<Car> optCar = listOfCars.stream().filter(car -> car.getId() == id).findFirst();
        if (optCar.isPresent()) {
            listOfCars.remove(optCar.get());
            return new ResponseEntity<>(optCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
