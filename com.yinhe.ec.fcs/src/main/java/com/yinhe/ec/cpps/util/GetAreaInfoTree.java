// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetAreaInfoTree.java

package com.yinhe.ec.cpps.util;

import com.yinhe.ec.cpps.model.AreaDosageLossInfo;
import com.yinhe.ec.cpps.model.AreaInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetAreaInfoTree
{

    public GetAreaInfoTree()
    {
    }

    public static String getAreaInfo(List list, AreaInfo node)
    {
        returnStr1 = new StringBuilder();
        recursionFn(list, node);
        return modifyStr(returnStr1.toString());
    }

    public static String getAreaInfoNoRoot(List list, AreaInfo node)
    {
        returnStr2 = new StringBuilder();
        returnStr2.append("[");
        recursionFnNoRoot(list, node);
        returnStr2.append("],");
        return modifyStrNoRoot(returnStr2.toString());
    }

    public static String getAreaInfoForGrid(List list, AreaInfo node)
    {
        returnStr4 = new StringBuilder();
        recursionFnForGrid(list, node);
        return modifyStr(returnStr4.toString());
    }

    public static String getAreaInfoNoRootForGrid(List list, AreaInfo node)
    {
        returnStr5 = new StringBuilder();
        recursionFnNoRootForGrid(list, node);
        return modifyStrNoRoot(returnStr5.toString());
    }

    public static void recursionFn(List list, AreaInfo node)
    {
        if(hasChild(list, node))
        {
            returnStr1.append("{\"id\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr1.append(",\"text\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr1.append(",\"attributes\":");
            returnStr1.append("[{");
            returnStr1.append("\"areaPid\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr1.append(",\"remark\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr1.append("}]");
            returnStr1.append(",\"iconCls\":");
            returnStr1.append("\"icon-house_go\"");
            returnStr1.append(",\"children\":[");
            List childList = getChildList(list, node);
            AreaInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFn(list, n))
                n = (AreaInfo)it.next();

            returnStr1.append("]},");
        } else
        {
            returnStr1.append("{\"id\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr1.append(",\"text\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr1.append(",\"attributes\":");
            returnStr1.append("[{");
            returnStr1.append("\"areaPid\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr1.append(",\"remark\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr1.append("}]");
            returnStr1.append(",\"iconCls\":");
            returnStr1.append("\"icon-house\"");
            returnStr1.append("},");
        }
    }

    public static void recursionFnNoRoot(List list, AreaInfo node)
    {
        if(hasChild(list, node))
        {
            returnStr2.append("{\"id\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr2.append(",\"text\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr2.append(",\"attributes\":");
            returnStr2.append("[{");
            returnStr2.append("\"areaPid\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr2.append(",\"remark\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr2.append("}]");
            returnStr2.append(",\"iconCls\":");
            returnStr2.append("\"icon-house_go\"");
            returnStr2.append(",\"children\":[");
            List childList = getChildList(list, node);
            AreaInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFnNoRoot(list, n))
                n = (AreaInfo)it.next();

            returnStr2.append("]},");
        } else
        {
            returnStr2.append("{\"id\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr2.append(",\"text\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr2.append(",\"attributes\":");
            returnStr2.append("[{");
            returnStr2.append("\"areaPid\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr2.append(",\"remark\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr2.append("}]");
            returnStr2.append(",\"iconCls\":");
            returnStr2.append("\"icon-house\"");
            returnStr2.append("},");
        }
    }

    public static void recursionFnForGrid(List list, AreaInfo node)
    {
        if(hasChild(list, node))
        {
            returnStr4.append("{\"areaId\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr4.append(",\"areaName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr4.append(",\"areaPid\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr4.append(",\"remark\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr4.append(",\"areaFullName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaFullName()).append("\"").toString());
            returnStr4.append(",\"businessFlag\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getBusinessFlag()).append("\"").toString());
            returnStr4.append(",\"meterNo\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getMeterNo()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append("\"icon-table_add\"");
            returnStr4.append(",\"children\":[");
            List childList = getChildList(list, node);
            AreaInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFnForGrid(list, n))
                n = (AreaInfo)it.next();

            returnStr4.append("]},");
        } else
        {
            returnStr4.append("{\"areaId\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr4.append(",\"areaName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr4.append(",\"areaPid\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr4.append(",\"remark\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr4.append(",\"areaFullName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaFullName()).append("\"").toString());
            returnStr4.append(",\"businessFlag\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getBusinessFlag()).append("\"").toString());
            returnStr4.append(",\"meterNo\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getMeterNo()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append("\"icon-table\"");
            returnStr4.append("},");
        }
    }

    public static void recursionFnNoRootForGrid(List list, AreaInfo node)
    {
        if(hasChild(list, node))
        {
            if(node.getAreaPid().equals("0"))
            {
                returnStr5.append("[");
                List childList = getChildList(list, node);
                AreaInfo n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFnNoRootForGrid(list, n))
                    n = (AreaInfo)it.next();

                returnStr5.append("],");
            } else
            {
                returnStr5.append("{\"areaId\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
                returnStr5.append(",\"areaName\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
                returnStr5.append(",\"areaPid\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
                returnStr5.append(",\"remark\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
                returnStr5.append(",\"areaFullName\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getAreaFullName()).append("\"").toString());
                returnStr5.append(",\"businessFlag\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getBusinessFlag()).append("\"").toString());
                returnStr5.append(",\"meterNo\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getMeterNo()).append("\"").toString());
                returnStr5.append(",\"iconCls\":");
                returnStr5.append("\"icon-table_add\"");
                returnStr5.append(",\"children\":[");
                List childList = getChildList(list, node);
                AreaInfo n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFnNoRootForGrid(list, n))
                    n = (AreaInfo)it.next();

                returnStr5.append("]},");
            }
        } else
        {
            returnStr5.append("{\"areaId\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr5.append(",\"areaName\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr5.append(",\"areaPid\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr5.append(",\"remark\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr5.append(",\"areaFullName\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getAreaFullName()).append("\"").toString());
            returnStr5.append(",\"businessFlag\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getBusinessFlag()).append("\"").toString());
            returnStr5.append(",\"meterNo\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getMeterNo()).append("\"").toString());
            returnStr5.append(",\"iconCls\":");
            returnStr5.append("\"icon-table\"");
            returnStr5.append("},");
        }
    }

    public static boolean hasChild(List list, AreaInfo node)
    {
        return getChildList(list, node).size() > 0;
    }

    public static List getChildList(List list, AreaInfo node)
    {
        List li = new ArrayList();
        for(Iterator it = list.iterator(); it.hasNext();)
        {
            AreaInfo n = (AreaInfo)it.next();
            if(n.getAreaPid().equals(node.getAreaId()))
                li.add(n);
        }

        return li;
    }

    public static String modifyStr(String returnStr)
    {
        String s = (new StringBuilder("[")).append(returnStr).append("]").toString().replaceAll(",]", "]");
        return s;
    }

    public static String modifyStrNoRoot(String returnStr)
    {
        String res = returnStr.replaceAll(",]", "]");
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String getChildRegionIds(List list, AreaInfo node)
    {
        returnStr3 = new StringBuilder();
        returnStr3.append((new StringBuilder("'")).append(node.getAreaId()).append("',").toString());
        getChildOrganizationFn(list, node);
        return returnStr3.toString();
    }

    public static void getChildOrganizationFn(List list, AreaInfo node)
    {
        if(hasChild(list, node))
        {
            List childList = getChildList(list, node);
            AreaInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); getChildOrganizationFn(list, n))
            {
                n = (AreaInfo)it.next();
                returnStr3.append((new StringBuilder("'")).append(n.getAreaId()).append("',").toString());
            }

        }
    }

    public static String getLossAreaInfoForGrid(List list, AreaDosageLossInfo node)
    {
        returnStr4 = new StringBuilder();
        recursionLossForGrid(list, node);
        return modifyStr(returnStr4.toString());
    }

    public static void recursionLossForGrid(List list, AreaDosageLossInfo node)
    {
        if(hasLossChild(list, node))
        {
            returnStr4.append("{\"areaId\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr4.append(",\"areaName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr4.append(",\"areaPid\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr4.append(",\"meterNo\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getMeterNo()).append("\"").toString());
            returnStr4.append(",\"zDosage\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getzDosage()).append("\"").toString());
            returnStr4.append(",\"hDosage\":");
            returnStr4.append((new StringBuilder("\"")).append(node.gethDosage()).append("\"").toString());
            returnStr4.append(",\"lossDosage\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getLossDosage()).append("\"").toString());
            returnStr4.append(",\"lossPercent\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getLossPercent()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append("\"icon-table_add\"");
            returnStr4.append(",\"children\":[");
            List childList = getLossChildList(list, node);
            AreaDosageLossInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionLossForGrid(list, n))
                n = (AreaDosageLossInfo)it.next();

            returnStr4.append("]},");
        } else
        {
            returnStr4.append("{\"areaId\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaId()).append("\"").toString());
            returnStr4.append(",\"areaName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaName()).append("\"").toString());
            returnStr4.append(",\"areaPid\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getAreaPid()).append("\"").toString());
            returnStr4.append(",\"meterNo\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getMeterNo()).append("\"").toString());
            returnStr4.append(",\"zDosage\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getzDosage()).append("\"").toString());
            returnStr4.append(",\"hDosage\":");
            returnStr4.append((new StringBuilder("\"")).append(node.gethDosage()).append("\"").toString());
            returnStr4.append(",\"lossDosage\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getLossDosage()).append("\"").toString());
            returnStr4.append(",\"lossPercent\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getLossPercent()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append("\"icon-table\"");
            returnStr4.append("},");
        }
    }

    public static boolean hasLossChild(List list, AreaDosageLossInfo node)
    {
        return getLossChildList(list, node).size() > 0;
    }

    public static List getLossChildList(List list, AreaDosageLossInfo node)
    {
        List li = new ArrayList();
        for(Iterator it = list.iterator(); it.hasNext();)
        {
            AreaDosageLossInfo n = (AreaDosageLossInfo)it.next();
            if(n.getAreaPid().equals(node.getAreaId()))
                li.add(n);
        }

        return li;
    }

    private static StringBuilder returnStr1;
    private static StringBuilder returnStr2;
    private static StringBuilder returnStr3;
    private static StringBuilder returnStr4;
    private static StringBuilder returnStr5;
}
