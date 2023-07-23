<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hdh.wiki.dao.BoardDAO" %>

<%
    ArrayList<BoardDAO> boardlist = (ArrayList<BoardDAO>) request.getAttribute("boardlist");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 글 확인 날짜</title>
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
            border-spacing: 0;
        }

        .listbtArea {
            width: 70%;
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
            cursor: pointer;
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
            width: 70%;
            color: #777;
            height: 80px;
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
        function GoAdminUser() {
            document.location = "userdata";
        }

        function delCon(i) {
            document.location = "conlist?conInfo=" + i;
        }

        function goDetail(i) {
            document.location = "wikiDetail?num=" + i
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
<h2 style="margin: 100px auto;">글 정보 확인<input class="golist" type="button" value="로그아웃" onclick="GoLogout()"/></h2>
<table>
    <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성날짜</th>
        <th>조회수</th>
        <th>삭제</th>
    </tr>
    <%
        for (int i = 0; i < boardlist.size(); i++) {
    %>
    <tr>
        <td onclick="goDetail(<%=boardlist.get(i).getContentsIdx()%>)"><%=boardlist.get(i).getContentsIdx()%>
        </td>
        <td onclick="goDetail(<%=boardlist.get(i).getContentsIdx()%>)"><%=boardlist.get(i).getContentsTitle()%>
        </td>
        <td onclick="goDetail(<%=boardlist.get(i).getContentsIdx()%>)"><%=boardlist.get(i).getWriterId()%>
        </td>
        <td onclick="goDetail(<%=boardlist.get(i).getContentsIdx()%>)"><%=boardlist.get(i).getWriteDate()%>
        </td>
        <td onclick="goDetail(<%=boardlist.get(i).getContentsIdx()%>)"><%=boardlist.get(i).getHit()%>
        </td>
        <td><input class="clickBT" type="button" value="삭제" onclick="delCon(<%=boardlist.get(i).getContentsIdx()%>)"/>
        </td>
    </tr>
    <%}%>
</table>
<div class="listbtArea"><input class="golist" type="button" value="유저 관리로" onclick="GoAdminUser()"/></div>

</body>
</html>