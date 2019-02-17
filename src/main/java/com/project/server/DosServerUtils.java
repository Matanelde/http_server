package com.project.server;

import org.apache.commons.lang3.math.NumberUtils;

public class DosServerUtils {
    public static String[] parseQuery(String query) throws Exception {
        String message = null;
        if (query == null || "".equals(query.trim())) {
            message = "query is empty";
        }
        String[] queryContentArr = query.split("=");
        if (queryContentArr.length < 2) {
            message = "query not valid " + query;
        }

        if (!"clientId".equals(queryContentArr[0])) {
            message = "query not valid " + query;
        }

        if (!NumberUtils.isDigits(queryContentArr[1])) {
            message = "query not valid " + query;
        }

        if (message != null) {
            throw new Exception("query not valid " + query);
        }
        return queryContentArr;
    }

    public static boolean isLessThan5Seconds(Long bigger, Long lower) {
        return (bigger - lower) / 1000 < 5.0;
    }
}
