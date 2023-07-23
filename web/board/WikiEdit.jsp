<%@ page import="hdh.wiki.dao.BoardDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    BoardDAO board = (BoardDAO) request.getAttribute("board");
    String title = board.getContentsTitle();
    String text = board.getContentsText();
    int idx = board.getContentsIdx();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 수정하기</title>
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

        h1:hover {
            cursor: pointer;
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

        form {
            width: 700px;
            box-sizing: border-box;
            padding: 10px;
            margin: 0 auto;
            border-radius: 5px;
            margin-bottom: 10px;
            box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.1) inset;
            background-color: #8ebbdc;
        }

        .bottomBT {
            float: right;
            width: 100px;
            height: 36px;
            background-color: #a4f;
        }

        .conInfo {
            width: 30px;
            border: none;
        }

        .searchBT {
            float: right;
            width: 80px;
            height: 35px;
            margin-top: 20px;
            margin-left: 10px;
            border-radius: 5px;
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
            resize: none;
        }

        input {
            width: 98.8%;
            height: 40px;
        }
    </style>
    <script type="text/javascript">
        function GoToList() {
            alert("저장되었습니다.");
        }

        function BackList(idx) {
            alert("저장되지 않습니다. 이전으로 돌아가시겠습니까?");
            document.location = "./wikiDetail?num=" + idx;
        }

        function GoLogout() {
            document.location = "WikiLogout.jsp";
        }

        function GoToList() {
            document.location = "wikiresult";
        }

        function GoToMain() {
            document.location = "wikisearch";
        }
    </script>
</head>
<body>
<h1 onclick="GoToMain()">T.D WIKI</h1>
<h2>수정하기<input class="golist" type="button" value="로그아웃" onclick="GoLogout()"/><label><%=session.getAttribute("user") %>
</label></h2>
<form action="wikiEdit" method="post">
    <input type="text" name="title" value="<%=title%>" required="required"/>

    <textarea cols="30" rows="5" name="text" required="required"><%=text%> </textarea><br/>
    <input type="hidden" name="idx" value="<%=idx%>">
    <input type="hidden" name="flag" value="already">

    <input class="searchBT" type="submit" value="저장하기" onclick="GoToList(<%=idx %>)"/>
    <input class="searchBT" type="button" value="취소하기" onclick="BackList(<%=idx %>)"/>
</form>
</body>
</html>