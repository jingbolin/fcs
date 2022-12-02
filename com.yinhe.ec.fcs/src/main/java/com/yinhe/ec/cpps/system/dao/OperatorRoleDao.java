// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorRoleDao.java

package com.yinhe.ec.cpps.system.dao;

import com.yinhe.ec.cpps.model.OperRole;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OperatorRoleDao
{

    public abstract void addOperatorRole(OperRole operrole)
        throws DataAccessException;

    public abstract int getTotal(String s);

    public abstract List getOperRole(int i, int j, String s);

    public abstract void editOperRole(OperRole operrole)
        throws DataAccessException;

    public abstract void deleteOperRoleById(String s)
        throws DataAccessException;

    public abstract void delRoleMethodByOrId(String s)
        throws DataAccessException;

    public abstract void setRoleMethod(String s, String as[], String as1[], int i)
        throws DataAccessException;

    public abstract List getOperRoleByOperatorAccount(String s);

    public abstract List getRoleMethodByOrId(String s);

    public abstract List getOperRoleByOperId(int i);

    public abstract List getAllOperRole(int i);
}
