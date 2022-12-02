// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserInfoView.java

package com.yinhe.ec.cpps.model;


public class UserInfoView
{

    public UserInfoView()
    {
        custId = 0;
        custName = "";
        userId = "";
        userCode = "";
        userName = "";
        userAddr = "";
        areaId = "";
        areaName = "";
        areaListId = "";
        readOrder = 0;
        userTypeId = 0;
        userTypeName = "";
        billKind = 0;
        billTypeUrl = "";
        mobilePhone = "";
        phone = "";
        email = "";
        memberCount = 1;
        floors = 1;
        areas = Double.valueOf(0.0D);
        userState = 1;
        createDate = "";
        createUser = "";
        contractNo = "";
        smsKind = 1;
        checkUser = 0;
        checkOrder = "";
        uniId = "";
        meterNo = "";
        typeId = 1;
        typeName = "";
        manuId = 1;
        manuName = "";
        musterNo = "";
        repNo = "";
        recNo = 0;
        comPort = 1;
        baud = 1;
        connPara = "";
        musterPtl = 1;
        meterPtl = 1;
        batch = 1;
        dynameter = 1;
        alarm1 = 0;
        alarm2 = 0;
        dn = 1;
        span = Double.valueOf(1.0D);
        sealNo = "";
        subNo = "";
        subName = "";
        mboxId = 0;
        mboxNo = "";
        pattern = 1;
        onState = 1;
        keyFlag = 0;
        dosageSum = Double.valueOf(0.0D);
        readDate = "";
        syncFlag = 1;
        tryFlag = 0;
        useFlag = 1;
        initDosage = Double.valueOf(0.0D);
        useDosageSum = Double.valueOf(0.0D);
        transDosage = Double.valueOf(0.0D);
        allSum = Double.valueOf(0.0D);
        buySum = Double.valueOf(0.0D);
        leftSum = Double.valueOf(0.0D);
        initSum = 0;
        errFlag = 1;
        maxDosage = Double.valueOf(50D);
        verifyDate = 0;
        spanCnt = 0;
        meterPic = "";
        feeItemId = "";
        feeItemName = "";
        feeState = 0;
        feeStyle = 0;
        yearDosageDt = "";
        feeDate = "";
        operatorId = 0;
        ytotal = Double.valueOf(0.0D);
        yquantity = Double.valueOf(0.0D);
        price = "";
        sendType = 0;
        sendDetail = "";
        sendDate = "";
        manageFlag = 0;
        manageDate = "";
        manageCount = 0;
        idCard = "";
        onOff = 1;
        thesdfprice = Double.valueOf(0.0D);
        thewsfprice = Double.valueOf(0.0D);
        remark = "";
        buyCount = 0;
        scheduleBuysum = Double.valueOf(0.0D);
        scheduleLeftsum = Double.valueOf(0.0D);
        stepTotal = Double.valueOf(0.0D);
        chargemethod = 0;
        changeLeftsum = Double.valueOf(0.0D);
        xsfeeItemId = "";
        deviceId = "";
        shareFlag = 0;
        shareRatio = Double.valueOf(100D);
    }

    public int getCustId()
    {
        return custId;
    }

    public void setCustId(int custId)
    {
        this.custId = custId;
    }

    public String getCustName()
    {
        return custName;
    }

    public void setCustName(String custName)
    {
        this.custName = custName;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserCode()
    {
        return userCode;
    }

    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserAddr()
    {
        return userAddr;
    }

    public void setUserAddr(String userAddr)
    {
        this.userAddr = userAddr;
    }

    public String getAreaId()
    {
        return areaId;
    }

    public void setAreaId(String areaId)
    {
        this.areaId = areaId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public String getAreaListId()
    {
        return areaListId;
    }

    public void setAreaListId(String areaListId)
    {
        this.areaListId = areaListId;
    }

    public int getReadOrder()
    {
        return readOrder;
    }

    public void setReadOrder(int readOrder)
    {
        this.readOrder = readOrder;
    }

    public int getUserTypeId()
    {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId)
    {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName()
    {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName)
    {
        this.userTypeName = userTypeName;
    }

    public int getBillKind()
    {
        return billKind;
    }

    public void setBillKind(int billKind)
    {
        this.billKind = billKind;
    }

    public String getBillTypeUrl()
    {
        return billTypeUrl;
    }

    public void setBillTypeUrl(String billTypeUrl)
    {
        this.billTypeUrl = billTypeUrl;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getMemberCount()
    {
        return memberCount;
    }

    public void setMemberCount(int memberCount)
    {
        this.memberCount = memberCount;
    }

    public int getFloors()
    {
        return floors;
    }

    public void setFloors(int floors)
    {
        this.floors = floors;
    }

    public Double getAreas()
    {
        return areas;
    }

    public void setAreas(Double areas)
    {
        this.areas = areas;
    }

    public int getUserState()
    {
        return userState;
    }

    public void setUserState(int userState)
    {
        this.userState = userState;
    }

    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getContractNo()
    {
        return contractNo;
    }

    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }

    public int getSmsKind()
    {
        return smsKind;
    }

    public void setSmsKind(int smsKind)
    {
        this.smsKind = smsKind;
    }

    public int getCheckUser()
    {
        return checkUser;
    }

    public void setCheckUser(int checkUser)
    {
        this.checkUser = checkUser;
    }

    public String getCheckOrder()
    {
        return checkOrder;
    }

    public void setCheckOrder(String checkOrder)
    {
        this.checkOrder = checkOrder;
    }

    public String getUniId()
    {
        return uniId;
    }

    public void setUniId(String uniId)
    {
        this.uniId = uniId;
    }

    public String getMeterNo()
    {
        return meterNo;
    }

    public void setMeterNo(String meterNo)
    {
        this.meterNo = meterNo;
    }

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public int getManuId()
    {
        return manuId;
    }

    public void setManuId(int manuId)
    {
        this.manuId = manuId;
    }

    public String getManuName()
    {
        return manuName;
    }

    public void setManuName(String manuName)
    {
        this.manuName = manuName;
    }

    public String getMusterNo()
    {
        return musterNo;
    }

    public void setMusterNo(String musterNo)
    {
        this.musterNo = musterNo;
    }

    public String getRepNo()
    {
        return repNo;
    }

    public void setRepNo(String repNo)
    {
        this.repNo = repNo;
    }

    public int getRecNo()
    {
        return recNo;
    }

    public void setRecNo(int recNo)
    {
        this.recNo = recNo;
    }

    public int getComPort()
    {
        return comPort;
    }

    public void setComPort(int comPort)
    {
        this.comPort = comPort;
    }

    public int getBaud()
    {
        return baud;
    }

    public void setBaud(int baud)
    {
        this.baud = baud;
    }

    public String getConnPara()
    {
        return connPara;
    }

    public void setConnPara(String connPara)
    {
        this.connPara = connPara;
    }

    public int getMusterPtl()
    {
        return musterPtl;
    }

    public void setMusterPtl(int musterPtl)
    {
        this.musterPtl = musterPtl;
    }

    public int getMeterPtl()
    {
        return meterPtl;
    }

    public void setMeterPtl(int meterPtl)
    {
        this.meterPtl = meterPtl;
    }

    public int getBatch()
    {
        return batch;
    }

    public void setBatch(int batch)
    {
        this.batch = batch;
    }

    public int getDynameter()
    {
        return dynameter;
    }

    public void setDynameter(int dynameter)
    {
        this.dynameter = dynameter;
    }

    public int getAlarm1()
    {
        return alarm1;
    }

    public void setAlarm1(int alarm1)
    {
        this.alarm1 = alarm1;
    }

    public int getAlarm2()
    {
        return alarm2;
    }

    public void setAlarm2(int alarm2)
    {
        this.alarm2 = alarm2;
    }

    public int getDn()
    {
        return dn;
    }

    public void setDn(int dn)
    {
        this.dn = dn;
    }

    public Double getSpan()
    {
        return span;
    }

    public void setSpan(Double span)
    {
        this.span = span;
    }

    public String getSealNo()
    {
        return sealNo;
    }

    public void setSealNo(String sealNo)
    {
        this.sealNo = sealNo;
    }

    public String getSubNo()
    {
        return subNo;
    }

    public void setSubNo(String subNo)
    {
        this.subNo = subNo;
    }

    public String getSubName()
    {
        return subName;
    }

    public void setSubName(String subName)
    {
        this.subName = subName;
    }

    public int getMboxId()
    {
        return mboxId;
    }

    public void setMboxId(int mboxId)
    {
        this.mboxId = mboxId;
    }

    public String getMboxNo()
    {
        return mboxNo;
    }

    public void setMboxNo(String mboxNo)
    {
        this.mboxNo = mboxNo;
    }

    public int getPattern()
    {
        return pattern;
    }

    public void setPattern(int pattern)
    {
        this.pattern = pattern;
    }

    public int getOnState()
    {
        return onState;
    }

    public void setOnState(int onState)
    {
        this.onState = onState;
    }

    public int getKeyFlag()
    {
        return keyFlag;
    }

    public void setKeyFlag(int keyFlag)
    {
        this.keyFlag = keyFlag;
    }

    public Double getDosageSum()
    {
        return dosageSum;
    }

    public void setDosageSum(Double dosageSum)
    {
        this.dosageSum = dosageSum;
    }

    public String getReadDate()
    {
        return readDate;
    }

    public void setReadDate(String readDate)
    {
        this.readDate = readDate;
    }

    public int getSyncFlag()
    {
        return syncFlag;
    }

    public void setSyncFlag(int syncFlag)
    {
        this.syncFlag = syncFlag;
    }

    public int getTryFlag()
    {
        return tryFlag;
    }

    public void setTryFlag(int tryFlag)
    {
        this.tryFlag = tryFlag;
    }

    public int getUseFlag()
    {
        return useFlag;
    }

    public void setUseFlag(int useFlag)
    {
        this.useFlag = useFlag;
    }

    public Double getInitDosage()
    {
        return initDosage;
    }

    public void setInitDosage(Double initDosage)
    {
        this.initDosage = initDosage;
    }

    public Double getUseDosageSum()
    {
        return useDosageSum;
    }

    public void setUseDosageSum(Double useDosageSum)
    {
        this.useDosageSum = useDosageSum;
    }

    public Double getTransDosage()
    {
        return transDosage;
    }

    public void setTransDosage(Double transDosage)
    {
        this.transDosage = transDosage;
    }

    public Double getAllSum()
    {
        return allSum;
    }

    public void setAllSum(Double allSum)
    {
        this.allSum = allSum;
    }

    public Double getBuySum()
    {
        return buySum;
    }

    public void setBuySum(Double buySum)
    {
        this.buySum = buySum;
    }

    public Double getLeftSum()
    {
        return leftSum;
    }

    public void setLeftSum(Double leftSum)
    {
        this.leftSum = leftSum;
    }

    public int getInitSum()
    {
        return initSum;
    }

    public void setInitSum(int initSum)
    {
        this.initSum = initSum;
    }

    public int getErrFlag()
    {
        return errFlag;
    }

    public void setErrFlag(int errFlag)
    {
        this.errFlag = errFlag;
    }

    public Double getMaxDosage()
    {
        return maxDosage;
    }

    public void setMaxDosage(Double maxDosage)
    {
        this.maxDosage = maxDosage;
    }

    public int getVerifyDate()
    {
        return verifyDate;
    }

    public void setVerifyDate(int verifyDate)
    {
        this.verifyDate = verifyDate;
    }

    public int getSpanCnt()
    {
        return spanCnt;
    }

    public void setSpanCnt(int spanCnt)
    {
        this.spanCnt = spanCnt;
    }

    public String getMeterPic()
    {
        return meterPic;
    }

    public void setMeterPic(String meterPic)
    {
        this.meterPic = meterPic;
    }

    public String getFeeItemId()
    {
        return feeItemId;
    }

    public void setFeeItemId(String feeItemId)
    {
        this.feeItemId = feeItemId;
    }

    public String getFeeItemName()
    {
        return feeItemName;
    }

    public void setFeeItemName(String feeItemName)
    {
        this.feeItemName = feeItemName;
    }

    public int getSendType()
    {
        return sendType;
    }

    public void setSendType(int sendType)
    {
        this.sendType = sendType;
    }

    public String getSendDetail()
    {
        return sendDetail;
    }

    public void setSendDetail(String sendDetail)
    {
        this.sendDetail = sendDetail;
    }

    public String getSendDate()
    {
        return sendDate;
    }

    public void setSendDate(String sendDate)
    {
        this.sendDate = sendDate;
    }

    public int getManageFlag()
    {
        return manageFlag;
    }

    public void setManageFlag(int manageFlag)
    {
        this.manageFlag = manageFlag;
    }

    public String getManageDate()
    {
        return manageDate;
    }

    public void setManageDate(String manageDate)
    {
        this.manageDate = manageDate;
    }

    public int getManageCount()
    {
        return manageCount;
    }

    public void setManageCount(int manageCount)
    {
        this.manageCount = manageCount;
    }

    public int getFeeState()
    {
        return feeState;
    }

    public void setFeeState(int feeState)
    {
        this.feeState = feeState;
    }

    public int getFeeStyle()
    {
        return feeStyle;
    }

    public void setFeeStyle(int feeStyle)
    {
        this.feeStyle = feeStyle;
    }

    public String getYearDosageDt()
    {
        return yearDosageDt;
    }

    public void setYearDosageDt(String yearDosageDt)
    {
        this.yearDosageDt = yearDosageDt;
    }

    public String getFeeDate()
    {
        return feeDate;
    }

    public void setFeeDate(String feeDate)
    {
        this.feeDate = feeDate;
    }

    public int getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(int operatorId)
    {
        this.operatorId = operatorId;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public Double getYtotal()
    {
        return ytotal;
    }

    public void setYtotal(Double ytotal)
    {
        this.ytotal = ytotal;
    }

    public Double getYquantity()
    {
        return yquantity;
    }

    public void setYquantity(Double yquantity)
    {
        this.yquantity = yquantity;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public int getOnOff()
    {
        return onOff;
    }

    public void setOnOff(int onOff)
    {
        this.onOff = onOff;
    }

    public Double getThesdfprice()
    {
        return thesdfprice;
    }

    public void setThesdfprice(Double thesdfprice)
    {
        this.thesdfprice = thesdfprice;
    }

    public Double getThewsfprice()
    {
        return thewsfprice;
    }

    public void setThewsfprice(Double thewsfprice)
    {
        this.thewsfprice = thewsfprice;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public int getBuyCount()
    {
        return buyCount;
    }

    public void setBuyCount(int buyCount)
    {
        this.buyCount = buyCount;
    }

    public Double getV1()
    {
        return v1;
    }

    public void setV1(Double v1)
    {
        this.v1 = v1;
    }

    public Double getV2()
    {
        return v2;
    }

    public void setV2(Double v2)
    {
        this.v2 = v2;
    }

    public Double getV3()
    {
        return v3;
    }

    public void setV3(Double v3)
    {
        this.v3 = v3;
    }

    public Double getV4()
    {
        return v4;
    }

    public void setV4(Double v4)
    {
        this.v4 = v4;
    }

    public Double getScheduleBuysum()
    {
        return scheduleBuysum;
    }

    public void setScheduleBuysum(Double scheduleBuysum)
    {
        this.scheduleBuysum = scheduleBuysum;
    }

    public Double getScheduleLeftsum()
    {
        return scheduleLeftsum;
    }

    public void setScheduleLeftsum(Double scheduleLeftsum)
    {
        this.scheduleLeftsum = scheduleLeftsum;
    }

    public Double getStepTotal()
    {
        return stepTotal;
    }

    public void setStepTotal(Double stepTotal)
    {
        this.stepTotal = stepTotal;
    }

    public int getChargemethod()
    {
        return chargemethod;
    }

    public void setChargemethod(int chargemethod)
    {
        this.chargemethod = chargemethod;
    }

    public Double getChangeLeftsum()
    {
        return changeLeftsum;
    }

    public void setChangeLeftsum(Double changeLeftsum)
    {
        this.changeLeftsum = changeLeftsum;
    }

    public String getXsfeeItemId()
    {
        return xsfeeItemId;
    }

    public void setXsfeeItemId(String xsfeeItemId)
    {
        this.xsfeeItemId = xsfeeItemId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public int getShareFlag()
    {
        return shareFlag;
    }

    public void setShareFlag(int shareFlag)
    {
        this.shareFlag = shareFlag;
    }

    public Double getShareRatio()
    {
        return shareRatio;
    }

    public void setShareRatio(Double shareRatio)
    {
        this.shareRatio = shareRatio;
    }

    private int custId;
    private String custName;
    private String userId;
    private String userCode;
    private String userName;
    private String userAddr;
    private String areaId;
    private String areaName;
    private String areaListId;
    private int readOrder;
    private int userTypeId;
    private String userTypeName;
    private int billKind;
    private String billTypeUrl;
    private String mobilePhone;
    private String phone;
    private String email;
    private int memberCount;
    private int floors;
    private Double areas;
    private int userState;
    private String createDate;
    private String createUser;
    private String contractNo;
    private int smsKind;
    private int checkUser;
    private String checkOrder;
    private String uniId;
    private String meterNo;
    private int typeId;
    private String typeName;
    private int manuId;
    private String manuName;
    private String musterNo;
    private String repNo;
    private int recNo;
    private int comPort;
    private int baud;
    private String connPara;
    private int musterPtl;
    private int meterPtl;
    private int batch;
    private int dynameter;
    private int alarm1;
    private int alarm2;
    private int dn;
    private Double span;
    private String sealNo;
    private String subNo;
    private String subName;
    private int mboxId;
    private String mboxNo;
    private int pattern;
    private int onState;
    private int keyFlag;
    private Double dosageSum;
    private String readDate;
    private int syncFlag;
    private int tryFlag;
    private int useFlag;
    private Double initDosage;
    private Double useDosageSum;
    private Double transDosage;
    private Double allSum;
    private Double buySum;
    private Double leftSum;
    private int initSum;
    private int errFlag;
    private Double maxDosage;
    private int verifyDate;
    private int spanCnt;
    private String meterPic;
    private String feeItemId;
    private String feeItemName;
    private int feeState;
    private int feeStyle;
    private String yearDosageDt;
    private String feeDate;
    private int operatorId;
    private Double ytotal;
    private Double yquantity;
    private String price;
    private int sendType;
    private String sendDetail;
    private String sendDate;
    private int manageFlag;
    private String manageDate;
    private int manageCount;
    private String idCard;
    private int onOff;
    private Double thesdfprice;
    private Double thewsfprice;
    private String remark;
    private int buyCount;
    private Double v1;
    private Double v2;
    private Double v3;
    private Double v4;
    private Double scheduleBuysum;
    private Double scheduleLeftsum;
    private Double stepTotal;
    private int chargemethod;
    private Double changeLeftsum;
    private String xsfeeItemId;
    private String deviceId;
    private int shareFlag;
    private Double shareRatio;
}
