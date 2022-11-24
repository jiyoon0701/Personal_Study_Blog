package spring.community.domain;

public class User_IT {
    private int USER_ID;
    private int IT_ID;
    public User_IT(int user_id, int it) {
        this.USER_ID = user_id;
        this.IT_ID = it;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getIT_ID() {
        return IT_ID;
    }

    public void setIT_ID(int IT_ID) {
        this.IT_ID = IT_ID;
    }
}
