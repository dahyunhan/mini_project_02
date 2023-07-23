<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 작성하기</title>
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
            document.location = "../wikiresult";
        }

        function GoToMain() {
            document.location = "../wikisearch";
        }
    </script>
</head>
<body>
<h1 onclick="GoToMain()">T.D WIKI</h1>
<h2>새로운 글 쓰기<input class="golist" type="button" value="로그아웃"
                   onclick="GoLogout()"/><label><%=session.getAttribute("user") %>
</label></h2>

<form action="../wikiwrite" method="post">
    <input type="text" name="contentsTitle" placeholder="제목을 입력하세요" required="required"/>
    <input type="hidden" name="writerId" value="<%=session.getAttribute("user") %>"/>
    <textarea cols="30" rows="5" name="contentstext" placeholder="내용을 입력하세요" required="required"></textarea><br/>
    <input class="searchBT" type="submit" value="저장하기"/>
    <input class="searchBT" type="button" value="취소하기" onclick="GoToList()"/>
</form>

</body>
</html>