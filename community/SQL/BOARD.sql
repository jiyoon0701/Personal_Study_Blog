create table board (
    BOARD_ID int primary key auto_increment,
    USER_ID INT NOT NULL,
    TITLE VARCHAR(50) NULL,
    CONTENT_HTML longtext,
    CONTENT_MARK longtext,
    REPRE_IMAGE longtext,
    TAG longtext,
    STATE boolean,
    RATING INT,
    BOARD_DATE DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`) ON DELETE CASCADE
);