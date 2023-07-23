<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
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

        h1:hover {
            cursor: pointer;
        }

        h2 {
            width: 100%;
            magin: auto;
            height: 80px;
            text-align: center;
            margin-top: 150px;
            font-weight: bold;
            font-size: 55px;
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
            width: 101%;
            height: 43px;
            font-weight: bold;
            font-size: 18px;
            border: none;
            background-color: #1ebbd7;
        }

        .loginpagebt:hover {
            font-weight: bold;
            font-size: 20px;
            background-color: #8ebbdc;
        }
    </style>
    <script type="text/javascript">
        function WikiJoin() {
            document.location = "WikiJoin.jsp";
        }

        function goLogin() {
            document.location = "WikiLogin.jsp";
        }
    </script>
</head>
<body>
<h1 onclick="goLogin()">T.D WIKI</h1>
<h2>로그인</h2>
<form action="wikilogin" method="post">
    <input type="text" name="userId" placeholder="아이디를 입력하세요" required="required"/><br/>
    <input type="password" name="userPw" placeholder="비밀번호를 입력하세요" required="required"/><br/>
    <input class="loginpagebt" type="submit" value="로그인"/><br/>
    <input class="loginpagebt" type="button" value="회원가입" onclick="WikiJoin()"/>

</form>
</body>
</html>