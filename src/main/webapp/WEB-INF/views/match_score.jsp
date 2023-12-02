<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match score page</title>
    <style>
        <%@include file="/WEB-INF/views/assets/match-score.css" %>
        <%@include file="/WEB-INF/views/assets/general.css" %>
    </style>
</head>
<body>
<div class="main-block">
    <div class="title">Match score</div>

    <div class="table">
        <div class="players">
            <div class="table-header">PLAYERS</div>
            <div class="player-name">
                Nadal
            </div>
            <div class="player-name">
                Federersdaadsasdadsasdasdasddas
            </div>
        </div>

        <div class="sets">
            <div class="table-header">SETS</div>
            <div class="player-sets">
                1
            </div>
            <div class="player-sets">
                0
            </div>
        </div>

        <div class="games">
            <div class="table-header">GAMES</div>
            <div class="player-games">
                5
            </div>
            <div class="player-games">
                6
            </div>
        </div>

        <div class="points">
            <div class="table-header">POINTS</div>
            <div class="player-points">
                30
            </div>
            <div class="player-points">
                15
            </div>
        </div>
    </div>
    <div class="add-point-button-div">
        <button class="add-point-button">Player 1 wins point!</button>
        <button class="add-point-button">Player 2 wins point!</button>
    </div>
</div>
</body>
</html>