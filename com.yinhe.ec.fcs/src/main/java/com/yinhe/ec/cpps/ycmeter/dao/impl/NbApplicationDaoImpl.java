//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yinhe.ec.cpps.ycmeter.dao.impl;

import com.yinhe.ec.cpps.model.Nb_Application;
import com.yinhe.ec.cpps.model.Nb_MeterParameter;
import com.yinhe.ec.cpps.model.Nb_MeterProperty;
import com.yinhe.ec.cpps.model.Nb_MeterService;
import com.yinhe.ec.cpps.util.Tools;
import com.yinhe.ec.cpps.util.UUIDUtil;
import com.yinhe.ec.cpps.ycmeter.dao.NbApplicationDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class NbApplicationDaoImpl implements NbApplicationDAO {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public NbApplicationDaoImpl() {
    }

    public List<Nb_Application> getNbApplication() {
        String sql = "select * from NB_APPLICATION order by createDate";
        return this.jdbcTemplate.query(sql, new ApplicationDTO());
    }

    public void updateAccessToken(String appId, String accessToken) {
        String sql = "update NB_APPLICATION set accessToken='" + accessToken + "',accessTime='" + Tools.getNow() + "' where appId='" + appId + "'";
        this.jdbcTemplate.update(sql);
    }

    public void addApplicationMgr(final Nb_Application application) throws DataAccessException {
        String sql = "insert into NB_APPLICATION(appId,appName,secret,appuseFlag,selfcertPath,selfcertPwd,trustcaPath,trustcaPwd,callbackUrl,createDate) values(?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, application.getAppId());
                ps.setString(2, application.getAppName());
                ps.setString(3, application.getSecret());
                ps.setInt(4, application.getAppuseFlag());
                ps.setString(5, application.getSelfcertPath());
                ps.setString(6, application.getSelfcertPwd());
                ps.setString(7, application.getTrustcaPath());
                ps.setString(8, application.getTrustcaPwd());
                ps.setString(9, application.getCallbackUrl());
                ps.setString(10, Tools.getNow());
            }
        });
    }

    public void editApplicationMgr(final Nb_Application application) throws DataAccessException {
        String sql = "update NB_APPLICATION set appName=?,secret=?,appuseFlag=?,selfcertPath=?,selfcertPwd=?,trustcaPath=?,trustcaPwd=?,callbackUrl=? where appId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, application.getAppName());
                ps.setString(2, application.getSecret());
                ps.setInt(3, application.getAppuseFlag());
                ps.setString(4, application.getSelfcertPath());
                ps.setString(5, application.getSelfcertPwd());
                ps.setString(6, application.getTrustcaPath());
                ps.setString(7, application.getTrustcaPwd());
                ps.setString(8, application.getCallbackUrl());
                ps.setString(9, application.getAppId());
            }
        });
    }

    public void delApplicationMgr(String appId) throws DataAccessException {
        String sql = "delete from NB_APPLICATION where appId='" + appId + "'";
        this.jdbcTemplate.update(sql);
    }

    public List<Nb_MeterParameter> getNbMeterParameter(int custId) {
        String sql = "select * from Nb_MeterParameter order by createDate";
        return this.jdbcTemplate.query(sql, new MeterParameterDTO());
    }

    public void addNbMeterParameter(final Nb_MeterParameter meterParameter) throws DataAccessException {
        String sql = "insert into Nb_MeterParameter(projectId,custId,manufacturerId,manufacturerName,deviceModel,deviceType,produceDesc,protocolType,useFlag,createDate,appId,manuCode) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, UUIDUtil.getUUID());
                ps.setInt(2, meterParameter.getCustId());
                ps.setString(3, meterParameter.getManufacturerId());
                ps.setString(4, meterParameter.getManufacturerName());
                ps.setString(5, meterParameter.getDeviceModel());
                ps.setString(6, meterParameter.getDeviceType());
                ps.setString(7, meterParameter.getProduceDesc());
                ps.setString(8, meterParameter.getProtocolType());
                ps.setInt(9, meterParameter.getUseFlag());
                ps.setString(10, Tools.getNow());
                ps.setString(11, meterParameter.getAppId());
                ps.setString(12, meterParameter.getManuCode());
            }
        });
    }

    public void editNbMeterParameter(final Nb_MeterParameter meterParameter) throws DataAccessException {
        String sql = "update Nb_MeterParameter set manufacturerId=?,manufacturerName=?,deviceModel=?,deviceType=?,produceDesc=?,protocolType=?,useFlag=?,appId=?,manuCode=? where projectId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, meterParameter.getManufacturerId());
                ps.setString(2, meterParameter.getManufacturerName());
                ps.setString(3, meterParameter.getDeviceModel());
                ps.setString(4, meterParameter.getDeviceType());
                ps.setString(5, meterParameter.getProduceDesc());
                ps.setString(6, meterParameter.getProtocolType());
                ps.setInt(7, meterParameter.getUseFlag());
                ps.setString(8, meterParameter.getAppId());
                ps.setString(9, meterParameter.getManuCode());
                ps.setString(10, meterParameter.getProjectId());
            }
        });
    }

    public void delNbMeterParameter(String projectId) throws DataAccessException {
        String sql = "delete from Nb_MeterParameter where projectId='" + projectId + "'";
        this.jdbcTemplate.update(sql);
    }

    public Nb_Application getNbApplicationByappId(String appId) {
        String sql = "select * from NB_APPLICATION where appId='" + appId + "'";
        List<Nb_Application> list = this.jdbcTemplate.query(sql, new ApplicationDTO());
        return list.size() > 0 ? (Nb_Application)list.get(0) : new Nb_Application();
    }

    public int getManuCodeCount(String manuCode) {
        String sql = "select count(*) from Nb_MeterParameter where manuCode='" + manuCode + "'";
        return (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public Nb_MeterParameter getNbMeterParameterByManuCode(String manuCode) {
        String sql = "select * from Nb_MeterParameter where manuCode='" + manuCode + "'";
        List<Nb_MeterParameter> list = this.jdbcTemplate.query(sql, new MeterParameterDTO());
        return list.size() > 0 ? (Nb_MeterParameter)list.get(0) : new Nb_MeterParameter();
    }

    public List<Nb_MeterService> getNbMeterService() {
        String sql = "select * from NB_METERSERVICE order by createDate";
        return this.jdbcTemplate.query(sql, new MeterServiceDTO());
    }

    public void addNbMeterService(final Nb_MeterService nbMeterService) {
        String sql = "insert into NB_METERSERVICE(manuCode,serviceId,serviceDesc,createDate,methodName,serviceType) values (?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterService.getManuCode());
                ps.setString(2, nbMeterService.getServiceId());
                ps.setString(3, nbMeterService.getServiceDesc());
                ps.setString(4, Tools.getNow());
                ps.setString(5, nbMeterService.getMethodName());
                ps.setInt(6, nbMeterService.getServiceType());
            }
        });
    }

    public void editNbMeterService(final Nb_MeterService nbMeterService) {
        String sql = "update NB_METERSERVICE set serviceDesc=?,createDate=?,manuCode=?,methodName=?,serviceType=? where serviceId=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterService.getServiceDesc());
                ps.setString(2, Tools.getNow());
                ps.setString(3, nbMeterService.getManuCode());
                ps.setString(4, nbMeterService.getMethodName());
                ps.setInt(5, nbMeterService.getServiceType());
                ps.setString(6, nbMeterService.getServiceId());
            }
        });
    }

    public void delNbMeterService(String serviceId) {
        String sql = "delete from NB_METERSERVICE where serviceId='" + serviceId + "'";
        this.jdbcTemplate.update(sql);
    }

    public List<Nb_MeterProperty> getNbmeterProperty(String serviceId) {
        String sql = "select * from Nb_MeterProperty where serviceId='" + serviceId + "' order by createDate";
        return this.jdbcTemplate.query(sql, new NbmeterPropertyDTO());
    }

    public void addNbmeterProperty(final Nb_MeterProperty nbMeterProperty) {
        String sql = "insert into Nb_MeterProperty(serviceId,propertyId,propertyDatatype,propertyDatalength,propertyDataunit,propertyIsnull,propertyType,createDate) values (?,?,?,?,?,?,?,?)";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterProperty.getServiceId());
                ps.setString(2, nbMeterProperty.getPropertyId());
                ps.setString(3, nbMeterProperty.getPropertyDatatype());
                ps.setInt(4, nbMeterProperty.getPropertyDatalength());
                ps.setString(5, nbMeterProperty.getPropertyDataunit());
                ps.setInt(6, nbMeterProperty.getPropertyIsnull());
                ps.setInt(7, nbMeterProperty.getPropertyType());
                ps.setString(8, Tools.getNow());
            }
        });
    }

    public void editNbmeterProperty(final Nb_MeterProperty nbMeterProperty) {
        String sql = "update Nb_MeterProperty set serviceId=?,propertyDatatype=?,propertyDatalength=?,propertyDataunit=?,propertyIsnull=?,createDate=? where propertyId=? and propertyType=?";
        this.jdbcTemplate.update(sql, new PreparedStatementSetter() {
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, nbMeterProperty.getServiceId());
                ps.setString(2, nbMeterProperty.getPropertyDatatype());
                ps.setInt(3, nbMeterProperty.getPropertyDatalength());
                ps.setString(4, nbMeterProperty.getPropertyDataunit());
                ps.setInt(5, nbMeterProperty.getPropertyIsnull());
                ps.setString(6, Tools.getNow());
                ps.setString(7, nbMeterProperty.getPropertyId());
                ps.setInt(8, nbMeterProperty.getPropertyType());
            }
        });
    }

    public void delNbmeterProperty(String propertyId) {
        String sql = "delete from Nb_MeterProperty where propertyId='" + propertyId + "'";
        this.jdbcTemplate.update(sql);
    }

    public int getNbmeterPropertyCount(String serviceId) {
        String sql = "select count(*) from Nb_MeterProperty where serviceId='" + serviceId + "'";
        return (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public class ApplicationDTO implements RowMapper {
        public ApplicationDTO() {
        }

        public Object mapRow(ResultSet rs, int count) throws SQLException {
            Nb_Application dto = new Nb_Application();
            dto.setAppId(rs.getString("appId"));
            dto.setAppName(rs.getString("appName"));
            dto.setSecret(rs.getString("secret"));
            dto.setAppuseFlag(rs.getInt("appuseFlag"));
            dto.setSelfcertPath(rs.getString("selfcertPath"));
            dto.setSelfcertPwd(rs.getString("selfcertPwd"));
            dto.setTrustcaPath(rs.getString("trustcaPath"));
            dto.setTrustcaPwd(rs.getString("trustcaPwd"));
            dto.setCallbackUrl(rs.getString("callbackUrl"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setAccessTime(rs.getString("accessTime") == null ? "" : rs.getString("accessTime"));
            dto.setAccessToken(rs.getString("accessToken") == null ? "" : rs.getString("accessToken"));
            return dto;
        }
    }

    public class MeterParameterDTO implements RowMapper {
        public MeterParameterDTO() {
        }

        public Object mapRow(ResultSet rs, int count) throws SQLException {
            Nb_MeterParameter dto = new Nb_MeterParameter();
            dto.setProjectId(rs.getString("projectId"));
            dto.setCustId(rs.getInt("custId"));
            dto.setManufacturerId(rs.getString("manufacturerId"));
            dto.setManufacturerName(rs.getString("manufacturerName"));
            dto.setDeviceModel(rs.getString("deviceModel"));
            dto.setDeviceType(rs.getString("deviceType"));
            dto.setProduceDesc(rs.getString("produceDesc"));
            dto.setProtocolType(rs.getString("protocolType"));
            dto.setUseFlag(rs.getInt("useFlag"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setAppId(rs.getString("appId"));
            dto.setManuCode(rs.getString("manuCode"));
            dto.setApplication(NbApplicationDaoImpl.this.getNbApplicationByappId(rs.getString("appId")));
            return dto;
        }
    }

    public class MeterServiceDTO implements RowMapper {
        public MeterServiceDTO() {
        }

        public Object mapRow(ResultSet rs, int count) throws SQLException {
            Nb_MeterService dto = new Nb_MeterService();
            dto.setManuCode(rs.getString("manuCode"));
            dto.setServiceId(rs.getString("serviceId"));
            dto.setServiceDesc(rs.getString("serviceDesc"));
            dto.setCreateDate(rs.getString("createDate"));
            dto.setMethodName(rs.getString("methodName"));
            dto.setServiceType(rs.getInt("serviceType"));
            return dto;
        }
    }

    public class NbmeterPropertyDTO implements RowMapper {
        public NbmeterPropertyDTO() {
        }

        public Object mapRow(ResultSet rs, int count) throws SQLException {
            Nb_MeterProperty dto = new Nb_MeterProperty();
            dto.setServiceId(rs.getString("serviceId"));
            dto.setPropertyId(rs.getString("propertyId"));
            dto.setPropertyDatatype(rs.getString("propertyDatatype"));
            dto.setPropertyDatalength(rs.getInt("propertyDatalength"));
            dto.setPropertyDataunit(rs.getString("propertyDataunit"));
            dto.setPropertyIsnull(rs.getInt("propertyIsnull"));
            dto.setPropertyType(rs.getInt("propertyType"));
            dto.setCreateDate(rs.getString("createDate"));
            return dto;
        }
    }
}
