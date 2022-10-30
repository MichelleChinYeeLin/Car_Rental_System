import java.util.ArrayList;

public class CarRentalSystem {

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static SignUpPage signUpPage;
    public static MainMenu mainMenu;
    public static Customer loginCustomer = null;
    public static Admin loginAdmin = null; // 不懂有没有更好的方法

    public static void main (String[] args) {
        //TODO: create GUI for login
        //TODO: user login
        //TODO: read text file, check username + password
        homePage = new HomePage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        mainMenu = new MainMenu();

        //ADMIN:
        //TODO: show gui (admin)

        //CUSTOMER:
        //TODO: show gui (customer)
    }
}
