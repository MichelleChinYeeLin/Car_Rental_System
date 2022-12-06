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

            GUI.playSound("ji.wav");
            CarRentalSystem.loginAdmin.setPassword(password);
            FileIO.writeAdminFile();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Your password has been successfully changed!");

        } catch (EmptyInputException emptyInputException) {
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (MismatchPasswordException mismatchPasswordException){
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } finally {
            AccountFunctions.getAdminPassword1().setText("");
            AccountFunctions.getAdminPassword2().setText("");
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
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } finally {
            AccountFunctions.getAdminUsername2().setText("");
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
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid age entered!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            AccountFunctions.getAdminFromAge().setValue(17);
            AccountFunctions.getAdminToAge().setValue(17);
        } catch (InvalidPointException invalidPointException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Invalid point entered!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            AccountFunctions.getAdminFromPoint().setValue(0);
            AccountFunctions.getAdminToPoint().setValue(0);
        }

        return searchedCustomerList;
    }

    public static void editAccountDetails(int numberValue, String name, String phone, String email, String address, String gender, int age, int point){

        Customer customer = FileIO.customerList.get(numberValue - 1);
        if (Customer.validateCustomerDetails(true, customer.getUsername(), name, age, customer.getPassword(), customer.getPassword(), phone, email)){
            GUI.playSound("ji.wav");
            customer.setName(name);
            customer.setPhone(phone);
            customer.setGender(gender);
            customer.setAge(age);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setPoints(point);
            FileIO.writeCustomerFile();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "User account has been modified!");
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
        JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Account password successfully reset!");
    }

    public static boolean deleteAccount(int numberValue, String userType){
        boolean flag = false;
        try {
            if (userType.equals("Admin")) {
                if (FileIO.adminList.size() == 1){
                    throw new LastAdminException();
                }

                FileIO.adminList.remove(numberValue - 1);
                FileIO.writeAdminFile();
            }
            else if (userType.equals("Customer")){
                Customer customer = FileIO.customerList.get(numberValue - 1);

                if (customer.getMyBookings().size() > 0){
                    throw new InvalidUserException();
                }

                FileIO.customerList.remove(numberValue - 1);
                FileIO.feedbackList.removeAll(customer.getMyFeedbacks());
                FileIO.writeCustomerFile();
                FileIO.writeFeedbackFile();
            }

            flag = true;

        } catch (LastAdminException lastAdminException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can't delete the last admin account!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (InvalidUserException invalidUserException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "You can't delete customer that have bookings!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }
}
