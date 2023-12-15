<%@ page import="org.primshic.stepan.entity.Matches" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Matches page
    </title>
    <style>
        <%@include file="/WEB-INF/views/assets/matches.css" %>
        <%@include file="/WEB-INF/views/assets/general.css" %>
    </style>
    <%
        List<Matches> pageList = (List<Matches>) request.getAttribute("pageList");
    %>
</head>
<body>
<div class="main-block">
    <div class="title">Finished matches</div>
    <div class="search-by-name-box">
        <p class="name">Name: </p>
        <input class="search-bar" placeholder="Steve">
        <button class="search-button">Search</button>
        <button class="clear-button">Clear</button>
    </div>
    <div class="table">
        <div class="id-column">
            <div class="table-header">ID</div>
            <div class="id">
                <%if (pageList.size() >= 1) {%>
                <%=pageList.get(0).getId()%>
                <%}%>
            </div>
            <div class="id">
                <%if (pageList.size() >= 2) {%>
                <%=pageList.get(1).getId()%>
                <%}%>
            </div>
            <div class="id"><%if (pageList.size() >= 3) {%>
                <%=pageList.get(2).getId()%>
                <%}%>
            </div>
            <div class="id"><%if (pageList.size() >= 4) {%>
                <%=pageList.get(3).getId()%>
                <%}%>
            </div>
            <div class="id"><%if (pageList.size() >= 5) {%>
                <%=pageList.get(4).getId()%>
                <%}%>
            </div>
        </div>
        <div class="player1-column">
            <div class="table-header">Player 1</div>
            <div class="player"><%if (pageList.size() >= 1) {%>
                <%=pageList.get(0).getPlayers1().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 2) {%>
                <%=pageList.get(1).getPlayers1().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 3) {%>
                <%=pageList.get(2).getPlayers1().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 4) {%>
                <%=pageList.get(3).getPlayers1().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 5) {%>
                <%=pageList.get(4).getPlayers1().getName()%>
                <%}%>
            </div>
        </div>
        <div class="player2-column">
            <div class="table-header">Player 2</div>
            <div class="player"><%if (pageList.size() >= 1) {%>
                <%=pageList.get(0).getPlayers2().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 2) {%>
                <%=pageList.get(1).getPlayers2().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 3) {%>
                <%=pageList.get(2).getPlayers2().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 4) {%>
                <%=pageList.get(3).getPlayers2().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 5) {%>
                <%=pageList.get(4).getPlayers2().getName()%>
                <%}%>
            </div>
        </div>
        <div class="winner-column">
            <div class="table-header">Winner</div>
            <div class="player"><%if (pageList.size() >= 1) {%>
                <%=pageList.get(0).getWinner().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 2) {%>
                <%=pageList.get(1).getWinner().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 3) {%>
                <%=pageList.get(2).getWinner().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 4) {%>
                <%=pageList.get(3).getWinner().getName()%>
                <%}%>
            </div>
            <div class="player"><%if (pageList.size() >= 5) {%>
                <%=pageList.get(4).getWinner().getName()%>
                <%}%>
            </div>
        </div>
    </div>
    <form method="post"
          action="${pageContext.request.contextPath}/matches?page=<%=request.getAttribute("pageNumber")%>">
        <div class="pagination">
            <button name="pagination" value="-1" class="prev-button">Prev</button>
            <div class="current-page">
                <%=request.getAttribute("pageNumber")%>
            </div>
            <button name="pagination" value="1" type="submit" class="next-button">Next</button>
        </div>
    </form>
</div>
</body>
</html>