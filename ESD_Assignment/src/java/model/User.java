package model;

import java.sql.Date;
import java.time.LocalDate;

public class User {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String dateOfCreation;
    private String membershipStatus;
    private float claimBalance;
    private float accountBalance;
    private int claimCount; //This shouldn't be allowed to be over 2

    public User() {
        username = "";
        password = "";
        firstName = "";
        lastName = "";
        dateOfBirth = "";
        dateOfCreation = "0000-00-00";
        membershipStatus = "";
        claimBalance = 0;
        accountBalance = 0;
        claimCount = 0;
    }

    public User(String username, String password, String firstName, String lastName, String dateOfBirth, String dateOfCreation, String membershipStatus, float claimBalance, float accountBalance, int claimCount) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfCreation = dateOfCreation;
        this.membershipStatus = membershipStatus;
        this.claimBalance = claimBalance;
        this.accountBalance = accountBalance;
        this.claimCount = claimCount;
    }

    public void setColumn(int index, Object value) {
        switch (index) {
            case 1:
                username = value.toString();
                break;
            case 2:
                password = value.toString();
                break;
            case 3:
                firstName = value.toString();
                break;
            case 4:
                lastName = value.toString();
                break;
            case 5:
                dateOfBirth = value.toString();
                break;
            case 6:
                dateOfCreation = value.toString();
                break;
            case 7:
                membershipStatus = value.toString();
                break;
            case 8:
                claimBalance = Float.parseFloat(value.toString());
                break;
            case 9:
                accountBalance = Float.parseFloat(value.toString());
                break;
            case 10:
                claimCount = Integer.parseInt(value.toString());
                break;
        }
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfCreation=" + dateOfCreation + ", membershipStatus=" + membershipStatus + ", claimBalance=" + claimBalance + ", accountBalance=" + accountBalance + ", claimCount=" + claimCount + '}';
    }
}
