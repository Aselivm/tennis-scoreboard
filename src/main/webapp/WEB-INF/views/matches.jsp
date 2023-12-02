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
            <div class="id">1</div>
            <div class="id">2</div>
            <div class="id">3</div>
            <div class="id">4</div>
            <div class="id">5</div>
        </div>
        <div class="player1-column">
            <div class="table-header">Player 1</div>
            <div class="player">Steve</div>
            <div class="player">Tomsadasdsadsad</div>
            <div class="player">Danny</div>
            <div class="player">Jack</div>
            <div class="player">John</div>
        </div>
        <div class="player2-column">
            <div class="table-header">Player 2</div>
            <div class="player">Nick</div>
            <div class="player">Sam</div>
            <div class="player">Irwin</div>
            <div class="player">Henry</div>
            <div class="player">Dean</div>
        </div>
        <div class="winner-column">
            <div class="table-header">Winner</div>
            <div class="player">Steve</div>
            <div class="player">Sam</div>
            <div class="player">Danny</div>
            <div class="player">Henry</div>
            <div class="player">John</div>
        </div>
    </div>
    <div class="pagination">
        <button class="prev-button">Prev</button>
        <div class="current-page">1</div>
        <button class="next-button">Next</button>
    </div>
</div>
</body>
</html>