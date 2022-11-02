import javax.swing.*;
import java.util.ArrayList;

public class Customer extends User{

    private String name;
    private String phone;
    private String gender;
    private int age;
    private String email;
    private String address;
//    private ArrayList<Booking> myBookings = new ArrayList<>();

    public Customer(){
        super("", "");
        this.name = "";
        this.phone = "";
        this.gender = "";
        this.email = "";
        this.address = "";
    }

    public Customer(String username, String password, String name, String phone, String gender, int age, String email, String address){
        super(username, password);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender(){return gender;}

    public void setGender(String gender){this.gender = gender;}

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public ArrayList<Booking> getMyBookings() {
//        return myBookings;
//    }
//
//    public void setMyBookings(ArrayList<Booking> myBookings) {
//        this.myBookings = myBookings;
//    }

    public static boolean login(String username, String password){
        for (Customer c : FileIO.getCustomerList()) {
            if (username.equals(c.getUsername())){
                if (password.equals(c.getPassword())){
                    CarRentalSystem.loginCustomer = c;
                    return true;
                }
                else {
                    if (password.length() > 0)
                        JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "Wrong password!");
                    else
                        JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "Please enter your password!");
                }
            }
            else {
                if (username.length() > 0)
                    JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "User \"" + username + "\" not found.");
                else
                    JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "Please enter your username!");
            }
        }
        return false;
    }

    public static boolean signUp(String username, String password, String name, int age, String gender, String phone, String email, String address) {

        ArrayList<Customer> customerList = FileIO.getCustomerList();
        ArrayList<Customer> newAccList = FileIO.getRegistrationList();

        for(Customer customer: customerList){
            if(customer.getUsername().equals(username)){
                return false;
            }
        }

        for(Customer newAcc: newAccList){
            if(newAcc.getUsername().equals(username)){
                return false;
            }
        }

        newAccList.add(new Customer(username, password, name, phone, gender, age, email, address));
        FileIO.setRegistrationList(newAccList);
        FileIO.writeRegistrationFile();
        return true;
    }
}
