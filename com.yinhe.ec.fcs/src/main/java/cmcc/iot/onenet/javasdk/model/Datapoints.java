// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Datapoints.java

package cmcc.iot.onenet.javasdk.model;

import java.util.List;

public class Datapoints
{

    public List getDatapoints()
    {
        return datapoints;
    }

    public void setDatapoints(List datapoints)
    {
        this.datapoints = datapoints;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Datapoints(String id, List datapoints)
    {
        this.id = id;
        this.datapoints = datapoints;
    }

    public String id;
    public List datapoints;
}
