//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.ycmeter.dao.GenerateExcelDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@Repository
@Transactional
public class GenerateExcelDaoImpl implements GenerateExcelDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public GenerateExcelDaoImpl() {
    }

    public Vector<HashMap> getExcelInfo(String sql) {
        Vector<HashMap> vector = new Vector();
        List list = this.jdbcTemplate.query(sql, new RowMapper() {
            public Object mapRow(ResultSet rs, int index) throws SQLException {
                ResultSetMetaData rsm = rs.getMetaData();
                int count = rsm.getColumnCount();
                HashMap<String, String> map = new HashMap();

                for(int i = 0; i < count; ++i) {
                    String columnName = rsm.getColumnName(i + 1);
                    map.put(columnName.toLowerCase(), rs.getString(columnName));
                }

                return map;
            }
        });
        Iterator it = list.iterator();

        while(it.hasNext()) {
            HashMap hasmap = (HashMap)it.next();
            vector.add(hasmap);
        }

        return vector;
    }
}
