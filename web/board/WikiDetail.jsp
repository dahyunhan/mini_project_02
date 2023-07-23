<%@ page import="hdh.wiki.dao.BoardDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    BoardDAO board = (BoardDAO) request.getAttribute("board");
    int idx = board.getContentsIdx();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 확인하기</title>
    <style>
        h1 {
            width: 100%;
            height: 80px;
            font-size: 55px;
            color: #fff;
            margin-top: 0;
            margin-bottom: 70px;
            background-color: #8ebbdc;
        }

        h2 {
            display: block;
            width: 700px;
            height: 80px;
            margin: 0 auto;
            color: #777;
            font-weight: bold;
            font-size: 40px;
        }

        h1:hover {
            cursor: pointer;
        }

        form {
            width: 700px;
            margin-bottom: 30px;
            margin: auto;
        }

        .bottomBT {
            float: right;
            width: 100px;
            height: 36px;
            background-color: #a4f;
        }

        table {
            width: 700px;
            box-sizing: border-box;
            padding: 10px;
            border-radius: 5px;
            margin: auto;
            margin-bottom: 10px;
            box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.1) inset;
            background-color: #8ebbdc;
        }

        th {
            width: 300px;
            height: 40px;
            text-align: left;
            border: 1px solid #ccc;
            background-color: #fff;
        }

        .conInfo {
            width: 30px;
            border: none;
        }

        td {
            width: 300px;
            height: 300px;
            border: 1px solid #ccc;
            background-color: #fff;
        }

        .searchBT {
            float: right;
            width: 80px;
            height: 35px;
            margin-left: 10px;
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

        label {
            float: right;
            font-size: 20px;
            margin-right: 10px;
        }

        textarea {
            width: 99%;
            height: 300px;
            border: 0;
            resize: none;
        }
    </style>
    <script type="text/javascript">
        function GoToList() {
            document.location = "wikiresult";
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
<h2>상세보기<input class="golist" type="button" value="로그아웃" onclick="GoLogout()"/><label><%=session.getAttribute("user") %>
</label></h2>

<div>
    <table>
        <tr>
            <th>제목 : <%=board.getContentsTitle()%>
            </th>
        </tr>
        <tr>
            <th>글쓴이 :<%=board.getWriterId()%>  / 조회수 : <%=board.getHit()%>  / 작성일 : <%=board.getWriteDate()%>
            </th>
        </tr>
        <tr>
            <td><textarea cols="30" rows="5"><%=board.getContentsText()%></textarea></td>
        </tr>
    </table>

    <input class="searchBT" type="button" value="수정하기"
           onclick="location.href='wikiEdit?idx=${board.getContentsIdx()}'"/>

    <input class="searchBT" type="button" value="목록으로" onclick="GoToList()"/>

</div>

</body>
</html>