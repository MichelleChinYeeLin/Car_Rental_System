public class CarRentalSystem {

    public static HomePage homePage;
    public static loginPage loginPage;
    public static signUpPage signUpPage;
    public static Customer loginCustomer = null;
    public static Admin loginAdmin = null; // 不懂有没有更好的方法

    public static void main (String[] args) {
        //TODO: create GUI for login
        //TODO: user login
        //TODO: read text file, check username + password
        homePage = new HomePage();
        loginPage = new loginPage();
        signUpPage = new signUpPage();

        //ADMIN:
        //TODO:

        //CUSTOMER:
        //TODO:
    }
}
