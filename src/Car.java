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

    public void addCar(String numberPlate, String brand, String model, String color, String levelText, double price){

    }

    public static ArrayList<Car> searchCar(String numberPlate, String brand, String model, String color, String levelText, double price, String availabilityText) {
        ArrayList<Car> carList = FileIO.getCarList();
        ArrayList<Car> searchedList = new ArrayList<>();

        for(Car car : carList){
            boolean isMatch = true;

            if (!car.getNumberPlate().toLowerCase().contains(numberPlate.toLowerCase())) {
                isMatch = false;
            }

            if (!car.getBrand().toLowerCase().contains(brand.toLowerCase())) {
                isMatch = false;
            }

            if (!car.getModel().toLowerCase().contains(model.toLowerCase())){
                isMatch = false;
            }

            if (!color.equals("Any")){

                if (!car.getColor().equalsIgnoreCase(color)){
                    isMatch = false;
                }
            }

            if (!levelText.equals("Any")){
                if (car.getLevel() != Integer.parseInt(levelText)){
                    isMatch = false;
                }
            }

            if (car.getPrice() > price){
                isMatch = false;
            }

            if (!availabilityText.equals("Any")){

                boolean availability = availabilityText.equalsIgnoreCase("Available");

                if (car.isAvailability() != availability){
                    isMatch = false;
                }
            }

            if (isMatch){
                searchedList.add(car);
            }
        }
        return searchedList;
    }
}
