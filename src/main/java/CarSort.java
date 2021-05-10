import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarSort {

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("blue", 1));
        cars.add(new Car("yellow", 1));
        cars.add(new Car("green", 1));
        cars.add(new Car("green", 2));
        Map<String, List<Car>> carsByColor = new HashMap<>();
        for (Car car : cars) {
            if (carsByColor.containsKey(car.color)) {
                carsByColor.get(car.color).add(car);
            } else {
                List<Car> list = new ArrayList<>();
                list.add(car);
                carsByColor.put(car.color, list);
            }
        }
        System.out.println("map of cars by color:" + carsByColor);
    }

    static class Car {
        private String color;
        private int num;

        public Car(String color, int num) {
            this.color = color;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Car:color=" + color + ", num=" + num;
        }
    }
}
