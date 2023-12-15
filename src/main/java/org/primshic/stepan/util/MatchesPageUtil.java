package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchesPageUtil {
    public static String getRedirectUrl(int paginationValue, int pageNumber, String playerName) {
        int redirectTo = getPage(paginationValue, pageNumber);

        if (playerName != null) {
            return getFilterByNameUrl(redirectTo, playerName);
        } else {
            return getPageOnlyUrl(redirectTo);
        }
    }

    private static String getFilterByNameUrl(int page, String name) {
        return "/matches?page=" + page + "&filter_by_player_name=" + name;
    }

    private static String getPageOnlyUrl(int page) {
        return "/matches?page=" + page;
    }

    private static int getPage(int paginationValue, int pageNumber) {
        int redirectTo = 1;
        if (paginationValue != 0) {
            if (paginationValue == 1) {
                redirectTo = pageNumber + 1;
            } else if (pageNumber != 1) {
                redirectTo = pageNumber - 1;
            }
        }
        return redirectTo;
    }
}
