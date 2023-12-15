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
<div class="header">
    <div class="new-game-head">
        <a class="new-game-head-text" href="${pageContext.request.contextPath}/new-match">New game</a>
    </div>
    <div class="finished-matches-head">
        <a class="finished-matches-head-text"
           href="${pageContext.request.contextPath}/matches?page=1&filter_by_player_name=">Finished matches</a>
    </div>
</div>
<div class="new-game-inputs">
    <form class="start-form" method="post" action="${pageContext.request.contextPath}/new-match"
          onsubmit="return validateNewGameForm();">
        <div class="player">
            <div class="player-name">Player name 1:</div>
            <input type="text" class="player-name-input" id="player-name-1" placeholder="Steve" name="player_name_1">
        </div>
        <div class="player">
            <div class="player-name">Player name 2:</div>
            <input type="text" class="player-name-input" id="player-name-2" placeholder="John" name="player_name_2">
        </div>
        <div class="start-button-div">
            <button type="submit" class="start-button">Start</button>
        </div>
    </form>
    <script>
        function validateNewGameForm() {
            let playerName1 = document.getElementById('player-name-1').value.trim();
            let playerName2 = document.getElementById('player-name-2').value.trim();
            if (playerName1 === playerName2) {
                alert("Please enter different names");
                return false;
            }
            return true;
        }
    </script>
</div>
</body>

</html>