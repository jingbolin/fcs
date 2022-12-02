// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Yq_SysloginResult.java

package com.yinhe.ec.cpps.model;


public class Yq_SysloginResult
{

    public Yq_SysloginResult()
    {
        code = 0;
        msg = "";
        token = "";
        expire = "";
        username = "";
        userPost = 0;
        communityName = "";
        userType = 5;
        communityId = 2;
        userId = 0;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getExpire()
    {
        return expire;
    }

    public void setExpire(String expire)
    {
        this.expire = expire;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getUserPost()
    {
        return userPost;
    }

    public void setUserPost(int userPost)
    {
        this.userPost = userPost;
    }

    public String getCommunityName()
    {
        return communityName;
    }

    public void setCommunityName(String communityName)
    {
        this.communityName = communityName;
    }

    public int getUserType()
    {
        return userType;
    }

    public void setUserType(int userType)
    {
        this.userType = userType;
    }

    public int getCommunityId()
    {
        return communityId;
    }

    public void setCommunityId(int communityId)
    {
        this.communityId = communityId;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    private int code;
    private String msg;
    private String token;
    private String expire;
    private String username;
    private int userPost;
    private String communityName;
    private int userType;
    private int communityId;
    private int userId;
}
