import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Admin  extends User{

    public Admin(String username, String password) {
        super(username, password);
    }

    public boolean login(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();

        for (Admin a : FileIO.adminList) {
            if (getUsername().equals(a.getUsername())) {
                if (getPassword().equals(a.getPassword())) {
                    CarRentalSystem.loginAdmin = a;

                    FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login successful.");
                    return true;
                }
                else {
                    FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login failed.");
                    return false;
                }
            }
        }
        FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login failed. Admin not found.");
        return false;
    }

    public static void changePassword(String password, String passwordCheck){
        try {
            if (password.equals("") || passwordCheck.equals("")) throw new EmptyInputException();
            if (!password.equals(passwordCheck)) throw new MismatchPasswordException();

            CarRentalSystem.loginAdmin.setPassword(password);
            FileIO.writeAdminFile();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Your password has been successfully changed!");

        } catch (EmptyInputException e) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (MismatchPasswordException mismatchPasswordException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } finally {
            AccountFunctions.getPassword1().setText("");
            AccountFunctions.getPassword2().setText("");
        }
    }

    @Override
    public boolean signUp(){
        //TODO: addAdmin function move here?
        return false;
    }

    public static void addAdmin(String username){
        try {
            if (username.equals("")) throw new EmptyInputException();

            for (Admin a: FileIO.adminList) {
                if (username.equals(a.getUsername())){
                    throw new UsernameTakenException();
                }
            }

            FileIO.adminList.add(new Admin(username, username));
            FileIO.writeAdminFile();
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "New admin account is successfully created!");

        } catch (EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } finally {
            AccountFunctions.getUsername2().setText("");
        }
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

        } catch (InvalidAgeException e) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Invalid age entered!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            AccountFunctions.getFromAge().setValue(17);
            AccountFunctions.getToAge().setValue(17);
        } catch (InvalidPointException e) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Invalid point entered!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            AccountFunctions.getFromPoint().setValue(0);
            AccountFunctions.getToPoint().setValue(0);
        }

        return searchedCustomerList;
    }

    public static void editAccountDetails(int numberValue, String name, String phone, String email, String address, String gender, int age, int point){
        try {
            if (name.equals("") || phone.equals("") || email.equals("") || address.equals("")){
                throw new EmptyInputException();
            }

            char[] chars = name.toCharArray();
            for (char c : chars) {
                if (!Character.isLetter(c) && !Character.isSpaceChar(c)) throw new InvalidNameException();
            }

            if (!phone.matches("[0-9]+")) throw new InvalidPhoneException();

            Customer customer = FileIO.customerList.get(numberValue - 1);
            customer.setName(name);
            customer.setPhone(phone);
            customer.setGender(gender);
            customer.setAge(age);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setPoints(point);
            FileIO.writeCustomerFile();

        } catch (EmptyInputException emptyInputException) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (InvalidNameException invalidNameException) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Invalid name entered! Your name must only include characters or spaces.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (InvalidPhoneException invalidPhoneException) {
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Invalid phone number entered! Your phone number must only include numbers.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception exception){
            JOptionPane.showMessageDialog(CarRentalSystem.adminMenu.getFrame(), "Something wrong!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
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
