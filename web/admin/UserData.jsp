<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="hdh.wiki.dao.UserDAO" %>

<%
    ArrayList<UserDAO> userlist = (ArrayList<UserDAO>) request.getAttribute("userlist");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>유저데이터 관리자페이지</title>
    <style>
        * {
            color: #333;
        }

        body {
            width: 100%;
        }

        table {
            width: 70%;
            margin: auto;
            magin-top: 100px;
            border-spacing: 0
        }

        .listbtArea {
            width: 70%;
            height: 60px;
            margin: auto;
        }

        th {
            height: 30px;
            border: 1px solid #333;
            border-right: 1px solid #eee;
            border-left: 1px solid #eee;
            text-align: center;
            background-color: #1ebbd7;
        }

        td {
            height: 26px;
            box-sizing: border-box;
            padding: 3px;
            border: 1px solid #aaa;
            border-right: 1px solid #eee;
            border-left: 1px solid #eee;
            text-align: center;
        }

        tr:hover {
            background-color: #eee;
        }

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
            display: block;
            width: 70%;
            height: 80px;
            color: #777;
            font-weight: bold;
            font-size: 55px;
        }

        h1:hover {
            cursor: pointer;
        }

        .clickBT {
            width: 60%;
            height: 100%;
            margin: auto;
            border-radius: 3px;
            border: 1px solid #1ebbd7;
        }

        .clickBT:hover {
            font-weight: bold;
            background-color: #1ebbd7;
        }

        .golist {
            float: right;
            width: 10%;
            height: 30px;
            margin-top: 30px;
            border: 1px solid #9fddea;
            border-radius: 3px;
        }

        .golist:hover {
            font-weight: bold;
            background-color: #9fddea;
        }
    </style>
    <script type="text/javascript">
        function GoAdminList() {
            document.location = "conlist";
        }

        function acceptId(n) {
            document.location = "userdata?userIdx=" + n;
        }

        function delUser(i) {
            document.location = "userdata?userInfo=" + i;
        }

        function GoLogout() {
            document.location = "WikiLogout.jsp";
        }

        function GoToMain() {
            document.location = "wikisearch";
        }
    </script>
</head>
<body>
<h1 onclick="GoToMain()">T.D WIKI</h1>
<h2 style="margin: 100px auto;">유저 정보 확인 <input class="golist" type="button" value="로그아웃" onclick="GoLogout()"/></h2>
<table>
    <tr>
        <th>idx</th>
        <th>ID</th>
        <th>비번</th>
        <th>이름</th>
        <th>승인 여부</th>
        <th>삭제</th>
    </tr>
    <%
        for (int i = 0; i < userlist.size(); i++) {
    %>
    <tr>
        <td><%=userlist.get(i).getUserIdx()%>
        </td>
        <td><%=userlist.get(i).getUserId()%>
        </td>
        <td><%=userlist.get(i).getUserPw()%>
        </td>
        <td><%=userlist.get(i).getUserName()%>
        </td>
        <td><%if (userlist.get(i).isUserPass() == false) {%>

            <form action="userdata">
                <input class="clickBT" type="button" value="승인" onclick="acceptId(<%=userlist.get(i).getUserIdx()%>)"/>
                <%} else {%>
                <label>승인 완료</label>
                <%}%>
            </form>
        </td>

        <td><% if (i != 0) {%>
            <input class="clickBT" type="button" value="삭제" onclick="delUser(<%=userlist.get(i).getUserIdx()%>)"/>
            <%} else {%>
            <label>삭제 불가능</label>
            <%}%></td>
    </tr>
    <%
        }
    %>
</table>
<div class="listbtArea"><input class="golist" type="button" value="글 관리로" onclick="GoAdminList()"/></div>
</body>
</html>