# 나만의 개인 블로그

## 🍀 소개
<aside>

블로그에 별점(중요도)과 태그를 작성하여 별점이 높은 블로그들은 한 눈에 찾아볼 수 있으며, 태그 검색을 통해 블로그를 검색할 수 있습니다.

또한, 시험 기간에 작성했던 블로그를 PDF로 다운로드 받게 되면 광고나 브라우저 전체가 PDF로 다운로드 되는 것에 불편함을 느껴 작성한 글로만 PDF로 다운로드 받을 수 있도록 구현하였습니다.
</aside>

<br>

## 📌 시스템 아키텍처

![1](https://user-images.githubusercontent.com/83527046/218429124-2f431f79-7b8c-43a2-841c-8787df1474fa.png)

<br>

## 🔍 화면 소개

**SignUp page**
<br>

<img src="https://user-images.githubusercontent.com/83527046/218411794-246acfaf-f9c6-41f1-8db4-bf4cd093aaab.png">

- 회원가입 시 관심분야를 다중 선택

<br>

**Main Page**
<br>

<img src="https://user-images.githubusercontent.com/83527046/218412289-967b5206-789a-4666-8a3e-15122998a01e.png">

- 회원가입 시 선택했던 관심 분야들이 하나씩 Change 되면서 표시
<br>

**Blog writing page**
<br>

![localhost_8081_board_write (3)](https://user-images.githubusercontent.com/83527046/218428360-67d59d82-699b-43fc-b409-50b50ee05949.png)

- 블로그 작성 (Markdown)
<br>

**Blog page**
<br>

<img src="https://user-images.githubusercontent.com/83527046/218428674-930525c3-bb8c-4542-b670-f764873adca2.png">

- 블로그 내용 작성 화면
<br>

**Blog Content page**
<br>

<img src="https://user-images.githubusercontent.com/83527046/218428776-249b0396-c8dc-42e9-9587-4e03620698c2.png">

- 작성한 블로그 내용 보기
<br>

**PDF 다운**
<img src="https://user-images.githubusercontent.com/83527046/218428873-70e096b4-a171-45e5-9839-35cc59a36290.png">

- PDF 다운 시 불필요한 요소들이 없애고 내가 적은 내용만 다운로드 가능
<br>

## 💻 담당한 기능

- DB설계 및 API 구현
- Session을 통한 로그인 방식 구현
- fragments를 활용해 중복되는 코드를 최소화
- 자동 매핑 기능을 위해 ORM인 Mybatis를 사용하여 DB에 접근
- ToastUI Open Source 사용

<br>

## 💡 깨달은 점

- Thymeleaf에서 공통 영역 처리하는 방법 → th:fragment
- AJAX에서 배열을 넘기기 위해 tradination:true라는 옵션이 필요
