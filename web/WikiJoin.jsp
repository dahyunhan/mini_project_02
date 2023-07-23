<%@ page import="hdh.wiki.dao.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%UserDAO user = (UserDAO) request.getAttribute("user");%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>

    <script type="text/javascript">

        function GoToLogin1() {
            document.location = "WikiLogin.jsp"
        }
    </script>
    <style>
        h1 {
            width: 100%;
            height: 80px;
            font-size: 55px;
            color: #fff;
            margin-top: 0;
            margin-bottom: 100px;
            background-color: #8ebbdc;
        }

        h2 {
            width: 100%;
            height: 80px;
            text-align: center;
            margin-top: 90px;
            font-weight: bold;
            font-size: 40px;
        }

        h1:hover {
            cursor: pointer;
        }

        form {
            width: 20%;
            height: 500px;
            margin: auto;
        }

        input {
            width: 99%;
            height: 38px;
            margin: auto;
            margin-bottom: 10px;
            border-radius: 3px;
        }

        .loginpagebt {
            width: 48%;
            height: 43px;
            font-weight: bold;
            margin-top: 20px;
            font-size: 18px;
            border: none;
            background-color: #1ebbd7;
        }

        .loginpagebt:hover {
            font-weight: bold;
            font-size: 20px;
            background-color: #afcced;
        }

    </style>
</head>
<body>
<h1 onclick="GoToLogin1()">T.D WIKI</h1>
<h2>회원가입</h2>
<form action="wikijoin" method="post">
    <label>아이디 </label><input class="idBox" type="text" name="USERID" placeholder="아이디를 입력하세요" required="required"/>
    <label>비밀번호 </label><input type="password" name="USERPW" placeholder="비밀번호를 입력하세요" required="required"/><br/>
    <label>비밀번호 재확인 </label><input type="password" name="USERPW2" placeholder="비밀번호를 다시 입력하세요"
                                   required="required"/><br/>
    <label>이름 </label><input type="text" name="USERNAME" placeholder="이름을 입력하세요" required="required"/><br/>
    <input class="loginpagebt" type="button" value="돌아가기" onclick="GoToLogin1()"/>
    <input class="loginpagebt" type="submit" value="아이디 생성하기"/><br/>

</form>
</body>

</html>