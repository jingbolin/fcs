package com.yinhe.ec.cpps.sys.web;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yinhe.ec.cpps.SolarDateLock;
import com.yinhe.ec.cpps.sys.model.ConfList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.yinhe.ec.core.base.AbstractController;
import com.yinhe.ec.core.util.Builder;
import com.yinhe.ec.core.util.ConfigEnum;
import com.yinhe.ec.core.util.PropertiesUtil;
import com.yinhe.ec.cpps.rtdp.scheduled.SynchronizationDateTimeScheduled;
import com.yinhe.ec.cpps.sys.service.ConfListService;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户表
 * @author Administrator
 * @date 2020-01-13 16:14:04
 */
@Api("工程操作")
@Controller
@RequestMapping("/sys/project")
public class ProjectOperationController extends AbstractController {

    private ApplicationHome h = new ApplicationHome(Object.class.getClass());
    private String servicePath = h.toString();
    private String solarPath = FileUtil.getParent(servicePath, 1);
    
    private String tempPath = solarPath + PropertiesUtil.getString("solar.tempPath");
    private String svgPath = solarPath + PropertiesUtil.getString("solar.svg");
    private String solarVoice = solarPath + PropertiesUtil.getString("solar.voice");
    
    // private String importZipTempPath = servicePath + "import.zip";
    private String exportZipTempPath = solarPath + "/export.zip";
    
    private String importSvgTempPath = tempPath + "/svg/";
    private String importSqlTempPath = tempPath + "/tbea_solar.sql";
    private String exportSqlTempPath = tempPath + "/tbea_solar.sql";
    
    private String tbeaSolar = "tbea_solar";
    
    private LRUCache<String, Future<Boolean>> importResultCache = CacheUtil.newLRUCache(16);
    
    @Autowired
    private ConfListService confListService;
    
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired(required = false)
    private SynchronizationDateTimeScheduled dateTimeScheduled;
    
    @PostMapping(value = "/importProject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "工程导入功能", notes = "工程导入功能")
    public Object importProject(MultipartFile file, HttpSession session) throws Exception {
        InputStream is = file.getInputStream();
        importResultCache.put(session.getId(), threadPoolTaskExecutor.submit(() -> this.importProjectExecutor(is)));
        return setSuccessModelMap();
    }

    @PostMapping(value = "/importProjectResult", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "工程导入功能是否完成", notes = "工程导入功能是否完成")
    public Object importProjectResult(HttpSession session) throws Exception {
        Future<Boolean> future = importResultCache.get(session.getId());
        return setSuccessModelMap(future.isDone() && future.get());
    }

    private boolean importProjectExecutor(InputStream is) {
        synchronized(SolarDateLock.class) {
            File tempFolder = FileUtil.mkdir(tempPath);
            FileUtil.clean(tempFolder);
            // 解压文件到临时目录 并把svg文件放到目标文件夹中
            ZipUtil.unzip(is, tempFolder, CharsetUtil.defaultCharset());
            FileUtil.copy(importSvgTempPath, solarPath, true);
            if (FileUtil.exist(importSqlTempPath)) {
                // Windows
                if (SystemUtil.getOsInfo().isWindows()) {
                    // 删除和创建数据库
                    String createCmd = "mysql -e " + "\"DROP DATABASE IF EXISTS " + tbeaSolar + " ; CREATE DATABASE IF NOT EXISTS " + tbeaSolar + " CHARACTER SET utf8;\"";
                    String createResult = this.windowsExecForStr(createCmd);
                    logger.info(createResult);

                    // 导入工程数据
                    String importCmd = "mysql tbea_solar < " + importSqlTempPath;
                    String importResult = this.windowsExecForStr(importCmd);
                    logger.info(importResult);
                }
                // Linux
                if (SystemUtil.getOsInfo().isLinux()) {
                    // 删除和创建数据库
                    String createCmd = "mysql -e " + "\"DROP DATABASE IF EXISTS " + tbeaSolar + " ; CREATE DATABASE IF NOT EXISTS " + tbeaSolar + " CHARACTER SET utf8;\"";                    
                    String createResult = this.linuxExecForStr(createCmd);
                    logger.info(createResult);

                    // 导入工程数据
                    String importCmd = "mysql tbea_solar < " + importSqlTempPath;
                    String importResult = this.linuxExecForStr(importCmd);
                    logger.info(importResult);
                }
            }
            return true;
        }
    }

    @PostMapping(value = "/exportProject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "工程备份功能", notes = "工程备份功能")
    public void exportProject(HttpServletResponse response) throws Exception {
        synchronized(SolarDateLock.class) {
            FileUtil.mkdir(svgPath);
            FileUtil.mkdir(tempPath);
            FileUtil.clean(tempPath);
            FileUtil.copy(svgPath, tempPath, true);
            // Windows
            if (SystemUtil.getOsInfo().isWindows()) {
                // 导出tbea_solar表数据
                String cmd = "mysqldump " + tbeaSolar + " -r " + exportSqlTempPath;
                String result = this.windowsExecForStr(cmd);
                logger.info(result);
            }
            // Linux
            if (SystemUtil.getOsInfo().isLinux()) {
                // 导出tbea_solar表数据
                String cmd = "mysqldump " + tbeaSolar + " > " + exportSqlTempPath;
                String result = this.linuxExecForStr(cmd);
                logger.info(result);
            }
            // 压缩文件
            File zip = ZipUtil.zip(tempPath, exportZipTempPath);
            // 下载工程数据
            FileUtil.writeToStream(zip, response.getOutputStream());
        }
    }

    @PostMapping(value = "/restartProject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "重启监控系统", notes = "重启监控系统")
    public Object restartProject() {
        System.exit(0);
        return setSuccessModelMap();
    }

    @PostMapping(value = "/slaveOnlineCheck", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "备机通讯检测", notes = "备机通讯检测")
    public Object slaveOnlineCheck() throws Exception {
        // IP格式校验正则
        String reg = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
        // 查询备机IP
        ConfList confList = Builder.of(ConfList::new).with(ConfList::setConfListCode, ConfigEnum.IP_OF_SLAVE.name()).build();
        List<ConfList> confLists = confListService.queryConfListList(confList);
        if (confLists.isEmpty() || !ReUtil.isMatch(reg, confLists.get(0).getConfListValue())) {
            throw new RuntimeException("备机IP配置错误!");
        }
        // root用户下使用ping、其他情况下使用telnet端口7（一般不通）
        if (NetUtil.ping(confLists.get(0).getConfListValue())) {
            return setSuccessModelMap();
        }
        throw new RuntimeException("通讯异常!");
    }

    @PostMapping(value = "/queryVioce", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "查询音频文件", notes = "查询音频文件")
    public Object queryVioce(@RequestBody String[] suffixs) throws Exception {
        JSONObject json = JSONUtil.createObj();
        IntStream.range(0, 5).forEach(index -> {
            JSONArray jsonArray = JSONUtil.createArray();
            String folderPath = solarVoice + index;
            File folder = FileUtil.mkdir(folderPath);
            Arrays.stream(folder.listFiles())
                .map(File::getName)
                .filter(fileName -> StrUtil.endWithAny(fileName, suffixs))
                .forEach(jsonArray::add);
            json.set(String.valueOf(index), jsonArray);
        });
        return setSuccessModelMap(json);
    }

    @PostMapping(value = "/downloadVioce", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "下载音频文件", notes = "下载音频文件")
    public void downloadVioce(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String body = ServletUtil.getBody(request);
        JSONObject json = JSONUtil.parseObj(body);
        String filePath = json.getStr("filePath");
        FileUtil.writeToStream(solarVoice + filePath, response.getOutputStream());
    }

    @PostMapping(value = "/synDateTime", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "手动同步时间", notes = "手动同步时间")
    public Object synDateTime() throws Exception {
        Optional.ofNullable(dateTimeScheduled).ifPresent(service -> {
            service.synDateTime();
        });
        return setSuccessModelMap();
    }

    private String windowsExecForStr(String cmd) {
        return RuntimeUtil.execForStr("cmd", "/c", cmd);
    }

    private String linuxExecForStr(String cmd) {
        return RuntimeUtil.execForStr("/bin/sh", "-c", cmd);
    }
}
