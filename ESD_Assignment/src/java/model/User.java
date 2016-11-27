package model;

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

    public User(String[] strings) {
        username = strings[0];
        password = strings[1];
        firstName = strings[2];
        lastName = strings[3];
        dateOfBirth = strings[4];
        dateOfCreation = strings[5];
        membershipStatus = strings[6];
        claimBalance = Float.parseFloat(strings[7]);
        accountBalance = Float.parseFloat(strings[8]);
        claimCount = Integer.parseInt(strings[9]);
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

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", dateOfCreation=" + dateOfCreation + ", membershipStatus=" + membershipStatus + ", claimBalance=" + claimBalance + ", accountBalance=" + accountBalance + ", claimCount=" + claimCount + '}';
    }
}
