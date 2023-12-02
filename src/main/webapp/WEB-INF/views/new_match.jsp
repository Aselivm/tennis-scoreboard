<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>
        New match page
    </title>
    <style>
        <%@include file="/WEB-INF/views/assets/new-match.css" %>
        <%@include file="/WEB-INF/views/assets/general.css" %>
    </style>
</head>

<body>
<div class="new-game-inputs">
    <div class="player">
        <div class="player-name">Player name 1:</div>
        <input class="player-name-input" placeholder="Steve">
    </div>
    <div class="player">
        <div class="player-name">Player name 2:</div>
        <input class="player-name-input" placeholder="John">
    </div>
    <div class="start-button-div">
        <button class="start-button">Start</button>
    </div>

</div>
</body>

</html>