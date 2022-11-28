create table board (
   BOARD_ID int primary key auto_increment,
   USER_EMAIL VARCHAR(20) NOT NULL,
   TITLE VARCHAR(50) NOT NULL,
   CONTENT_HTML longtext,
   CONTENT_MARK longtext
);