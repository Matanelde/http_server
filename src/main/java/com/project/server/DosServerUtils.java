package com.project.server;

import org.apache.commons.lang3.math.NumberUtils;

public class DosServerUtils {
    public static String[] parseQuery(String query) {
        String message = null;
        if (query == null || "".equals(query.trim())) {
            return null;
        }
        String[] queryContentArr = query.split("=");
        if (queryContentArr.length < 2) {
            System.err.println("query is not valid " + query);
            return null;
        }

        if (!"clientId".equals(queryContentArr[0])) {
            message = "query not valid " + query;
        }

        if (!NumberUtils.isDigits(queryContentArr[1])) {
            message = "query not valid " + query;
        }

        if (message != null) {
            System.err.println("query is not valid " + query);
            return null;
        }
        return queryContentArr;
    }

    public static boolean isLessThan5Seconds(Long bigger, Long lower) {
        return (bigger - lower) / 1000 < 5.0;
    }
}
