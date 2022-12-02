// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DatastreamsResponse.java

package cmcc.iot.onenet.javasdk.response.datastreams;

import java.util.Date;
import java.util.List;

public class DatastreamsResponse
{

    public DatastreamsResponse(String id, Date createTime, List tags, String unit, String unitSymbol, Object currentValue, Date updateAt, 
            String formula, int interval, String cmd, String uuid)
    {
        this.id = id;
        this.createTime = createTime;
        this.tags = tags;
        this.unit = unit;
        this.unitSymbol = unitSymbol;
        this.currentValue = currentValue;
        this.updateAt = updateAt;
        this.formula = formula;
        this.interval = interval;
        this.cmd = cmd;
        this.uuid = uuid;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public List getTags()
    {
        return tags;
    }

    public void setTags(List tags)
    {
        this.tags = tags;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnitSymbol()
    {
        return unitSymbol;
    }

    public void setUnitSymbol(String unitSymbol)
    {
        this.unitSymbol = unitSymbol;
    }

    public Object getCurrentValue()
    {
        return currentValue;
    }

    public void setCurrentValue(Object currentValue)
    {
        this.currentValue = currentValue;
    }

    public Date getUpdateAt()
    {
        return updateAt;
    }

    public String getFormula()
    {
        return formula;
    }

    public void setFormula(String formula)
    {
        this.formula = formula;
    }

    public int getInterval()
    {
        return interval;
    }

    public void setInterval(int interval)
    {
        this.interval = interval;
    }

    public String getCmd()
    {
        return cmd;
    }

    public void setCmd(String cmd)
    {
        this.cmd = cmd;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public void setUpdateAt(Date updateAt)
    {
        this.updateAt = updateAt;
    }

    private String id;
    private Date createTime;
    private List tags;
    private String unit;
    private String unitSymbol;
    private Object currentValue;
    private Date updateAt;
    private String formula;
    private int interval;
    private String cmd;
    private String uuid;
}
