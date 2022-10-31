public class CarRentalSystem {

    public static HomePage homePage;
    public static LoginPage loginPage;
    public static SignUpPage signUpPage;
    public static CustomerMenu customerMenu;
    public static AdminMenu adminMenu;
    public static Customer loginCustomer = null;
    public static Admin loginAdmin = null; // 不懂有没有更好的方法

    public static void main (String[] args) {
        //TODO: create GUI for login
        //TODO: user login
        //TODO: read text file, check username + password
        FileIO.readAllFiles();
        homePage = new HomePage();
        loginPage = new LoginPage();
        signUpPage = new SignUpPage();
        customerMenu = new CustomerMenu();
        adminMenu = new AdminMenu();

        //ADMIN:
        //TODO: show gui (admin)

        //CUSTOMER:
        //TODO: show gui (customer)
    }
}
