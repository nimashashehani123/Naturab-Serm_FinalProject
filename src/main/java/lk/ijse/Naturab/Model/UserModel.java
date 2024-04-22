package lk.ijse.Naturab.Model;

public class UserModel {
    private String UserId;
    private String Password;
    private String UserName;

    public UserModel(String UserId, String Password, String UserName) {
        this.UserId = UserId;
        this.Password = Password;
        this.UserName = UserName;

    }
    public UserModel(String UserId, String Password) {
        this.UserId = UserId;
        this.Password = Password;
    }
    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserId() {
        return UserId;
    }
}
