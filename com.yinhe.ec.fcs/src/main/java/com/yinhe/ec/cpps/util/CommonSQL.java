//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.util;

public class CommonSQL {
    public CommonSQL() {
    }

    public static String searchByPage(String sql, int page, int rows) {
        String otherSql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (" + sql + ") A WHERE ROWNUM <= " + page * rows + ")WHERE RN > " + (page - 1) * rows;
        return otherSql;
    }

    public static String searchCount(String tableName, String condition) {
        String sql = "";
        if (condition != null && condition != "null") {
            sql = "select count(*) from " + tableName + " where " + condition;
        } else {
            sql = "select count(*) from " + tableName;
        }

        return sql;
    }

    public static String searchByPageForSql2005(String sql, int page, int rows, String id, String ascStr) {
        String otherSql = "select * from (select row_number() over(order by " + id + "  " + ascStr + ") as rowNum," + sql + ") as t where rowNum between " + ((page - 1) * rows + 1) + " and " + page * rows + " ";
        return otherSql;
    }

    public static String searchByPageForPG(String s, int page, int rows) {
        return "";
    }
}
