import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
    }

    public boolean login() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        for (Admin a : FileIO.adminList) {
            if (getUsername().equals(a.getUsername())) {
                if (getPassword().equals(a.getPassword())) {
                    CarRentalSystem.loginAdmin = a;

                    FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login successful.");
                    return true;
                } else {
                    FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login failed.");
                    return false;
                }
            }
        }
        FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login failed. Admin not found.");
        return false;
    }

    @Override
    public void signUp(){
        FileIO.adminList.add(new Admin(getUsername(), getUsername()));
        FileIO.writeAdminFile();
    }

    public static void changePassword(String password, String passwordCheck){
        try {
            if (password.equals("") || passwordCheck.equals("")) throw new EmptyInputException();
            if (!password.equals(passwordCheck)) throw new MismatchPasswordException();

            CarRentalSystem.loginAdmin.setPassword(password);
            FileIO.writeAdminFile();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Your password has been successfully changed!");

        } catch (EmptyInputException e) {
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (MismatchPasswordException mismatchPasswordException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } finally {
            AccountFunctions.getPassword1().setText("");
            AccountFunctions.getPassword2().setText("");
        }
    }

    public static boolean addAdmin(String username){
        boolean flag = false;
        try {
            if (username.equals("")) throw new EmptyInputException();

            for (Admin a: FileIO.adminList) {
                if (username.equals(a.getUsername())){
                    throw new UsernameTakenException();
                }
            }
            flag = true;

        } catch (EmptyInputException emptyInputException){
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } finally {
            AccountFunctions.getUsername2().setText("");
        }
        return flag;
    }

    public static ArrayList<Admin> searchAdmin(String username){
        ArrayList<Admin> searchedAdminList = new ArrayList<>(FileIO.adminList);

        searchedAdminList.removeIf(a -> !a.getUsername().contains(username));

        return searchedAdminList;
    }

    public static ArrayList<Customer> searchCustomer(String username, String name, String phone, String email, String address, String gender, int fromAge, int toAge, int fromPoint, int toPoint){
        ArrayList<Customer> searchedCustomerList = new ArrayList<>(FileIO.customerList);

        try {
            if (fromAge > toAge){
                throw new InvalidAgeException();
            }

            if (fromPoint > toPoint){
                throw new InvalidPointException();
            }

            searchedCustomerList.removeIf(a -> !a.getUsername().contains(username));

            if (!name.equals("")){
                searchedCustomerList.removeIf(c -> !c.getUsername().contains(name));
            }

            if (!phone.equals("")){
                searchedCustomerList.removeIf(c -> !c.getPhone().contains(phone));
            }

            if (!email.equals("")){
                searchedCustomerList.removeIf(c -> !c.getEmail().contains(email));
            }

            if (!address.equals("")){
                searchedCustomerList.removeIf(c -> !c.getAddress().contains(address));
            }

            searchedCustomerList.removeIf(c -> !c.getGender().equals(gender));

            searchedCustomerList.removeIf(c -> !(c.getAge() >= fromAge && c.getAge() <= toAge));

            searchedCustomerList.removeIf(c -> !(c.getPoints() >= fromPoint && c.getPoints() <= toPoint));

        } catch (InvalidAgeException invalidAgeException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Invalid age entered!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            AccountFunctions.getFromAge().setValue(17);
            AccountFunctions.getToAge().setValue(17);
        } catch (InvalidPointException invalidPointException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Invalid point entered!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            AccountFunctions.getFromPoint().setValue(0);
            AccountFunctions.getToPoint().setValue(0);
        }

        return searchedCustomerList;
    }

    public static void editAccountDetails(int numberValue, String name, String phone, String email, String address, String gender, int age, int point){

        Customer customer = FileIO.customerList.get(numberValue - 1);
        if (Customer.validateCustomerDetails(true, customer.getUsername(), name, age, customer.getPassword(), customer.getPassword(), phone, email)){
            customer.setName(name);
            customer.setPhone(phone);
            customer.setGender(gender);
            customer.setAge(age);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setPoints(point);
            FileIO.writeCustomerFile();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "User account has been modified!");
        }
    }

    public static void resetPassword(int numberValue, String userType){
        if (userType.equals("Admin")) {
            Admin admin = FileIO.adminList.get(numberValue - 1);
            admin.setPassword(admin.getUsername());
            FileIO.writeAdminFile();
        }
        else if (userType.equals("Customer")){
            Customer customer = FileIO.customerList.get(numberValue - 1);
            customer.setPassword(customer.getUsername());
            FileIO.writeCustomerFile();
        }
    }

    public static boolean deleteAccount(int numberValue, String userType){
        if (userType.equals("Admin")) {
            if (FileIO.adminList.size() == 1){
                return false;
            }
            FileIO.adminList.remove(numberValue - 1);
            FileIO.writeAdminFile();
        }
        else if (userType.equals("Customer")){
            FileIO.customerList.remove(numberValue - 1);
            FileIO.writeCustomerFile();
        }
        return true;
    }
}
