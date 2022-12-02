// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyList.java

package cmcc.iot.onenet.javasdk.response.key;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KeyList
{
    public static class KeysItem
    {
        public static class PermissionsItem
        {
            public static class DevicesItem
            {

                public String devid;

                public DevicesItem(String devid)
                {
                    this.devid = devid;
                }
            }


            public List getResources()
            {
                return resources;
            }

            public void setResources(List resources)
            {
                this.resources = resources;
            }

            public ArrayList getAccessMethods()
            {
                return accessMethods;
            }

            public void setAccessMethods(ArrayList accessMethods)
            {
                this.accessMethods = accessMethods;
            }

            public List resources;
            public ArrayList accessMethods;

            public PermissionsItem(List resources, ArrayList accessMethods)
            {
                this.resources = resources;
                this.accessMethods = accessMethods;
            }
        }


        public Date getCreateTime()
        {
            return createTime;
        }

        public void setCreateTime(Date createTime)
        {
            this.createTime = createTime;
        }

        public String getTitle()
        {
            return title;
        }

        public void setTitle(String title)
        {
            this.title = title;
        }

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public ArrayList getPermissions()
        {
            return permissions;
        }

        public void setPermissions(ArrayList permissions)
        {
            this.permissions = permissions;
        }

        public Date createTime;
        public String title;
        public String key;
        public ArrayList permissions;

        public KeysItem(Date createTime, String title, String key, ArrayList permissions)
        {
            this.createTime = createTime;
            this.title = title;
            this.key = key;
            this.permissions = permissions;
        }
    }


    public KeyList(int totalcount, int perpage, int page, ArrayList keys)
    {
        this.totalcount = totalcount;
        this.perpage = perpage;
        this.page = page;
        this.keys = keys;
    }

    public int getTotalcount()
    {
        return totalcount;
    }

    public void setTotalcount(int totalcount)
    {
        this.totalcount = totalcount;
    }

    public int getPerpage()
    {
        return perpage;
    }

    public void setPerpage(int perpage)
    {
        this.perpage = perpage;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public ArrayList getKeys()
    {
        return keys;
    }

    public void setKeys(ArrayList keys)
    {
        this.keys = keys;
    }

    public int totalcount;
    public int perpage;
    public int page;
    public ArrayList keys;
}
