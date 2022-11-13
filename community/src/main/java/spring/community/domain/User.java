package spring.community.domain;

public class User {

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
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

    public Integer getUSER_AGE() {
        return USER_AGE;
    }

    public void setUSER_AGE(Integer USER_AGE) {
        this.USER_AGE = USER_AGE;
    }

    private String USER_ID;
    private String USER_NAME;
    private String USER_PASS;
    private Integer USER_AGE;

}