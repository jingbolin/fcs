// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JwtUtil.java

package com.yinhe.ec.cpps.util;

//import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.yinhe.ec.cpps.util:
//            Tools

public class JwtUtil
{

    public JwtUtil()
    {
    }

//    public static Claims parseJWT(String jwtStr)
//    {
//        try
//        {
//            Claims claims = (Claims)Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("xianPengniaokeji")).parseClaimsJws(jwtStr).getBody();
//            return claims;
//        }
//        catch(Exception ex)
//        {
//            System.out.println("Claims ERROR...");
//        }
//        return null;
//    }

    public static Map createJWT(String username, String subject)
    {
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//        byte apiKeySecretBytes[] = DatatypeConverter.parseBase64Binary("xianPengniaokeji");
//        java.security.Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//        JwtBuilder builder = Jwts.builder().setId(username).setIssuedAt(now).setSubject(subject).setIssuer("http://www.pn-iot.com").signWith(signatureAlgorithm, signingKey);
//        Date exp = new Date();
//        long expMillis = nowMillis + 0x1b7740L;
//        exp = new Date(expMillis);
//        builder.setExpiration(exp);
//        Map map = new HashMap();
//        map.put("jwt", builder.compact());
//        map.put("exp", Tools.formartTimeFromDatetime(exp));
//        return map;
        return null;
    }

    public static void main(String args[])
        throws ParseException
    {
        System.out.println((String)createJWT("admin", "\u6D4B\u8BD5\u6570\u636E").get("jwt"));
    }

    private static final long EXPIRE_TIME = 0x1b7740L;
    private static final String TOKEN_SECRET = "xianPengniaokeji";
    private static final String ISSUER = "http://www.pn-iot.com";
}
