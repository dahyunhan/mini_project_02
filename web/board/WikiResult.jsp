<%@ page import="hdh.wiki.dao.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    ArrayList<BoardDAO> boards = (ArrayList<BoardDAO>) request.getAttribute("boards");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>검색된 결과창</title>
    <style>
        * {
            color: #333;
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
            margin-bottom: 40px;
            background-color: #8ebbdc;
        }

        h2 {
            display: block;
            width: 100%;
            height: 80px;
            margin-top: 0;
            color: #777;
            font-weight: bold;
            font-size: 40px;
        }

        h1:hover {
            cursor: pointer;
        }

        form {
            float: left;
            width: 99%;
            margin-bottom: 30px;
        }

        label {
            float: right;
            font-size: 20px;
            margin-right: 10px;
        }

        input {
            width: 70%;
            height: 35px;
            border-radius: 8px;
            box-sizing: border-box;
            paddin: 3px;
        }

        table {
            width: 100%;
            margin: auto;
            magin-top: 100px;
            border-spacing: 0
        }

        select {
            width: 50px;
            height: 35px;
        }

        .writeBT {
            float: right;
            width: 70px;
            height: 35px;
            margin-top: 20px;
            margin-bottom: 20px;
            border: 1px solid #9fddea;
        }

        .writeBT:hover {
            font-weight: bold;
            background-color: #9fddea;
        }

        .searchBT {
            width: 60px;
            height: 35px;
            border: 1px solid #9fddea;
        }

        .searchBT:hover {
            font-weight: bold;
            background-color: #9fddea;
        }

        .golist {
            float: right;
            width: 70px;
            height: 30px;
            border: 1px solid #9fddea;
            border-radius: 3px;
        }

        .golist:hover {
            font-weight: bold;
            background-color: #9fddea;
        }
    </style>
    <script type="text/javascript">

        function GoLogout() {
            document.location = "WikiLogout.jsp";
        }

        function goDetail(i) {
            document.location = "wikiDetail?num=" + i
        }

        function GoToList() {
            document.location = "wikiresult";
        }

        function GoToWrite() {
            document.location = "./board/WikiWrite.jsp";
        }

        function GoToMain() {
            document.location = "wikisearch";
        }
    </script>
</head>
<body>
<h1 onclick="GoToMain()">T.D WIKI</h1>
<h2>검색된 결과<input class="golist" type="button" value="로그아웃"
                 onclick="GoLogout()"/><label><%=session.getAttribute("user") %>
</label></h2>
<form name="searchForm" action="wikiresult" method="post">
    <p style="width:100%">
        <select name="search">
            <option value="contentsTitle">제목</option>
            <option value="writerId">작성자</option>
            <option value="contentsText">내용</option>
        </select>
        <input type="text" name="keyword" placeholder="검색어를 입력하세요"/>
        <input class="searchBT" type="submit" value="검색"/>
    </p>
</form>
<table border="1">
    <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>날짜</th>
        <th>조회수</th>
    </tr>
    <%if (boards.size() == 0) { %>
    <tr>
        <td align="center" colspan="5">등록된 게시물이 없습니다.</td>
    </tr>
    <%} else {%>
    <%for (BoardDAO board : boards) {%>
    <tr onclick="goDetail(<%=board.getContentsIdx()%>)">
        <td align="center"><%=board.getContentsIdx()%>
        </td>
        <td><%=board.getContentsTitle()%>
        </td>
        <td align="center"><%=board.getWriterId()%>
        </td>
        <td align="center"><%=board.getWriteDate()%>
        </td>
        <td align="center"><%=board.getHit()%>
        </td>
    </tr>
    <%}%>
    <%}%>
</table>
<input class="writeBT" type="button" value="글 쓰기" onclick="GoToWrite()"/>
</body>
</html>