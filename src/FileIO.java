//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
//import javafx.beans.property.SimpleIntegerProperty;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileIO {

    //File names
    private static final String adminFileName = "admins.txt";
    private static final String customerFileName = "customers.txt";
    private static final String carFileName = "cars.txt";
    private static final String bookingFileName = "bookings.txt";
    private static final String registrationFileName = "registration.txt";

    //Admin and customer
    private static final String userNameText = "Username: ";
    private static final String passwordText = "Password: ";
    private static final String nameText = "Name: ";
    private static final String phoneText = "Phone Number: ";
    private static final String genderText = "Gender: ";
    private static final String ageText = "Age: ";
    private static final String emailText = "Email: ";
    private static final String addressText = "Address: ";
    private static final String pointsText = "Points: ";

    //Car
    private static final String numberPlateText = "Number Plate: ";
    private static final String brandText = "Brand: ";
    private static final String modelText = "Model: ";
    private static final String colorText = "Color: ";
    private static final String levelText = "Level: ";
    private static final String priceText = "Price: ";
    private static final String availabilityText = "Available: ";

    //Booking
    private static final String totalPriceText = "Total Price: ";
    private static final String statusText = "Status: ";
    private static final String startDateText = "Start Date: ";
    private static final String endDateText = "End Date: ";

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    //ArrayLists
    public static ArrayList<Admin> adminList = new ArrayList<>();
    public static ArrayList<Customer> customerList= new ArrayList<>();
    public static ArrayList<Car> carList= new ArrayList<>();
    public static ArrayList<Booking> bookingList= new ArrayList<>();
    public static ArrayList<Customer> registrationList= new ArrayList<>();

    public static ArrayList<Admin> getAdminList() {

        if(adminList == null){
            readAdminFile();
        }

        return adminList;
    }

    public static void setAdminList(ArrayList<Admin> list) {
        adminList = list;
    }

    public static ArrayList<Customer> getCustomerList() {

        if(customerList == null){
            readCustomerFile();
        }

        return customerList;
    }

    public static void setCustomerList(ArrayList<Customer> list) {
        customerList = list;
    }

    public static ArrayList<Booking> getBookingList(){

        if(bookingList == null){
            readBookingFile();
        }

        return bookingList;
    }

    public static void setBookingList(ArrayList<Booking> list){bookingList = list;}

    public static ArrayList<Car> getCarList() {

        if(carList == null){
            readCarFile();
        }
        return carList;
    }

    public static void setCarList(ArrayList<Car> list) {
        carList = list;
    }

    public static ArrayList<Customer> getRegistrationList() {

        if(registrationList == null){
            readRegistrationFile();
        }
        return registrationList;
    }

    public static void setRegistrationList(ArrayList<Customer> list) {
        registrationList = list;
    }

    public static void readAdminFile(){
//        adminList = new ArrayList<>();

        try{
            FileReader fr = new FileReader(adminFileName);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null){
                String nextLine = br.readLine();

                String username = line.substring(userNameText.length());
                String password = nextLine.substring(passwordText.length());
                Admin admin = new Admin(username, password);
                adminList.add(admin);

                line = br.readLine();
                line = br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void writeAdminFile(){

        try{
            FileWriter fw = new FileWriter(adminFileName);

            for(Admin admin : adminList){
                fw.write(userNameText + admin.getUsername() + "\n");
                fw.write(passwordText + admin.getPassword() + "\n\n");
            }
            fw.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void readCustomerFile(){
//        customerList = new ArrayList<>();

        try{
            FileReader fr = new FileReader(customerFileName);
            BufferedReader br = new BufferedReader(fr);

            String username = "", password = "", name = "", phone = "", gender = "", email = "", address = "";
            int age = 0, points = 0;
            String line = br.readLine();
            String line2 = "";

            while(line != null){

                if(line.startsWith(userNameText)){
                    username = line.substring(userNameText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(passwordText)){
                    password = line.substring(passwordText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(nameText)){
                    name = line.substring(nameText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(phoneText)){
                    phone = line.substring(phoneText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(genderText)){
                    gender = line.substring(genderText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(ageText)){
                    age = Integer.parseInt(line.substring(ageText.length()));
                    line = br.readLine();
                }
                else if(line.startsWith(emailText)){
                    email = line.substring(emailText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(addressText)){
                    address = line.substring(addressText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(pointsText)){
                    points = Integer.parseInt(line.substring(pointsText.length()));

                    Customer customer = new Customer(username, password, name, phone, gender, age, email, address, points);
                    customerList.add(customer);

                    line2 = br.readLine();
                    line = br.readLine();
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (NumberFormatException numberFormatException){
            System.out.println("Unable to parse. Please try again.");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void writeCustomerFile(){
        try{
            FileWriter fw = new FileWriter(customerFileName);

            for(Customer customer : customerList){
                fw.write(userNameText + customer.getUsername() + "\n");
                fw.write(passwordText + customer.getPassword() + "\n");
                fw.write(nameText + customer.getName() + "\n");
                fw.write(phoneText + customer.getPhone() + "\n");
                fw.write(genderText + customer.getGender() + "\n");
                fw.write(ageText + customer.getAge() + "\n");
                fw.write(emailText + customer.getEmail() + "\n");
                fw.write(addressText + customer.getAddress() + "\n");
                fw.write(pointsText + customer.getPoints() + "\n\n");
            }
            fw.close();
        }
        catch(IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch(Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void readBookingFile(){
//        bookingList = new ArrayList<>();

        try {
            FileReader fr = new FileReader(bookingFileName);
            BufferedReader br = new BufferedReader(fr);

            Car car = new Car();
            Customer customer = new Customer();
            double totalPrice = 0.0;
            String status = "";
            Date startDate = new Date();
            Date endDate = new Date();
            String line = br.readLine();
            String line2 = "";

            while(line != null && line2 != null){

                if(line.startsWith(numberPlateText)){
                    String numberPlate = line.substring(numberPlateText.length());

                    for(Car nextCar : carList){
                        if(nextCar.getNumberPlate().equals(numberPlate)){
                            car = nextCar;
                            break;
                        }
                    }
                    line = br.readLine();
                }

                else if(line.startsWith(userNameText)){
                    String username = line.substring(userNameText.length());

                    for(Customer nextCustomer : customerList){
                        if(nextCustomer.getUsername().equals(username)){
                            customer = nextCustomer;
                            break;
                        }
                    }

                    line = br.readLine();
                }
                else if(line.startsWith(totalPriceText)){
                    totalPrice = Double.parseDouble(line.substring(totalPriceText.length()));
                    line = br.readLine();
                }
                else if(line.startsWith(statusText)){
                    status = line.substring(statusText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(startDateText)){
                    startDate = dateFormat.parse(line.substring(startDateText.length()));
                    line = br.readLine();
                }
                else if(line.startsWith(endDateText)){
                    endDate = dateFormat.parse(line.substring(endDateText.length()));

                    Booking booking = new Booking(car, customer, totalPrice, status, startDate, endDate);
                    bookingList.add(booking);

                    line2 = br.readLine();
                    line = br.readLine();
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void writeBookingFile(){
        try{
            FileWriter fw = new FileWriter(bookingFileName);

            for(Booking booking : bookingList){
                fw.write(numberPlateText + booking.getCar().getNumberPlate() + "\n");
                fw.write(userNameText + booking.getCustomer().getUsername() + "\n");
                fw.write(totalPriceText + booking.getTotalPrice() + "\n");
                fw.write(statusText + booking.getStatus() + "\n");
                fw.write(startDateText + dateFormat.format(booking.getStartDate()) + "\n");
                fw.write(endDateText + dateFormat.format(booking.getEndDate()) + "\n\n");
            }
            fw.close();
        }
        catch(IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch(Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void readCarFile(){
//        carList = new ArrayList<>();

        try{
            FileReader fr = new FileReader(carFileName);
            BufferedReader br = new BufferedReader(fr);

            String numberPlate = "", brand = "", model = "", color = "";
            int level = 0;
            double price = 0.0;
            boolean availability = false;
            String line = br.readLine();
            String line2 = "";

            while (line != null){

                if (line.startsWith(numberPlateText)){
                    numberPlate = line.substring(numberPlateText.length());
                    line = br.readLine();
                }
                else if (line.startsWith(brandText)){
                    brand = line.substring(brandText.length());
                    line = br.readLine();
                }
                else if (line.startsWith(modelText)){
                    model = line.substring(modelText.length());
                    line = br.readLine();
                }
                else if (line.startsWith(colorText)){
                    color = line.substring(colorText.length());
                    line = br.readLine();
                }
                else if (line.startsWith(levelText)){
                    level = Integer.parseInt(line.substring(levelText.length()));
                    line = br.readLine();
                }
                else if (line.startsWith(priceText)){
                    price = Double.parseDouble(line.substring(priceText.length()));
                    line = br.readLine();
                }
                else if (line.startsWith(availabilityText)){
                    availability = line.substring(availabilityText.length()).equals("true");
                    Car car = new Car(numberPlate, brand, model, color, level, price, availability);
                    carList.add(car);
                    line2 = br.readLine();
                    line = br.readLine();
                }

            }
            br.close();
            fr.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void writeCarFile(){
        try{
            FileWriter fw = new FileWriter(carFileName);

            for(Car car : carList){
                fw.write(numberPlateText + car.getNumberPlate() + "\n");
                fw.write(brandText + car.getBrand() + "\n");
                fw.write(modelText + car.getModel() + "\n");
                fw.write(colorText + car.getColor() + "\n");
                fw.write(priceText + car.getPrice() + "\n");
                fw.write(availabilityText + car.isAvailability() + "\n\n");
            }
            fw.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void readRegistrationFile(){
//        registrationList = new ArrayList<>();

        try{
            FileReader fr = new FileReader(registrationFileName);
            BufferedReader br = new BufferedReader(fr);

            String username = "", password = "", name = "",  phone = "", gender = "", email = "", address = "";
            int age = 0;
            String line = br.readLine();
            String line2 = "";

            while(line != null && line2 != null){

                if(line.startsWith(userNameText)){
                    username = line.substring(userNameText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(passwordText)){
                    password = line.substring(passwordText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(nameText)){
                    name = line.substring(nameText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(phoneText)){
                    phone = line.substring(phoneText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(genderText)){
                    gender = line.substring(genderText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(ageText)){
                    age = Integer.parseInt(line.substring(ageText.length()));
                    line = br.readLine();
                }
                else if(line.startsWith(emailText)){
                    email = line.substring(emailText.length());
                    line = br.readLine();
                }
                else if(line.startsWith(addressText)){
                    address = line.substring(addressText.length());
                    line2 = br.readLine();
                    line = br.readLine();

                    Customer customer = new Customer(username, password, name, phone, gender, age, email, address);
                    registrationList.add(customer);
                }
            }
            br.close();
            fr.close();
        }
        catch (IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch (NumberFormatException numberFormatException){
            System.out.println("Unable to parse. Please try again");
        }
        catch (Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void writeRegistrationFile(){
        try{
            FileWriter fw = new FileWriter(registrationFileName);

            for(Customer customer : registrationList){
                fw.write(userNameText + customer.getUsername() + "\n");
                fw.write(passwordText + customer.getPassword() + "\n");
                fw.write(nameText + customer.getName() + "\n");
                fw.write(phoneText + customer.getPhone() + "\n");
                fw.write(genderText + customer.getGender() + "\n");
                fw.write(ageText + customer.getAge() + "\n");
                fw.write(emailText + customer.getEmail() + "\n");
                fw.write(addressText + customer.getAddress() + "\n\n");
            }
            fw.close();
        }
        catch(IOException ioException){
            System.out.println("Unable to open file. Please try again.");
        }
        catch(Exception e){
            System.out.println("An unexpected error has occurred. Please try again.");
        }
    }

    public static void readAllFiles(){
        readAdminFile();
        readCustomerFile();
        readCarFile();
        readBookingFile();
        readRegistrationFile();
    }
}
