import javax.swing.*;

public class Admin  extends User{

    public Admin(String username, String password) {
        super(username, password);
    }

    public static boolean login(String username, String password){
        for (Admin a : FileIO.getAdminList()) {
            if (username.equals(a.getUsername())) {
                if (password.equals(a.getPassword())) {
                    CarRentalSystem.loginAdmin = a;
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}
