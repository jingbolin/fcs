// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SendMail.java

package com.yinhe.ec.cpps.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Date;
import java.util.Properties;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            MailAuthenticator

public class SendMail
{

    public SendMail()
    {
        to = "";
        from = "";
        host = "";
        username = "";
        password = "";
        subject = "";
        content = "";
        filename = "";
    }

    public SendMail(String to, String from, String smtpServer, String username, String password, String subject, String content)
    {
        this.to = "";
        this.from = "";
        host = "";
        this.username = "";
        this.password = "";
        this.subject = "";
        this.content = "";
        filename = "";
        this.to = to;
        this.from = from;
        host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public void setUserName(String usn)
    {
        username = usn;
    }

    public void setPassWord(String pwd)
    {
        password = pwd;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String transferChinese(String strText)
    {
        try
        {
            strText = MimeUtility.encodeText(new String(strText.getBytes(), "gbk2312"), "gbk2312", "B");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return strText;
    }

    public boolean sendMail()
    {
        boolean sucflag = false;
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.126.com");
            props.put("mail.smtp.port", String.valueOf(25));
            props.put("mail.smtp.auth", "true");
            Transport transport = null;
            MailAuthenticator myauth = new MailAuthenticator("eappsys@126.com", "@eapp2017");
            Session session = Session.getDefaultInstance(props, myauth);
            session.setDebug(true);
            transport = session.getTransport("smtp");
            transport.connect("smtp.126.com", "\u7528\u7535\u81EA\u52A9\u67E5\u8BE2\u7CFB\u7EDF", "@eapp2017");
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            InternetAddress fromAddress = new InternetAddress("eappsys@126.com");
            msg.setFrom(fromAddress);
            InternetAddress toAddress[] = new InternetAddress[1];
            toAddress[0] = new InternetAddress(to);
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject);
            msg.setContent(content, "text/html;charset=utf8");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
            sucflag = true;
        }
        catch(NoSuchProviderException e)
        {
            e.printStackTrace();
        }
        catch(MessagingException e)
        {
            e.printStackTrace();
        }
        return sucflag;
    }

    public static void main(String args[])
    {
        SendMail sendmail = new SendMail();
        sendmail.setTo("251808026@qq.com");
        sendmail.setSubject("\u627E\u56DE\u80FD\u6E90\u901A\u767B\u5F55\u5BC6\u7801");
        sendmail.setContent("&nbsp;&nbsp;&nbsp;&nbsp;\u60A8\u597D\uFF0C\u8BF7\u70B9\u51FB  \u8FDB\u5165\u627E\u56DE\u5BC6\u7801\u9875\u9762,\u627E\u56DE\u5BC6\u7801\uFF01<br><br>&nbsp;&nbsp;&nbsp;&nbsp;\uFF08\u5982\u679C\u60A8\u65E0\u6CD5\u70B9\u51FB\u8FD9\u4E2A\u94FE\u63A5\uFF0C\u8BF7\u5C06\u6B64\u94FE\u63A5\u590D\u5236\u5230\u6D4F\u89C8\u5668\u5730\u5740\u680F\u540E\u8BBF\u95EE\uFF09<br>&nbsp;&nbsp;&nbsp;&nbsp;\u5982\u679C\u60A8\u8BEF\u6536\u5230\u6B64\u7535\u5B50\u90AE\u4EF6\uFF0C\u5219\u53EF\u80FD\u662F\u5176\u4ED6\u7528\u6237\u5728\u5C1D\u8BD5\u5E10\u53F7\u8BBE\u7F6E\u65F6\u7684\u8BEF\u64CD\u4F5C\uFF0C\u5982\u679C\u60A8\u5E76\u672A\u53D1\u8D77\u8BE5\u8BF7\u6C42\uFF0C\u5219\u65E0\u9700\u518D\u8FDB\u884C\u4EFB\u4F55\u64CD\u4F5C\uFF0C\u5E76\u53EF\u4EE5\u653E\u5FC3\u5730\u5FFD\u7565\u6B64\u7535\u5B50\u90AE\u4EF6\u3002<br>");
        sendmail.sendMail();
    }

    String to;
    String from;
    String host;
    String username;
    String password;
    String subject;
    String content;
    String filename;
}
