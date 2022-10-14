public class Car {

    private String brand;
    private String model;
    private String color;
    private double price;
    private String numberPlate;
    private boolean availability;

    public Car(String brand, String model, String color, double price, String numberPlate, boolean availability){
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
        this.numberPlate = numberPlate;
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

    public boolean isAvailability() {
        return availability;
    }

    public void updateCarStatus(){
        if (availability == true)
            availability = false;
        else
            availability = true;
    }
}
