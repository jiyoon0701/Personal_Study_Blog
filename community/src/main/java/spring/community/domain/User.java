package spring.community.domain;

public class User {
    private int USER_ID;
    private String USER_EMAIL;
    private String USER_NAME;
    private String USER_PASS;
    private int USER_AGE;
    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_PASS() {
        return USER_PASS;
    }

    public void setUSER_PASS(String USER_PASS) {
        this.USER_PASS = USER_PASS;
    }

    public int getUSER_AGE() {
        return USER_AGE;
    }

    public void setUSER_AGE(int USER_AGE) {
        this.USER_AGE = USER_AGE;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

}