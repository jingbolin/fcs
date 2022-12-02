// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Permissions.java

package cmcc.iot.onenet.javasdk.model;

import java.util.List;

public class Permissions
{

    public Permissions(List resources, List access_methods)
    {
        this.resources = resources;
        this.access_methods = access_methods;
    }

    public List getResources()
    {
        return resources;
    }

    public void setResources(List resources)
    {
        this.resources = resources;
    }

    public List getAccess_methods()
    {
        return access_methods;
    }

    public void setAccess_methods(List access_methods)
    {
        this.access_methods = access_methods;
    }

    private List resources;
    private List access_methods;
}
