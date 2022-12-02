// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Muster.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            ConnInfo, MusterProtocol

public class Muster
{

    public Muster()
    {
        batchCount = 10;
    }

    public String getMusterNo()
    {
        return musterNo;
    }

    public void setMusterNo(String musterNo)
    {
        this.musterNo = musterNo;
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public String getConnNo()
    {
        return connNo;
    }

    public void setConnNo(String connNo)
    {
        this.connNo = connNo;
    }

    public int getMusterPtl()
    {
        return musterPtl;
    }

    public void setMusterPtl(int musterPtl)
    {
        this.musterPtl = musterPtl;
    }

    public int getManuId()
    {
        return manuId;
    }

    public void setManuId(int manuId)
    {
        this.manuId = manuId;
    }

    public String getMusterAddr()
    {
        return musterAddr;
    }

    public void setMusterAddr(String musterAddr)
    {
        this.musterAddr = musterAddr;
    }

    public String getAccountNo()
    {
        return accountNo;
    }

    public void setAccountNo(String accountNo)
    {
        this.accountNo = accountNo;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public int getInitFlag()
    {
        return initFlag;
    }

    public void setInitFlag(int initFlag)
    {
        this.initFlag = initFlag;
    }

    public int getReadServer()
    {
        return readServer;
    }

    public void setReadServer(int readServer)
    {
        this.readServer = readServer;
    }

    public int getReadServerPort()
    {
        return readServerPort;
    }

    public void setReadServerPort(int readServerPort)
    {
        this.readServerPort = readServerPort;
    }

    public int getReadStyle()
    {
        return readStyle;
    }

    public void setReadStyle(int readStyle)
    {
        this.readStyle = readStyle;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public Integer getMeterCount()
    {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount)
    {
        this.meterCount = meterCount;
    }

    public Integer getNotReadCount()
    {
        return notReadCount;
    }

    public void setNotReadCount(Integer notReadCount)
    {
        this.notReadCount = notReadCount;
    }

    public Integer getNotTryCount()
    {
        return notTryCount;
    }

    public void setNotTryCount(Integer notTryCount)
    {
        this.notTryCount = notTryCount;
    }

    public ConnInfo getCn()
    {
        return cn;
    }

    public void setCn(ConnInfo cn)
    {
        this.cn = cn;
    }

    public MusterProtocol getMusterProtocol()
    {
        return musterProtocol;
    }

    public void setMusterProtocol(MusterProtocol musterProtocol)
    {
        this.musterProtocol = musterProtocol;
    }

    public int getOnlineState()
    {
        return onlineState;
    }

    public void setOnlineState(int onlineState)
    {
        this.onlineState = onlineState;
    }

    public String getLastDate()
    {
        return lastDate;
    }

    public void setLastDate(String lastDate)
    {
        this.lastDate = lastDate;
    }

    public int getBatchCount()
    {
        return batchCount;
    }

    public void setBatchCount(int batchCount)
    {
        this.batchCount = batchCount;
    }

    private String musterNo;
    private int custId;
    private String areaId;
    private String connNo;
    private int musterPtl;
    private int manuId;
    private String musterAddr;
    private String accountNo;
    private String createDate;
    private int initFlag;
    private int readServer;
    private int readServerPort;
    private int readStyle;
    private String remark;
    private Integer meterCount;
    private Integer notReadCount;
    private Integer notTryCount;
    private ConnInfo cn;
    private MusterProtocol musterProtocol;
    private int onlineState;
    private String lastDate;
    private int batchCount;
}
