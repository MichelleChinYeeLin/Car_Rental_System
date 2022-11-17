import javax.swing.*;
import java.util.ArrayList;

public class Customer extends User{

    private String name;
    private String phone;
    private String gender;
    private int age;
    private String email;
    private String address;
    private int points;

//    private ArrayList<Booking> myBookings = new ArrayList<>();

    public Customer(){
        super("", "");
        this.name = "";
        this.phone = "";
        this.gender = "";
        this.email = "";
        this.address = "";
        this.points = 0;
    }

    public Customer(String username, String password, String name, String phone, String gender, int age, String email, String address) {
        super(username, password);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.address = address;
        this.points = 0;
    }

    public Customer(String username, String password, String name, String phone, String gender, int age, String email, String address, int points){
        super(username, password);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.address = address;
        this.points = points;
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

    public void setAddress(String address) { this.address = address; }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points;}

//    public ArrayList<Booking> getMyBookings() {
//        return myBookings;
//    }
//
//    public void setMyBookings(ArrayList<Booking> myBookings) {
//        this.myBookings = myBookings;
//    }

    public static boolean login(String username, String password){
        try {
            for (Customer c : FileIO.customerList) {
                if (username.equals(c.getUsername())){
                    if (password.equals(c.getPassword())){
                        CarRentalSystem.loginCustomer = c;
                        return true;
                    }
                    else {
                        throw new WrongPasswordException();
                    }
                }
                else {
                    throw new UserNotFoundException();
                }
            }
        } catch (WrongPasswordException wrongPasswordException) {
            JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "Wrong Password!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (UserNotFoundException e) {
            JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "User \""+username+"\"not found!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
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

        int newPoints = 0;
        newAccList.add(new Customer(username, password, name, phone, gender, age, email, address, newPoints));
        FileIO.setRegistrationList(newAccList);
        return true;
    }

    public static boolean approveRegistration(int index){
        try{
            ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();

            if(index >= 0){
                Customer newCustomer = accRegistrationList.get(index);
                accRegistrationList.remove(newCustomer);
                FileIO.setRegistrationList(accRegistrationList);

                ArrayList<Customer> customerList = FileIO.getCustomerList();
                customerList.add(newCustomer);
                FileIO.setCustomerList(customerList);

                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception exception){
            return false;
        }
    }

    public static boolean denyRegistration(int index){
        try{
            ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();

            if(index >= 0){
                Customer deletedRegistration = accRegistrationList.get(index);
                accRegistrationList.remove(deletedRegistration);
                FileIO.setRegistrationList(accRegistrationList);

                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception exception){
            return false;
        }
    }
}
