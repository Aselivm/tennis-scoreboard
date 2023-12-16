package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputUtil {

    public static String getPlayerName(HttpServletRequest request, int playerNumber) {
        return request.getParameter("player_name_" + playerNumber).toUpperCase();
    }

    public static int getPlayerId(HttpServletRequest request) {
        String player = request.getParameter("player");
        int playerId = 0;
        if (player != null) {
            try {
                playerId = Integer.parseInt(player);
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }
        }
        return playerId;
    }

    public static int getPageNumber(HttpServletRequest request) {
        int pageNumber;
        String numberStr = request.getParameter("page");
        try {
            pageNumber = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return pageNumber;
    }

    public static String filterByPlayerName(HttpServletRequest request) {
        return request.getParameter("filter_by_player_name").toUpperCase();
    }
}
