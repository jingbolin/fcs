// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TriggersList.java

package cmcc.iot.onenet.javasdk.response.triggers;

import java.util.Date;
import java.util.List;

public class TriggersList
{
    public static class TriggersItem
    {

        private String id;
        private String title;
        private List dsuuids;
        private String url;
        private String type;
        private int threshold;
        private boolean invalid;
        private Date createtime;
        private Date targettype;

        public TriggersItem(String id, String title, List dsuuids, String url, String type, int threshold, boolean invalid, 
                Date createtime, Date targettype)
        {
            this.id = id;
            this.title = title;
            this.dsuuids = dsuuids;
            this.url = url;
            this.type = type;
            this.threshold = threshold;
            this.invalid = invalid;
            this.createtime = createtime;
            this.targettype = targettype;
        }
    }


    public TriggersList(int totalcount, int perpage, int page, List list)
    {
        this.totalcount = totalcount;
        this.perpage = perpage;
        this.page = page;
        this.list = list;
    }

    private int totalcount;
    private int perpage;
    private int page;
    private List list;
}
