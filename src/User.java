public class User { // 要不要弄成abstract class 反正好像也不会用到

    private String username;
    private String password;

    User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean login(String username, String password){
        return false;
    }

    public void signUp(){

    }
}
