//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.system.dao.impl;

import com.yinhe.ec.cpps.system.dao.GenPkIdDao;
import com.yinhe.ec.cpps.util.ConstParam;
import com.yinhe.ec.cpps.util.Tools;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;

@Repository
@Transactional
public class GenPkIdDaoImpl implements GenPkIdDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public GenPkIdDaoImpl() {
    }

    public synchronized String getPkIdByTable(String tbName, String pkName) {
        int tmpId = 0;
        int count = this.jdbcTemplate.queryForObject("select count(" + pkName + ") from " + tbName + " where substr(" + pkName + ",1,8)='" + Tools.getForNRDay(0) + "'",Integer.class);
        if (count > 0) {
            if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                tmpId = this.jdbcTemplate.queryForObject("select * from (select substr(" + pkName + ",9,6) from " + tbName + " where substr(" + pkName + ",1,8)='" + Tools.getForNRDay(0) + "' order by " + pkName + " desc) where rownum=1",Integer.class);
            } else {
                tmpId = this.jdbcTemplate.queryForObject("select max( substring(" + pkName + ",9,6) ) from " + tbName + " where substring(" + pkName + ",1,8)='" + Tools.getForNRDay(0) + "' order by " + pkName + " desc",Integer.class);
            }

            int newId = tmpId + 1;
            if (newId < 10) {
                return Tools.getForNRDay(0) + "00000" + newId;
            } else if (newId < 100) {
                return Tools.getForNRDay(0) + "0000" + newId;
            } else if (newId < 1000) {
                return Tools.getForNRDay(0) + "000" + newId;
            } else if (newId < 10000) {
                return Tools.getForNRDay(0) + "00" + newId;
            } else {
                return newId < 100000 ? Tools.getForNRDay(0) + "0" + newId : Tools.getForNRDay(0) + newId;
            }
        } else {
            return Tools.getForNRDay(0) + "000001";
        }
    }

    public synchronized String getPkNoByTable(String tbName, String pkName, Integer n) {
        int tmpId;
        int i=0;
        String newValue = "";
        int count = this.jdbcTemplate.queryForObject("select count(" + pkName + ") from " + tbName + " ",Integer.class);
        int newId;
        if (count > 0) {
            if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                tmpId = this.jdbcTemplate.queryForObject("select * from (select " + pkName + " from " + tbName + " order by " + pkName + " desc) where rownum=1 ",Integer.class);
            } else {
                tmpId = this.jdbcTemplate.queryForObject("select max( " + pkName + " ) from " + tbName + " order by " + pkName + " desc",Integer.class);
            }

            newId = tmpId + 1;
            newValue = "" + newId;
            i = newValue.length();
            if (i < n) {
                for(int p = 0; p < n - i; ++p) {
                    newValue = "0" + newValue;
                }
            }
        } else {
            if (i < n) {
                for(newId = 0; newId < n - i - 1; ++newId) {
                    newValue = "0" + newValue;
                }
                newValue = newValue + "1";
            }
        }

        return newValue;
    }

    public synchronized String getPkNoByTable(String tbName, String pkName, String orders, Integer n) {
        int tmpId = 0;
        int i = 0;
        String newValue = "";
        int count = this.jdbcTemplate.queryForObject("select count(" + pkName + ") from " + tbName + " where 1=1 " + orders,Integer.class);
        int newId;
        if (count > 0) {
            if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                tmpId = this.jdbcTemplate.queryForObject("select * from (select " + pkName + " from " + tbName + " where 1=1 " + orders + "  order by " + pkName + " desc) where rownum=1 ",Integer.class);
            } else {
                tmpId = this.jdbcTemplate.queryForObject("select max( " + pkName + ") from " + tbName + " where 1=1 " + orders + " order by " + pkName + " desc",Integer.class);
            }

            newId = tmpId + 1;
            newValue = "" + newId;
            i = newValue.length();
            if (i < n) {
                for(int p = 0; p < n - i; ++p) {
                    newValue = "0" + newValue;
                }
            }
        } else {
            if (i < n) {
                for(newId = 0; newId < n - i - 1; ++newId) {
                    newValue = "0" + newValue;
                }

                newValue = newValue + "1";
            }
        }

        return newValue;
    }

    public synchronized int getPkNoForInt(String tbName, String pkName) {
        int tmpId = 0;
        int count = this.jdbcTemplate.queryForList("select * from " + tbName + " ").size();
        if (count > 0) {
            if ("ORACLE".equals(ConstParam.SQLTYPE)) {
                tmpId = this.jdbcTemplate.queryForList("select * from (select * from " + tbName + " order by " + pkName + " desc) where rownum=1 ").size();
            } else {
//                tmpId = this.jdbcTemplate.query("select max("+pkName+") from "+tbName, ResultSet::getInt);
                tmpId = this.jdbcTemplate.queryForObject("select max(" + pkName + ") from " + tbName, Integer.class);
            }
            return tmpId + 1;
        } else {
            return 1;
        }
    }
}
