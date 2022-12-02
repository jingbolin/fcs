// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Cmd.java

package com.yinhe.ec.cpps.model;


// Referenced classes of package com.yinhe.ec.cpps.model:
//            MeterProtocol

public class Cmd
{

    public Cmd()
    {
        isuse = 0;
        typeId = 1;
    }

    public int getCmdId()
    {
        return cmdId;
    }

    public void setCmdId(int cmdId)
    {
        this.cmdId = cmdId;
    }

    public int getMeterPtl()
    {
        return meterPtl;
    }

    public void setMeterPtl(int meterPtl)
    {
        this.meterPtl = meterPtl;
    }

    public String getCmdName()
    {
        return cmdName;
    }

    public void setCmdName(String cmdName)
    {
        this.cmdName = cmdName;
    }

    public int getIsuse()
    {
        return isuse;
    }

    public void setIsuse(int isuse)
    {
        this.isuse = isuse;
    }

    public String getCmdCode()
    {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode)
    {
        this.cmdCode = cmdCode;
    }

    public String getDataLength()
    {
        return dataLength;
    }

    public void setDataLength(String dataLength)
    {
        this.dataLength = dataLength;
    }

    public String getDataItem()
    {
        return dataItem;
    }

    public void setDataItem(String dataItem)
    {
        this.dataItem = dataItem;
    }

    public String getReturnCmdCode()
    {
        return returnCmdCode;
    }

    public void setReturnCmdCode(String returnCmdCode)
    {
        this.returnCmdCode = returnCmdCode;
    }

    public String getReturnDataLength()
    {
        return returnDataLength;
    }

    public void setReturnDataLength(String returnDataLength)
    {
        this.returnDataLength = returnDataLength;
    }

    public String getReturnDataFormat()
    {
        return returnDataFormat;
    }

    public void setReturnDataFormat(String returnDataFormat)
    {
        this.returnDataFormat = returnDataFormat;
    }

    public MeterProtocol getMeterProtocol()
    {
        return meterProtocol;
    }

    public void setMeterProtocol(MeterProtocol meterProtocol)
    {
        this.meterProtocol = meterProtocol;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    private int cmdId;
    private int meterPtl;
    private String cmdName;
    private int isuse;
    private String cmdCode;
    private String dataLength;
    private String dataItem;
    private String returnCmdCode;
    private String returnDataLength;
    private String returnDataFormat;
    private MeterProtocol meterProtocol;
    private int typeId;
}
