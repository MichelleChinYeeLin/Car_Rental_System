import java.util.ArrayList;

public class Car {

    private String numberPlate;
    private String brand;
    private String model;
    private String color;
    private int level;
    private double price;
    private boolean availability;

    public Car(){
        this.numberPlate = "";
        this.brand = "";
        this.model = "";
        this.color = "";
        this.level = 0;
        this.price = 0.0;
        this.availability = false;
    }

    public Car(String numberPlate, String brand, String model, String color, int level, double price, boolean availability){
        this.numberPlate = numberPlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.level = level;
        this.price = price;
        this.availability = availability;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public boolean isAvailable() {
        return availability;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void updateCarStatus(){
        if (availability == true)
            availability = false;
        else
            availability = true;
    }

    //Still thinking
    public static Car checkField(String numberPlate, String brand, String model, String color, int level, double price, boolean available, boolean flag){
        if (!flag){
            if (price == 0.0){
                if (!numberPlate.equals("") && brand.equals("") && model.equals("") && color.equals("") && level == 0){
                }
                else if (!numberPlate.equals("") && !brand.equals("") && model.equals("") && color.equals("") && level == 0){
                }
            }
            else {

            }
        }
        else {

        }
        return null;
    }

    public static ArrayList<Car> searchCar(String numberPlate, String brand, String model, String color, int level, double price, boolean available, boolean flag){
        ArrayList<Car> foundCars = new ArrayList<Car>();
        for (Car c : FileIO.carList) {
            if (numberPlate.equals(c.numberPlate)){
                foundCars.add(c);
            }
            if (brand.equals(c.brand)){
                foundCars.add(c);
            }
            if (model.equals(c.model)){
                foundCars.add(c);
            }
            if (color.equals(c.color)){
                foundCars.add(c);
            }
            if (level == c.level){
                foundCars.add(c);
            }
            if (price == c.price){
                foundCars.add(c);
            }
            if (flag){
                if (available == c.availability){
                    foundCars.add(c);
                }
            }
        }
        return foundCars;
    }

}
