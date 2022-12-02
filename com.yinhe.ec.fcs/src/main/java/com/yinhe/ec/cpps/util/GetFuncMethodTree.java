// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetFuncMethodTree.java

package com.yinhe.ec.cpps.util;

import com.yinhe.ec.cpps.model.FuncMethod;
import com.yinhe.ec.cpps.model.RoleFuncMethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetFuncMethodTree
{

    public GetFuncMethodTree()
    {
    }

    public static String getFuncMethod(List list, FuncMethod node)
    {
        returnStr1 = new StringBuffer();
        recursionFn(list, node);
        return modifyStr(returnStr1.toString());
    }

    public static String getFuncMethodNoRoot(List list, FuncMethod node)
    {
        returnStr2 = new StringBuffer();
        recursionFnNoRoot(list, node);
        return modifyStrNoRoot(returnStr2.toString());
    }

    public static String getFuncMethodTree(List list, FuncMethod node, List list2)
    {
        returnStr3 = new StringBuffer();
        recursionFnTree(list, node, list2);
        String res = modifyStr(returnStr3.toString());
        return res;
    }

    public static String getFuncMethodTreeNoRoot(List list, FuncMethod node)
    {
        returnStr4 = new StringBuffer();
        recursionFnTreeNoRoot(list, node);
        return modifyStrNoRoot(returnStr4.toString());
    }

    public static void recursionFn(List list, FuncMethod node)
    {
        if(hasChild(list, node))
        {
            returnStr1.append("{\"fmId\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
            returnStr1.append(",\"fmName\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
            returnStr1.append(",\"fmUrl\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmUrl()).append("\"").toString());
            returnStr1.append(",\"fmDesc\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
            returnStr1.append(",\"fmPid\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmPid()).append("\"").toString());
            returnStr1.append(",\"fmCreateDate\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmCreateDate()).append("\"").toString());
            returnStr1.append(",\"fmPicUrl\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmPicUrl()).append("\"").toString());
            returnStr1.append(",\"isMethod\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getIsMethod()).append("\"").toString());
            returnStr1.append(",\"iconCls\":");
            returnStr1.append("\"icon-table_add\"");
            returnStr1.append(",\"children\":[");
            List childList = getChildList(list, node);
            FuncMethod n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFn(list, n))
                n = (FuncMethod)it.next();

            returnStr1.append("]},");
        } else
        {
            returnStr1.append("{\"fmId\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
            returnStr1.append(",\"fmName\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
            returnStr1.append(",\"fmUrl\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmUrl()).append("\"").toString());
            returnStr1.append(",\"fmDesc\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
            returnStr1.append(",\"fmPid\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmPid()).append("\"").toString());
            returnStr1.append(",\"fmCreateDate\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmCreateDate()).append("\"").toString());
            returnStr1.append(",\"fmPicUrl\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getFmPicUrl()).append("\"").toString());
            returnStr1.append(",\"isMethod\":");
            returnStr1.append((new StringBuilder("\"")).append(node.getIsMethod()).append("\"").toString());
            returnStr1.append(",\"iconCls\":");
            returnStr1.append("\"icon-table\"");
            returnStr1.append("},");
        }
    }

    public static void recursionFnNoRoot(List list, FuncMethod node)
    {
        if(hasChild(list, node))
        {
            if(node.getFmPid().equals(Integer.valueOf(0)))
            {
                returnStr2.append("[");
                List childList = getChildList(list, node);
                FuncMethod n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFn(list, n))
                    n = (FuncMethod)it.next();

                returnStr2.append("],");
            } else
            {
                returnStr2.append("{\"fmId\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
                returnStr2.append(",\"fmName\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
                returnStr2.append(",\"fmUrl\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmUrl()).append("\"").toString());
                returnStr2.append(",\"fmDesc\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
                returnStr2.append(",\"fmPid\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmPid()).append("\"").toString());
                returnStr2.append(",\"fmCreateDate\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmCreateDate()).append("\"").toString());
                returnStr2.append(",\"fmPicUrl\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getFmPicUrl()).append("\"").toString());
                returnStr2.append(",\"isMethod\":");
                returnStr2.append((new StringBuilder("\"")).append(node.getIsMethod()).append("\"").toString());
                returnStr2.append(",\"iconCls\":");
                returnStr2.append("\"icon-table_add\"");
                returnStr2.append(",\"children\":[");
                List childList = getChildList(list, node);
                FuncMethod n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFn(list, n))
                    n = (FuncMethod)it.next();

                returnStr2.append("]},");
            }
        } else
        {
            returnStr2.append("{\"fmId\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
            returnStr2.append(",\"fmName\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
            returnStr2.append(",\"fmUrl\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmUrl()).append("\"").toString());
            returnStr2.append(",\"fmDesc\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
            returnStr2.append(",\"fmPid\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmPid()).append("\"").toString());
            returnStr2.append(",\"fmCreateDate\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmCreateDate()).append("\"").toString());
            returnStr2.append(",\"fmPicUrl\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getFmPicUrl()).append("\"").toString());
            returnStr2.append(",\"isMethod\":");
            returnStr2.append((new StringBuilder("\"")).append(node.getIsMethod()).append("\"").toString());
            returnStr2.append(",\"iconCls\":");
            returnStr2.append("\"icon-table\"");
            returnStr2.append("},");
        }
    }

    public static void recursionFnTree(List list, FuncMethod node, List list2)
    {
        if(hasChild(list, node))
        {
            boolean sign = false;
            for(Iterator iterator = list2.iterator(); iterator.hasNext();)
            {
                RoleFuncMethod roleFuncMethod = (RoleFuncMethod)iterator.next();
                if(roleFuncMethod.getFuncMethod().getFmId().equals(node.getFmId()) && roleFuncMethod.getRfmStatus().equals("checked"))
                {
                    returnStr3.append("{\"id\":");
                    returnStr3.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
                    returnStr3.append(",\"text\":");
                    returnStr3.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
                    returnStr3.append(",\"attributes\":");
                    returnStr3.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
                    returnStr3.append(",\"checked\":");
                    returnStr3.append("\"true\"");
                    returnStr3.append(",\"iconCls\":");
                    returnStr3.append("\"icon-table_add\"");
                    returnStr3.append(",\"children\":[");
                    sign = true;
                    break;
                }
            }

            if(!sign)
            {
                returnStr3.append("{\"id\":");
                returnStr3.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
                returnStr3.append(",\"text\":");
                returnStr3.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
                returnStr3.append(",\"attributes\":");
                returnStr3.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
                returnStr3.append(",\"iconCls\":");
                returnStr3.append("\"icon-table_add\"");
                returnStr3.append(",\"children\":[");
            }
            List childList = getChildList(list, node);
            FuncMethod n;
            for(Iterator it = childList.iterator(); it.hasNext(); recursionFnTree(list, n, list2))
                n = (FuncMethod)it.next();

            returnStr3.append("]},");
        } else
        {
            boolean sign = false;
            for(Iterator iterator1 = list2.iterator(); iterator1.hasNext();)
            {
                RoleFuncMethod roleFuncMethod = (RoleFuncMethod)iterator1.next();
                if(roleFuncMethod.getFuncMethod().getFmId().equals(node.getFmId()) && roleFuncMethod.getRfmStatus().equals("checked"))
                {
                    returnStr3.append("{\"id\":");
                    returnStr3.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
                    returnStr3.append(",\"text\":");
                    returnStr3.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
                    returnStr3.append(",\"attributes\":");
                    returnStr3.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
                    returnStr3.append(",\"checked\":");
                    returnStr3.append("\"true\"");
                    returnStr3.append(",\"iconCls\":");
                    returnStr3.append("\"icon-table\"");
                    returnStr3.append("},");
                    sign = true;
                    break;
                }
            }

            if(!sign)
            {
                returnStr3.append("{\"id\":");
                returnStr3.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
                returnStr3.append(",\"text\":");
                returnStr3.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
                returnStr3.append(",\"attributes\":");
                returnStr3.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
                returnStr3.append(",\"iconCls\":");
                returnStr3.append("\"icon-table\"");
                returnStr3.append("},");
            }
        }
    }

    public static void recursionFnTreeNoRoot(List list, FuncMethod node)
    {
        if(hasChild(list, node))
        {
            if(node.getFmPid().equals(Integer.valueOf(0)))
            {
                returnStr4.append("[");
                List childList = getChildList(list, node);
                FuncMethod n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFnTreeNoRoot(list, n))
                    n = (FuncMethod)it.next();

                returnStr4.append("],");
            } else
            {
                returnStr4.append("{\"id\":");
                returnStr4.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
                returnStr4.append(",\"text\":");
                returnStr4.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
                returnStr4.append(",\"attributes\":");
                returnStr4.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
                returnStr4.append(",\"iconCls\":");
                returnStr4.append((new StringBuilder("\"")).append(node.getFmPicUrl()).append("\"").toString());
                returnStr4.append(",\"children\":[");
                List childList = getChildList(list, node);
                FuncMethod n;
                for(Iterator it = childList.iterator(); it.hasNext(); recursionFnTreeNoRoot(list, n))
                    n = (FuncMethod)it.next();

                returnStr4.append("]},");
            }
        } else
        {
            returnStr4.append("{\"id\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getFmId()).append("\"").toString());
            returnStr4.append(",\"text\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getFmName()).append("\"").toString());
            returnStr4.append(",\"attributes\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getFmDesc()).append("\"").toString());
            returnStr4.append(",\"iconCls\":");
            returnStr4.append((new StringBuilder("\"")).append(node.getFmPicUrl()).append("\"").toString());
            returnStr4.append("},");
        }
    }

    public static boolean hasChild(List list, FuncMethod node)
    {
        return getChildList(list, node).size() > 0;
    }

    public static List getChildList(List list, FuncMethod node)
    {
        List li = new ArrayList();
        for(Iterator it = list.iterator(); it.hasNext();)
        {
            FuncMethod n = (FuncMethod)it.next();
            if(n.getFmPid().equals(node.getFmId()))
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
        return (new StringBuilder("[")).append(res).append("]").toString();
    }

    private static StringBuffer returnStr1 = null;
    private static StringBuffer returnStr2 = null;
    private static StringBuffer returnStr3 = null;
    private static StringBuffer returnStr4 = null;

}
