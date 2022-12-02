// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VehicleDuplicateKeyException.java

package com.yinhe.ec.cpps.util;

import org.springframework.dao.DataIntegrityViolationException;

public class VehicleDuplicateKeyException extends DataIntegrityViolationException
{

    public VehicleDuplicateKeyException(String msg)
    {
        super(msg);
    }

    public VehicleDuplicateKeyException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    private static final long serialVersionUID = 1L;
}
