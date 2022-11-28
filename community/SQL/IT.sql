CREATE TABLE IT(
  IT_ID INT AUTO_INCREMENT PRIMARY KEY,
  IT_NAME VARCHAR(20)
);

insert into IT(IT_NAME) values("PM/PO");
insert into IT(IT_NAME) values("UX/UI 디자이너");
insert into IT(IT_NAME) values("프론트엔드");
insert into IT(IT_NAME) values("백엔드");
insert into IT(IT_NAME) values("앱");
insert into IT(IT_NAME) values("DevOps 엔지니어");
insert into IT(IT_NAME) values("데이터 사이언스");
insert into IT(IT_NAME) values("데이터 분석가");
insert into IT(IT_NAME) values("데이터 엔지니어");
insert into IT(IT_NAME) values("웹디자이너");

select * from IT;