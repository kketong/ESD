package model;

public class User {

    private String userID;
    private String userPassword;
    private String userStatus;

    public User() {
        userID = "";
        userPassword = "";
        userStatus = "";
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userPassword=" + userPassword + ", userStatus=" + userStatus + '}';
    }
    
}
