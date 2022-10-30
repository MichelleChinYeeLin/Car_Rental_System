public class Car {

    private String numberPlate;
    private String brand;
    private String model;
    private String color;
    private double price;
    private boolean availability;

    public Car(){
        this.numberPlate = "";
        this.brand = "";
        this.model = "";
        this.color = "";
        this.price = 0.0;
        this.availability = false;
    }

    public Car(String numberPlate, String brand, String model, String color, double price, boolean availability){
        this.numberPlate = numberPlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
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

    public void updateCarStatus(){
        if (availability == true)
            availability = false;
        else
            availability = true;
    }
}
