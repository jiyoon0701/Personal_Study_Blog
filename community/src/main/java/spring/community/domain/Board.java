package spring.community.domain;

public class Board {

    private int BOARD_ID;
    private Integer USER_ID;
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

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
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
