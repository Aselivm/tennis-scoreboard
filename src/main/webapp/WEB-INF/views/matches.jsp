<%--
  Created by IntelliJ IDEA.
  User: step6
  Date: 02.12.2023
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        Matches page
    </title>
    <link rel="stylesheet" href="general.css">
    <link rel="stylesheet" href="matches.css">
    <link rel="stylesheet" href="main.css">
</head>
<body>
<div class="finished-matches">
    <div class="finished-matches-title">Finished matches</div>
    <div class="search-by-name-box">
        <p class="name">Name: </p>
        <input class="search-bar" placeholder="Steve">
        <button class="search-button">Search</button>
        <button class="clear-button">Clear</button>
    </div>
    <div class="finished-matches-table">
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
            <div class="player">Tom</div>
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
        <button class="button-prev">Prev</button>
        <div class="current-page">1</div>
        <button class="button-next">Next</button>
    </div>
</div>
</body>
</html>