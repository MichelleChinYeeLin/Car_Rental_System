import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {

    public enum Color {
        ANY,
        BLACK,
        WHITE,
        SILVER,
        RED,
        BLUE,
        YELLOW,
    }

    private String numberPlate;
    private String brand;
    private String model;
    private Color color;
    private int level;
    private double price;
    private boolean availability;
    public static final String pricePattern = "\\d+(.\\d{1,2})?"; // at most 1 "." and two tailing numbers

    public Car(){
        this.numberPlate = "";
        this.brand = "";
        this.model = "";
        this.color = Color.ANY;
        this.level = 0;
        this.price = 0.0;
        this.availability = false;
    }

    public Car(String numberPlate, String brand, String model, Color color, int level, double price, boolean availability){
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
        return String.valueOf(color);
    }
    public Color getColorType(){return color;}

    public void setColor(Color color) {
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

    private static double validateCarDetails(boolean flag, String numberPlate, String brand, String model, Color color, String price) {
        double priceInDouble = -1;
        try {
            if (numberPlate.equals("") || brand.equals("") || model.equals("")) {
                throw new EmptyInputException();
            }

            for (Car car : FileIO.carList) {
                if (car.numberPlate.equals(numberPlate) && !flag) {
                    throw new NumberPlateTakenException();
                }
            }

            if (color == Color.ANY) {
                throw new InvalidColorException();
            }

            Pattern pricePattern = Pattern.compile(Car.pricePattern);
            Matcher priceMatcher = pricePattern.matcher(price);
            if (!priceMatcher.matches()) throw new InvalidPriceException();
            priceInDouble = Double.parseDouble(price);

        } catch (EmptyInputException emptyInputException) {
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (NumberPlateTakenException numberPlateTakenException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Number plate already taken!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (InvalidColorException invalidColorException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can't select \"ANY\" color!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (InvalidPriceException invalidPriceException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Price must be numbers only!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException numberFormatException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid price format!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return priceInDouble;
    }

    public static void addCar(String numberPlate, String brand, String model, Color color, int level, String price){

        double priceInDouble = validateCarDetails(false, numberPlate, brand, model, color, price);
        if (!(priceInDouble == -1)){
            Car newCar = new Car(numberPlate, brand, model, color, level, priceInDouble, true);
            FileIO.carList.add(newCar);
            FileIO.writeCarFile();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Car added Successfully!");
        }
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

            if (!color.equals("ANY")) {

                if (!car.getColor().equals(color)) {
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

    public static void editCarDetails(int numberValue, String numberPlate, String brand, String model, Color color, int level, String price, boolean availability){

        boolean flag = true;
        Car car = FileIO.carList.get(numberValue - 1);
        if (!car.numberPlate.equals(numberPlate)){
            flag = false;
        }
        double priceInDouble = validateCarDetails(flag, numberPlate, brand, model, color, price);

        car.setNumberPlate(numberPlate);
        car.setBrand(brand);
        car.setModel(model);
        car.setColor(color);
        car.setLevel(level);
        car.setPrice(priceInDouble);
        car.setAvailability(availability);
        FileIO.writeCarFile();
    }

    public static void deleteCar(int numberValue){
        FileIO.carList.remove(numberValue - 1);
        FileIO.writeCarFile();
    }
}
