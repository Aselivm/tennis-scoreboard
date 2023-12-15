<%@ page import="org.primshic.stepan.entity.Players" %>
<%@ page import="org.primshic.stepan.service.score.MatchScore" %><%--
  Created by IntelliJ IDEA.
  User: step6
  Date: 04.12.2023
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match finished!</title>
    <style>
        <%@include file="/WEB-INF/views/assets/match-score.css" %>
        <%@include file="/WEB-INF/views/assets/general.css" %>
    </style>
    <%
        Players player1 = (Players) request.getAttribute("player1");
        Players player2 = (Players) request.getAttribute("player2");
        Players winner = (Players) request.getAttribute("winner");
        MatchScore matchScore = (MatchScore) request.getAttribute("matchScore");
    %>
</head>
<body>
<div class="main-block">
    <div class="title">Match finished! <%=winner.getName()%> wins!</div>

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


    </div>

</div>
</body>
</html>
