package spring.community.domain;

public class Board {

    private int BOARD_ID;
    private String USER_EMAIL;
    private String TITLE;
    private String CONTENT_MARK;
    private String CONTENT_HTML;

    private String REPRE_IMAGE;

    public String getCONTENT_MARK() {
        return CONTENT_MARK;
    }

    public void setCONTENT_MARK(String CONTENT_MARK) {
        this.CONTENT_MARK = CONTENT_MARK;
    }

    public String getCONTENT_HTML() {
        return CONTENT_HTML;
    }

    public void setCONTENT_HTML(String CONTENT_HTML) {
        this.CONTENT_HTML = CONTENT_HTML;
    }

    public int getBOARD_ID() {
        return BOARD_ID;
    }

    public void setBOARD_ID(int BOARD_ID) {
        this.BOARD_ID = BOARD_ID;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getREPRE_IMAGE() {
        return REPRE_IMAGE;
    }

    public void setREPRE_IMAGE(String REPRE_IMAGE) {
        this.REPRE_IMAGE = REPRE_IMAGE;
    }

}
