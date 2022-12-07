import javax.swing.*;

public class CarRentalSystem {

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static SignUpPage signUpPage;
    public static CustomerMenu customerMenu;
    public static AdminMenu adminMenu;
    public static Customer loginCustomer = null;
    public static Admin loginAdmin = null;

    public static JFrame currentFrame = null;

    public static void main (String[] args) {
        FileIO.readAllFiles();
        homePage = new HomePage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();

        currentFrame = homePage.getFrame();
    }
}
