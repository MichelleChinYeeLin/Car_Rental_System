import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

public class Customer extends User {

    private String name;
    private String phone;
    private String gender;
    private int age;
    private String email;
    private String address;
    private int points;
    private static int chance;
    public static final String phonePattern = "\\d{10,11}"; // 10 - 11 numbers
    public static final String emailPattern = "\\w{6,30}@gmail.com";  // at least 6 words and only one "@gmail.com"
    private ArrayList<Booking> myBookings = new ArrayList<>();

    public Customer() {
        super("", "");
        this.name = "";
        this.phone = "";
        this.gender = "";
        this.email = "";
        this.address = "";
        this.points = 0;
        chance = 2;
    }

    public Customer(String username, String password) {
        super(username, password);
        this.name = "";
        this.phone = "";
        this.gender = "";
        this.email = "";
        this.address = "";
        this.points = 0;
        chance = 2;
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
        chance = 2;
    }

    public Customer(String username, String password, String name, String phone, String gender, int age, String email, String address, int points) {
        super(username, password);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.address = address;
        this.points = points;
        chance = 2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<Booking> getMyBookings() {
        return myBookings;
    }

    public void setMyBookings(ArrayList<Booking> myBookings) {
        this.myBookings = myBookings;
    }

    public int getMemberLevel() {
        int memberLevel;

        if (points > 500) memberLevel = 3;
        else if (points > 200) memberLevel = 2;
        else if (points < -500) memberLevel = 0;
        else if (points < 0) memberLevel = -1;
        else memberLevel = 1;

        return memberLevel;
    }

    public static boolean validateCustomerDetails(boolean isExist, String username, String name, int age, String password, String passwordCheck, String phone, String email) {
        boolean flag = false;
        try {
            // Username
            if (!isExist) {
                if (username.equals("")) throw new EmptyInputException();
                ArrayList<Customer> customerList = FileIO.getCustomerList();
                ArrayList<Customer> newAccList = FileIO.getRegistrationList();

                for (Customer customer : customerList) {
                    if (customer.getUsername().equals(username)) {
                        throw new UsernameTakenException();
                    }
                }

                for (Customer newAcc : newAccList) {
                    if (newAcc.getUsername().equals(username)) {
                        throw new UsernameTakenException();
                    }
                }

                //Password
                if (password.equals("") || passwordCheck.equals("")) throw new EmptyInputException();
                if (!password.equals(passwordCheck)) throw new MismatchPasswordException();
            }

            // Name
            if (name.equals("")) throw new EmptyInputException();

            else {
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
        } catch (EmptyInputException emptyInputException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (UsernameTakenException usernameTakenException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Username is already taken! Please input a different username.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getUsername().setText("");
        } catch (InvalidNameException invalidNameException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid name entered! Your name must only include characters or spaces.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getName().setText("");
        } catch (InvalidAgeException invalidAgeException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid age entered! You must be 17 or above to register.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getAge().setValue(17);
        } catch (MismatchPasswordException mismatchPasswordException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getPassword().setText("");
            SignUpPage.getPasswordCheck().setText("");
        } catch (InvalidPhoneException invalidPhoneException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid phone number entered! Your phone number must only include 10 to 11 numbers.", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getPhoneNum().setText("");
        } catch (InvalidEmailException invalidEmailException) {
            JOptionPane.showMessageDialog(CarRentalSystem.signUpPage.getFrame(), "Invalid email entered! Your email must only include 6 and 30\ncharacters(a-z), numbers(0-9), underscore(_) and end with @gmail.com", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            SignUpPage.getEmail().setText("");
        } finally {
            if (!flag) GUI.playSound("NormalVoice.wav");
        }
        return flag;
    }

    public boolean login() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();

            for (Customer c : FileIO.customerList) {
                if (getUsername().equals(c.getUsername())) {
                    if (getPassword().equals(c.getPassword())) {
                        CarRentalSystem.loginCustomer = c;

                        FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login successful.");
                        return true;
                    } else {
                        FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login failed.");
                        throw new WrongPasswordException();
                    }
                }
            }

            FileIO.recordList.add(0, dateFormat.format(date) + " " + getUsername() + " login failed. Customer not found.");
            throw new UserNotFoundException();
        } catch (WrongPasswordException wrongPasswordException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "Wrong Password!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (UserNotFoundException userNotFoundException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.loginPage.getFrame(), "User \"" + getUsername() + "\"not found!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    @Override
    public void signUp() {
        FileIO.registrationList.add(new Customer(getUsername(), getPassword(), getName(), getPhone(), getGender(), getAge(), getEmail(), getAddress(), 0));
        FileIO.writeRegistrationFile();
    }

    public static boolean approveRegistration(int index) {
        try {
            ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();

            if (index >= 0) {
                Customer newCustomer = accRegistrationList.get(index);
                accRegistrationList.remove(newCustomer);
                FileIO.setRegistrationList(accRegistrationList);

                ArrayList<Customer> customerList = FileIO.getCustomerList();
                customerList.add(newCustomer);
                FileIO.setCustomerList(customerList);

                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean denyRegistration(int index) {
        try {
            ArrayList<Customer> accRegistrationList = FileIO.getRegistrationList();

            if (index >= 0) {
                Customer deletedRegistration = accRegistrationList.get(index);
                accRegistrationList.remove(deletedRegistration);
                FileIO.setRegistrationList(accRegistrationList);

                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            return false;
        }
    }

    public static boolean checkIdentity(String message) {
        boolean flag = false;
        try {
            String input = JOptionPane.showInputDialog("Enter your password to check your identity!");
            if (input == null) {
                throw new EmptyInputException();
            }
            else if (input.equals(CarRentalSystem.loginCustomer.getPassword())) {
                JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, message);
                if (chance < 2) chance = 2;
            }
            else {
                throw new WrongPasswordException();
            }

            flag = true;
        } catch (EmptyInputException emptyInputException) {
            GUI.playSound("ElectricVoice.wav");
        } catch (WrongPasswordException wrongPasswordException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Wrong Password!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
            chance--;
        } finally {
            if (chance == 0) {
                JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "We are sorry. Please contact admin to reset your password!", "Identity Verification Failed!", JOptionPane.WARNING_MESSAGE);
                CarRentalSystem.loginCustomer = null;
                CarRentalSystem.customerMenu.getFrame().setVisible(false);
                CarRentalSystem.homePage.getFrame().setVisible(true);
            }
        }
        return flag;
    }

    public static boolean changePassword(String password) {
        boolean flag = false;
        try {
            if (password.equals("")) throw new EmptyInputException();

            String passwordCheck = JOptionPane.showInputDialog("Confirm your password again:");
            if (passwordCheck != null && passwordCheck.equals(password)) {
                CarRentalSystem.loginCustomer.setPassword(password);
                FileIO.writeCustomerFile();
                JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Your password has been successfully changed!");
                flag = true;
            } else {
                throw new MismatchPasswordException();
            }

        } catch (EmptyInputException emptyInputException) {
            GUI.playSound("ElectricVoice.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "All fields require an input!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        } catch (MismatchPasswordException mismatchPasswordException) {
            GUI.playSound("ReflectYourself.wav");
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Your password does not match!", "Invalid input!", JOptionPane.WARNING_MESSAGE);
        }
        return flag;
    }

    public static boolean editAccountDetails(String name, String phone, String email, String address, String gender, int age) {
        boolean flag = false;
        if (Customer.validateCustomerDetails(true, CarRentalSystem.loginCustomer.getUsername(), name, age, CarRentalSystem.loginCustomer.getPassword(), CarRentalSystem.loginCustomer.getPassword(), phone, email)) {
            flag = true;
            CarRentalSystem.loginCustomer.setName(name);
            CarRentalSystem.loginCustomer.setPhone(phone);
            CarRentalSystem.loginCustomer.setGender(gender);
            CarRentalSystem.loginCustomer.setAge(age);
            CarRentalSystem.loginCustomer.setEmail(email);
            CarRentalSystem.loginCustomer.setAddress(address);
            FileIO.writeCustomerFile();
            JOptionPane.showMessageDialog(CarRentalSystem.currentFrame, "Your account has been modified!");
        }
        return flag;
    }

    public static void deleteAccount() {
        FileIO.customerList.remove(CarRentalSystem.loginCustomer);
        FileIO.writeCustomerFile();
    }

}
