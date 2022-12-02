// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorAndFMDaoImpl.java

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.system.dao.OperatorAndFMDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class OperatorAndFMDaoImpl
    implements OperatorAndFMDao
{

    public OperatorAndFMDaoImpl()
    {
    }

    public void setOperatorAndFM(int custId, int operatorId, String operatorAndFM)
        throws DataAccessException
    {
        String sql = "delete from OperatorAndFM where OperatorID = ?";
        jdbcTemplate.update(sql, new Object[] {
            Integer.valueOf(operatorId)
        });
        if(operatorAndFM != "")
        {
            sql = "insert into OperatorAndFM(OperatorID,FMID,CUSTID) values(?,?,?)";
            String operatorAndFMs[] = operatorAndFM.split(",");
            for(int i = 0; i < operatorAndFMs.length; i++)
                jdbcTemplate.update(sql, new Object[] {
                    Integer.valueOf(operatorId), operatorAndFMs[i], Integer.valueOf(custId)
                });

        }
    }

    private JdbcTemplate jdbcTemplate;
}
