<%@ page import="org.primshic.stepan.model.Match" %>
<%@ page import="org.primshic.stepan.entity.Players" %>
<%@ page import="org.primshic.stepan.service.score.IndividualPlayerScore" %>
<%@ page import="org.primshic.stepan.service.score.MatchScore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match score page</title>
    <style>
        <%@include file="/WEB-INF/views/assets/match-score.css" %>
        <%@include file="/WEB-INF/views/assets/general.css" %>
    </style>
    <%
        Players player1 = (Players) request.getAttribute("player1");
        Players player2 = (Players) request.getAttribute("player2");
        MatchScore matchScore = (MatchScore) request.getAttribute("matchScore");
    %>
</head>
<body>
<div class="header">
    <div class="new-game-head">
        <a class="new-game-head-text" href="${pageContext.request.contextPath}/new-match">New game</a>
    </div>
    <div class="finished-matches-head">
        <a class="finished-matches-head-text" href="/matches?page=1&filter_by_player_name=">Finished matches</a>
    </div>
</div>
<div class="main-block">
    <div class="title">Match score</div>

    <div class="table">
        <div class="players">
            <div class="table-header">PLAYERS</div>
            <div class="player-name">
                <%=player1.getName()%>
            </div>
            <div class="player-name">
                <%=player2.getName()%>
            </div>
        </div>
        <div class="sets">
            <div class="table-header">SETS</div>
            <div class="player-sets">
                <%=matchScore.getPlayer1Score().getSet().getCounter()%>
            </div>
            <div class="player-sets">
                <%=matchScore.getPlayer2Score().getSet().getCounter()%>
            </div>
        </div>

        <div class="games">
            <div class="table-header">GAMES</div>
            <div class="player-games">
                <%=matchScore.getPlayer1Score().getGame().getCounter()%>
            </div>
            <div class="player-games">
                <%=matchScore.getPlayer2Score().getGame().getCounter()%>
            </div>
        </div>

        <div class="points">
            <div class="table-header">POINTS</div>
            <div class="player-points">
                <%=matchScore.getPlayer1Score().getPoint().getCounter()%>
            </div>
            <div class="player-points">
                <%=matchScore.getPlayer2Score().getPoint().getCounter()%>
            </div>
        </div>
    </div>
    <form id="add-point" class="hidden-forms" method="post"
          action="${pageContext.request.contextPath}/match-score?uuid=<%=request.getAttribute("uuid")%>"></form>
    <div class="add-point-button-div">
        <button form="add-point" name="player" value="<%=player1.getId()%>" type="submit" class="add-point-button">
            Player 1 wins
            point!
        </button>
        <button form="add-point" name="player" value="<%=player2.getId()%>" type="submit" class="add-point-button">
            Player 2 wins
            point!
        </button>
    </div>
</div>
</body>
</html>