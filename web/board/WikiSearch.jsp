<%@ page import="hdh.wiki.dao.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    ArrayList<BoardDAO> boards = (ArrayList<BoardDAO>) request.getAttribute("boards");
    int size = boards.size();
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WikiSearch</title>
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

        .backArea {
            width: 80%;
            height: 500px;
            margin: auto;
            box-sizing: border-box;
            padding: 10px;
        }

        .serchBox {
            width: 80%;
            height: 50px;
            margin-top: 80px;
            box-sizing: border-box;
            padding: 10px;
            border-radius: 20px;
            font-size: 15px;
        }

        .serchBt {
            width: 80px;
            height: 50px;
            border-radius: 10px;
            font-weight: bold;
            border: 1px solid #1d9abc;
        }

        .serchBt:hover {
            color: #fff;
            border: 1px solid #aaa;
            background-color: #1d9abc;
        }

        ul {
            width: 100%;
            height: 300px;
            margin: 0 auto;
            padding: 0;
            list-style: none;
        }

        .title_best {
            float: none;
            color: #1d9abc;
            font-size: 30px;
            border: none;
            font-weight: bold;
            margin-bottom: 40px;
            background-color: #fff;
        }

        li {
            float: left;
            width: 20%;
            height: 35px;
            color: #fff;
            border-radius: 10px;
            font-size: 20px;
            font-weight: bold;
            margin-right: 10px;
            margin-bottom: 30px;
        }

        li a {
            display: block;
            width: 100%;
            height: 100%;
            white-space: nowrap;
            box-sizing: border-box;
            padding-top: 3px;
            overflow: hidden;
            text-overflow: ellipsis;
            padding-left: 5px;
            font-weight: bold;
            text-decoration: none;
            color: #fff;
            background-color: #1d9abc;
            border-radius: 10px;
            font-weight: bold;
            margin-right: 10px;
        }

        li a:hover {
            color: #1d9abc;
            border: 1px solid #1d9abc;
            background-color: #fff;
        }

        .bottonBT {
            float: none;
            width: 100%;
            height: 35px;
            margin: 0;
            text-align: center;
            color: #1d9abc;
            border-radius: 10px;
            font-size: 25px;
            font-weight: bold;
            border: 1px solid #1d9abc;
        }

        .bottonBT:hover {
            color: #fff;
            border: 1px solid #aaa;
            background-color: #1d9abc;
        }
    </style>
    <script type="text/javascript">
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
<div class="backArea">
    <form action="wikisearch" method="post">
        <input class="serchBox" placeholder="검색하고 싶은 내용을 입력해 주세요" type="text" name="keyword">
        <input class="serchBt" type="submit" value="검색"><br/><br/>
        <input type="hidden" name="search" value="contentsText"><br/><br/>
    </form>

    <ul>
        <li class="title_best">Best3</li>
        <br/>
        <%if (boards.size() == 0) { %>
        <li>등록된 게시물이 없습니다.</li>
        <%} else { %>
        <% for (int i = 0; i < boards.size(); i++) { %>
        <li><a href="wikiDetail?num=<%=boards.get(i).getContentsIdx()%>"><%=i + 1%>
            위. <%=boards.get(i).getContentsTitle()%>
        </a></li>
        <%
                if (i == 2) {
                    break;
                }
            }
        %>
        <% if (boards.size() == 1) { %>
        <li>등록된 게시물이 없습니다.</li>
        <li>등록된 게시물이 없습니다.</li>
        <%} else if (boards.size() == 2) { %>
        <li>등록된 게시물이 없습니다.</li>
        <%}%>

        <li><input class="bottonBT" type="button" value="전체 목록으로" onclick="GoToList()"/></li>
        <%}%>
    </ul>

</div>

</body>
</html>
