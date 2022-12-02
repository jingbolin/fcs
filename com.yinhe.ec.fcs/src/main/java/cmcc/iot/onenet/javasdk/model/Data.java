// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Data.java

package cmcc.iot.onenet.javasdk.model;


public class Data
{

    public Data(String at, Object value)
    {
        this.at = at;
        this.value = value;
    }

    public String getAt()
    {
        return at;
    }

    public void setAt(String at)
    {
        this.at = at;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    private String at;
    private Object value;
}
