// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VehicleDuplicateKeyTranslator.java

package com.yinhe.ec.cpps.util;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import java.sql.SQLException;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            VehicleDuplicateKeyException

public class VehicleDuplicateKeyTranslator
    implements SQLExceptionTranslator
{

    public VehicleDuplicateKeyTranslator()
    {
    }

    public DataAccessException translate(String task, String sql, SQLException ex)
    {
        if(task == null)
            task = "";
        if(ex.getErrorCode() == 547)
            return new VehicleDuplicateKeyException(buildMessage((new StringBuilder("\u6570\u636E\u4F7F\u7528\u4E2D")).append(task).toString(), sql, ex));
        else
            return new UncategorizedSQLException(buildMessage(task, sql, ex), sql, ex);
    }

    private String buildMessage(String task, String sql, SQLException ex)
    {
        return (new StringBuilder("ERROR:")).append(ex.getErrorCode()).toString();
    }
}
