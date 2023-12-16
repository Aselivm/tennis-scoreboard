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
        int pageNumber = (Integer) request.getAttribute("pageNumber");
        int next = pageNumber + 1;
        int prev = pageNumber - 1;
        String playerName = (String) request.getAttribute("playerName");
        boolean gotNext = (boolean) request.getAttribute("next");
    %>
</head>
<body>
<div class="header">
    <div class="new-game-head">
        <a class="new-game-head-text" href="${pageContext.request.contextPath}/new-match">New game</a>
    </div>
    <div class="finished-matches-head">
        <a class="finished-matches-head-text"
           href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=">Finished matches</a>
    </div>
</div>
<div class="main-block">
    <div class="title">Finished matches</div>
    <div class="search-by-name-box">
        <p class="name">Name: </p>
        <form id="searchForm" method="GET"
              action="${pageContext.request.contextPath}/matches"
              onsubmit="return validateSearchForm();"
        >
            <c:if test="${playerName != null}">
                <input type="hidden" name="page" value="1">
                <input id="playerNameInput" name="filter_by_player_name" class="search-bar" value="${playerName}"
                       placeholder="Steve">
            </c:if>
            <button type="submit" form="searchForm" class="search-button">Search</button>
        </form>

        <script>
            function validateSearchForm() {
                let playerNameInput = document.getElementById('playerNameInput');
                if (playerNameInput && playerNameInput.value.trim() === '') {
                    alert("Please enter a player name.");
                    return false;
                }
                return true;
            }
        </script>

        <form id="clearForm" method="GET"
              action="${pageContext.request.contextPath}/matches"
        >
            <input type="hidden" name="page" value="<%=pageNumber%>">
            <input type="hidden" name="filter_by_player_name" class="search-bar" placeholder="Steve">
        </form>
        <button form="clearForm" class="clear-button">Clear</button>
    </div>
    <div class="table">
        <div class="id-column">
            <div class="table-header">ID</div>
            <% for (int i = 0; i < 5; i++) {%>
            <div class="id">
                <%if (pageList.size() >= (i + 1)) {%>
                <%=pageList.get(i).getId()%>
                <%}%>
            </div>
            <%}%>
        </div>
        <div class="player1-column">
            <div class="table-header">Player 1</div>
            <% for (int i = 0; i < 5; i++) {%>
            <div class="player">
                <%if (pageList.size() >= (i + 1)) {%>
                <%=pageList.get(i).getPlayers1().getName()%>
                <%}%>
            </div>
            <%}%>
        </div>
        <div class="player2-column">
            <div class="table-header">Player 2</div>
            <% for (int i = 0; i < 5; i++) {%>
            <div class="player">
                <%if (pageList.size() >= (i + 1)) {%>
                <%=pageList.get(i).getPlayers2().getName()%>
                <%}%>
            </div>
            <%}%>
        </div>
        <div class="winner-column">
            <div class="table-header">Winner</div>
            <% for (int i = 0; i < 5; i++) {%>
            <div class="player">
                <%if (pageList.size() >= (i + 1)) {%>
                <%=pageList.get(i).getWinner().getName()%>
                <%}%>
            </div>
            <%}%>
        </div>
    </div>
    <div class="pagination">
        <form class="hidden-forms" id="prevForm" method="GET"
              action="${pageContext.request.contextPath}/matches"
        >
            <%if (prev != 0) {%>
            <input type="hidden" name="page" value="<%=prev%>">
            <%} else {%>
            <input type="hidden" name="page" value="<%=pageNumber%>">
            <%}%>
            <% if (playerName != null) { %>
            <input type="hidden" name="filter_by_player_name" value="<%=playerName%>">
            <% } %>
        </form>
        <button form="prevForm" type="submit" class="next-button">Prev</button>
        <!-- Текущая страница -->
        <div class="current-page">
            <%=request.getAttribute("pageNumber")%>
        </div>

        <form class="hidden-forms" id="nextForm" method="GET"
              action="${pageContext.request.contextPath}/matches"
        >
            <%if (gotNext) {%>
            <input type="hidden" name="page" value="<%=next%>">
            <%} else {%>
            <input type="hidden" name="page" value="<%=pageNumber%>">
            <%}%>
            <% if (playerName != null) { %>
            <input type="hidden" name="filter_by_player_name" value="<%=playerName%>">
            <% } %>
        </form>
        <button form="nextForm" type="submit" class="next-button">Next</button>
    </div>
</div>
</body>
</html>