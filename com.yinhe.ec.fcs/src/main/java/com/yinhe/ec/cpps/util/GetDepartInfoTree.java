// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetDepartInfoTree.java

package com.yinhe.ec.cpps.util;

import com.yinhe.ec.cpps.model.DepartInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetDepartInfoTree
{

    public GetDepartInfoTree()
    {
    }

    public static String getDepartInfo(List list, DepartInfo node)
    {
        returnStr1 = new StringBuilder();
        recursionFn(list, node);
        return modifyStr(returnStr1.toString());
    }

    public static String getDepartInfoNoRoot(List list, DepartInfo node)
    {
        returnStr2 = new StringBuilder();
        returnStr2.append("[");
        recursionFnNoRoot(list, node);
        returnStr2.append("],");
        return modifyStrNoRoot(returnStr2.toString());
    }

    public static String getDepartInfoForGrid(List list, DepartInfo node)
    {
        returnStr4 = new StringBuilder();
        recursionFnForGrid(list, node);
        return modifyStr(returnStr4.toString());
    }

    public static String getDepartInfoNoRootForGrid(List list, DepartInfo node)
    {
        returnStr5 = new StringBuilder();
        recursionFnNoRootForGrid(list, node);
        return modifyStrNoRoot(returnStr5.toString());
    }

    public static void recursionFn(List list, DepartInfo node)
    {
        if(hasChild(list, node))
        {
            returnStr1.append("{\"id\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr1.append(",\"text\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr1.append(",\"attributes\":");
            returnStr1.append("[{");
            returnStr1.append("\"remark\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr1.append(",\"departPid\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr1.append("}]");
            returnStr1.append(",\"iconCls\":");
            returnStr1.append("\"icon-house_go\"");
            returnStr1.append(",\"children\":[");
            List childList = getChildList(list, node);
            DepartInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFn(list, n))
                n = (DepartInfo)it.next();

            returnStr1.append("]},");
        } else
        {
            returnStr1.append("{\"id\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr1.append(",\"text\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr1.append(",\"attributes\":");
            returnStr1.append("[{");
            returnStr1.append("\"remark\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr1.append(",\"departPid\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr1.append("}]");
            returnStr1.append(",\"iconCls\":");
            returnStr1.append("\"icon-house\"");
            returnStr1.append("},");
        }
    }

    public static void recursionFnNoRoot(List list, DepartInfo node)
    {
        if(hasChild(list, node))
        {
            returnStr2.append("{\"id\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr2.append(",\"text\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr2.append(",\"attributes\":");
            returnStr2.append("[{");
            returnStr2.append("\"remark\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr2.append(",\"departPid\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr2.append("}]");
            returnStr2.append(",\"iconCls\":");
            returnStr2.append("\"icon-house_go\"");
            returnStr2.append(",\"children\":[");
            List childList = getChildList(list, node);
            DepartInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFnNoRoot(list, n))
                n = (DepartInfo)it.next();

            returnStr2.append("]},");
        } else
        {
            returnStr2.append("{\"id\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr2.append(",\"text\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr2.append(",\"attributes\":");
            returnStr2.append("[{");
            returnStr2.append("\"remark\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr2.append(",\"departPid\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr2.append("}]");
            returnStr2.append(",\"iconCls\":");
            returnStr2.append("\"icon-house\"");
            returnStr2.append("},");
        }
    }

    public static void recursionFnForGrid(List list, DepartInfo node)
    {
        if(hasChild(list, node))
        {
            returnStr4.append("{\"departId\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr4.append(",\"departName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr4.append(",\"remark\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr4.append(",\"departPid\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append("\"icon-table_add\"");
            returnStr4.append(",\"children\":[");
            List childList = getChildList(list, node);
            DepartInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFnForGrid(list, n))
                n = (DepartInfo)it.next();

            returnStr4.append("]},");
        } else
        {
            returnStr4.append("{\"departId\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr4.append(",\"departName\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr4.append(",\"remark\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr4.append(",\"departPid\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append("\"icon-table\"");
            returnStr4.append("},");
        }
    }

    public static void recursionFnNoRootForGrid(List list, DepartInfo node)
    {
        if(hasChild(list, node))
        {
            if(node.getDepartPid().equals("0"))
            {
                returnStr5.append("[");
                List childList = getChildList(list, node);
                DepartInfo n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFnNoRootForGrid(list, n))
                    n = (DepartInfo)it.next();

                returnStr5.append("],");
            } else
            {
                returnStr5.append("{\"departId\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
                returnStr5.append(",\"departName\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
                returnStr5.append(",\"remark\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
                returnStr5.append(",\"departPid\":");
                returnStr5.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
                returnStr5.append(",\"iconCls\":");
                returnStr5.append("\"icon-table_add\"");
                returnStr5.append(",\"children\":[");
                List childList = getChildList(list, node);
                DepartInfo n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFnNoRootForGrid(list, n))
                    n = (DepartInfo)it.next();

                returnStr5.append("]},");
            }
        } else
        {
            returnStr5.append("{\"departId\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getDepartId()).append("\"").toString());
            returnStr5.append(",\"departName\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getDepartName()).append("\"").toString());
            returnStr5.append(",\"remark\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getRemark()).append("\"").toString());
            returnStr5.append(",\"departPid\":");
            returnStr5.append((new StringBuilder("\"")).append(node.getDepartPid()).append("\"").toString());
            returnStr5.append(",\"iconCls\":");
            returnStr5.append("\"icon-table\"");
            returnStr5.append("},");
        }
    }

    public static boolean hasChild(List list, DepartInfo node)
    {
        return getChildList(list, node).size() > 0;
    }

    public static List getChildList(List list, DepartInfo node)
    {
        List li = new ArrayList();
        for(Iterator it = list.iterator(); it.hasNext();)
        {
            DepartInfo n = (DepartInfo)it.next();
            if(n.getDepartPid().equals(node.getDepartId()))
                li.add(n);
        }

        return li;
    }

    public static String modifyStr(String returnStr)
    {
        return (new StringBuilder("[")).append(returnStr).append("]").toString().replaceAll(",]", "]");
    }

    public static String modifyStrNoRoot(String returnStr)
    {
        String res = returnStr.replaceAll(",]", "]");
        res = res.substring(0, res.length() - 1);
        return res;
    }

    public static String getChildOrganization(List list, DepartInfo node)
    {
        returnStr3 = new StringBuilder();
        returnStr3.append((new StringBuilder("'")).append(node.getDepartId()).append("',").toString());
        getChildOrganizationFn(list, node);
        return returnStr3.toString();
    }

    public static void getChildOrganizationFn(List list, DepartInfo node)
    {
        if(hasChild(list, node))
        {
            List childList = getChildList(list, node);
            DepartInfo n;
            for(Iterator it = childList.iterator(); it.hasNext(); getChildOrganizationFn(list, n))
            {
                n = (DepartInfo)it.next();
                returnStr3.append((new StringBuilder("'")).append(n.getDepartId()).append("',").toString());
            }

        }
    }

    private static StringBuilder returnStr1;
    private static StringBuilder returnStr2;
    private static StringBuilder returnStr3;
    private static StringBuilder returnStr4;
    private static StringBuilder returnStr5;
}
