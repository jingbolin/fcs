// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TriggersResponse.java

package cmcc.iot.onenet.javasdk.response.triggers;

import java.util.Date;
import java.util.List;

public class TriggersResponse
{

    public TriggersResponse()
    {
    }

    public Date getTargettype()
    {
        return targettype;
    }

    public void setTargettype(Date targettype)
    {
        this.targettype = targettype;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List getDsuuids()
    {
        return dsuuids;
    }

    public void setDsuuids(List dsuuids)
    {
        this.dsuuids = dsuuids;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getThreshold()
    {
        return threshold;
    }

    public void setThreshold(int threshold)
    {
        this.threshold = threshold;
    }

    public boolean isInvalid()
    {
        return invalid;
    }

    public void setInvalid(boolean invalid)
    {
        this.invalid = invalid;
    }

    private String id;
    private String title;
    private List dsuuids;
    private String url;
    private String type;
    private int threshold;
    private boolean invalid;
    private Date createtime;
    private Date targettype;
}
