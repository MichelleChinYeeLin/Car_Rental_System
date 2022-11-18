import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends User{

    private String name;
    private String phone;
    private String gender;
    private int age;
    private String email;
    private String address;
    private int points;
    public static final String phonePattern = "\\d{10,11}"; // 10 - 11 numbers
    public static final String emailPattern = "\\w{6,30}@gmail.com";  // at least 6 words and only one "@gmail.com"

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

    public Customer(String username, String password){
        super(username, password);
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

    public static boolean validateCustomerDetails(boolean isExist, String username, String name, int age, String password, String passwordCheck, String phone, String email){
        boolean flag = false;
        try {
            // Username
            if (!isExist){
                if(username.equals("")) throw new EmptyInputException();
                ArrayList<Customer> customerList = FileIO.getCustomerList();
                ArrayList<Customer> newAccList = FileIO.getRegistrationList();

                for(Customer customer: customerList){
                    if(customer.getUsername().equals(username)){
                        throw new UsernameTakenException();
                    }
                }

                for(Customer newAcc: newAccList){
                    if(newAcc.getUsername().equals(username)){
                        throw new UsernameTakenException();
                    }
                }

                //Password
                if (password.equals("") || passwordCheck.equals("")) throw new EmptyInputException();
                if (!password.equals(passwordCheck)) throw new MismatchPasswordException();
            }

            // Name
            if(name.equals("")) throw new EmptyInputException();

            else{
                char[] chars = name.toCharArray();
                for (char c : chars) {
                    if (!Character.isLetter(c) && !Character.isSpaceChar(c)) throw new InvalidNameException();
                }
            }

            // Age
            if (age < 17) throw new InvalidAgeException();

            // Phone Number
            Pattern phonePattern = Pattern.compile(Customer.phonePattern);
            Matcher phoneMatcher = phonePattern.matcher(phone);
            if (!phoneMatcher.matches()) throw new InvalidPhoneException();

            // Email
            Pattern emailPattern = Pattern.compile(Customer.emailPattern);
            Matcher emailMatcher = emailPattern.matcher(email);
            if (!emailMatcher.matches()) throw new InvalidEmailException();

            flag = true;
        }
        catch(EmptyInputException emptyInputException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        catch(UsernameTakenException usernameTakenException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getUsername().setText("");
        }
        catch(InvalidNameException invalidNameException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid name entered! Your name must only include characters or spaces.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getName().setText("");
        }
        catch(InvalidAgeException invalidAgeException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid age entered! You must be 17 or above to register.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getAge().setValue(17);
        }
        catch(MismatchPasswordException mismatchPasswordException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getPassword().setText("");
            SignUpPage.getPasswordCheck().setText("");
        }
        catch(InvalidPhoneException invalidPhoneException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid phone number entered! Your phone number must only include 10 to 11 numbers.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getPhoneNum().setText("");
        }
        catch(InvalidEmailException invalidEmailException){
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid email entered! Your email must only include 6 and 30\ncharacters(a-z), numbers(0-9), underscore(_) and end with @gmail.com", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getEmail().setText("");
        }
        return flag;
    }

    public boolean login(){
        try {
            for (Customer c : FileIO.customerList) {
                if (getUsername().equals(c.getUsername())){
                    if (getPassword().equals(c.getPassword())){
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
        } catch (UserNotFoundException userNotFoundException) {
            JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "User \""+ getUsername() +"\"not found!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public void signUp() {
        FileIO.registrationList.add(new Customer(getUsername(), getPassword(), getName(), getPhone(), getGender(), getAge(), getEmail(), getAddress(), 0));
        FileIO.writeRegistrationFile();
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
