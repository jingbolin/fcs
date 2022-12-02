// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Yq_UserPfInfo.java

package com.yinhe.ec.cpps.model;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.yinhe.ec.cpps.model:
//            Yq_UserInfo

public class Yq_UserPfInfo extends Yq_UserInfo
{

    public Yq_UserPfInfo()
    {
        descDetails = new ArrayList();
    }

    public List getDescDetails()
    {
        return descDetails;
    }

    public void setDescDetails(List descDetails)
    {
        this.descDetails = descDetails;
    }

    List descDetails;
}
