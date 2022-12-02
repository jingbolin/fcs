// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AutoSendNbDataTask.java

package com.yinhe.ec.cpps.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            Tools

public class AutoSendNbDataTask
{

    public AutoSendNbDataTask()
    {
    }

    public static void start()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 9);
        calendar.set(12, 30);
        calendar.set(13, 0);
        Date time = calendar.getTime();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run()
            {
                System.out.println((new StringBuilder(String.valueOf(Tools.getNow()))).append("--->>Send NB-IOT Message start...").toString());
                String userStr0 = "";
                try
                {
                    URL url0 = new URL((new StringBuilder("http://127.0.0.1:8899/yqkj/ycmeter/getWillPostIotData?orders=&timeid=")).append(Math.random()).toString());
                    InputStream is0 = url0.openStream();
                    InputStreamReader isr0 = new InputStreamReader(is0, "UTF-8");
                    BufferedReader br0 = new BufferedReader(isr0);
                    for(String line0 = ""; (line0 = br0.readLine()) != null;)
                        userStr0 = line0;

                    is0.close();
                }
                catch(MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                System.out.println((new StringBuilder(String.valueOf(Tools.getNow()))).append("--->>Send NB-IOT Message end...").toString());
            }

        }
, time, 0x36ee80L);
    }
}
