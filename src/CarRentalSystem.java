import java.util.ArrayList;

public class CarRentalSystem {

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static SignUpPage signUpPage;
    public static CustomerMenu customerMenu;
    public static AdminMenu adminMenu;
    public static Customer loginCustomer = null;
    public static Admin loginAdmin = null; // 不懂有没有更好的方法

    public static void main (String[] args) {
        homePage = new HomePage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        customerMenu = new CustomerMenu();
        adminMenu = new AdminMenu();
    }
}
