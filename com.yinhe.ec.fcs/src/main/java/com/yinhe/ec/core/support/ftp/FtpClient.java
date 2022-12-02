package com.yinhe.ec.core.support.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.yinhe.ec.core.exception.FtpException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FTP上传下载
 */
public class FtpClient
{
	private static final byte[] LOCK = { 0 };
	private static final String FILELOCK = "Token.lock";
	private static FTPClient ftpClient = null;
	private Logger logger = LogManager.getLogger(FtpClient.class);
	private Properties properties = new Properties();

	public FtpClient()
	{
	}

	/**
	 * 初始化
	 * @param host
	 * IP
	 * @param port
	 * 端口
	 * @param username
	 * 用户名
	 * @param password
	 * 密码
	 * @throws FtpException
	 * e
	 */
	public FtpClient(String host, Integer port, String username, String password) throws FtpException
	{
		init(host, port, username, password);
	}

	// 执行文件下载
	public void excuteDownLoadFile(String host, Integer port, String username, String password, String localDnPath, String remotePath)
	{
		FtpClient ftpClient = new FtpClient(host, port, username, password);
		ftpClient.downLoadFile(remotePath, localDnPath);
		ftpClient.close();
	}

	// 获取本地路径所有文件
	public List<String> getLocalFiles(String localpath)
	{
		try
		{
			List<String> list = new ArrayList<>();

			File file = new File(localpath);
			File[] files = file.listFiles();
			if (files != null && files.length > 0)
			{
				for (File f : files)
				{
					String fileName = f.getName().toString();
					list.add(fileName);
				}
			}
			return list;
		}
		catch (Exception e)
		{
			logger.error("", e);
			throw new FtpException("FTP访问目录[" + localpath + "]出错!", e);
		}
	}

	/**
	 * FPT登录
	 */
	public void open()
	{
		init(properties.getProperty("FTPHOSTNAME"), Integer.valueOf(properties.getProperty("FTPPORT")), properties.getProperty("FTPUSERNAME"),
				properties.getProperty("FTPPASSWORD"));

	}

	/**
	 * 获取FTP连接
	 * @param server
	 * @param user
	 * @param password
	 * @throws FtpException
	 * e
	 */
	private void init(String host, int port, String username, String password) throws FtpException
	{
		synchronized (LOCK)
		{
			if (ftpClient == null)
			{
				ftpClient = new FTPClient();
			}
			try
			{
				ftpClient.connect(host, port);// 连接FTP服务器
			}
			catch (Exception e)
			{
				throw new FtpException("FTP[" + host + ":" + port + "]连接失败!", e);
			}
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
			{
				try
				{
					ftpClient.login(username, password);
				}
				catch (Exception e)
				{
					throw new FtpException("FTP用户[" + username + "]登陆失败!", e);
				}
			}
			else
			{
				throw new FtpException("FTP连接出错!");
			}
			logger.info("用户[" + username + "]登陆[" + host + "]成功.");
			properties.setProperty("userName", username);
			properties.setProperty("hostName", host);
			try
			{
				// 设置被动模式
				ftpClient.enterLocalPassiveMode();
				ftpClient.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			}
			catch (Exception e)
			{
				logger.error("", e);
				throw new FtpException("FTP初始化出错!", e);
			}
		}
	}

	/**
	 * 关闭FTP客户端
	 * @throws FtpException
	 * e
	 */
	public void close() throws FtpException
	{
		synchronized (LOCK)
		{
			try
			{
				ftpClient.logout();
			}
			catch (IOException e)
			{
				logger.error("", e);
				ftpClient = null;
				throw new FtpException("FTP退出登录出错!", e);
			}
			logger.info("用户[" + properties.getProperty("userName") + "]退出登录[" + properties.getProperty("hostName") + "].");
		}
	}

	/**
	 * 上传
	 * @param remotePath
	 * 上传目录
	 * @param localPath
	 * 本地目录
	 * @return boolean
	 * @throws FtpException
	 * e
	 */
	public boolean uploadFile(String remotePath, String localPath) throws FtpException
	{
		synchronized (LOCK)
		{
			File file = new File(localPath);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				if (!uploadFiles(files[i], remotePath))
				{
					return false;
				}
			}
			return files.length > 0;
		}
	}

	/**
	 * 递归上传文件
	 * @param localeFile
	 * 本地文件/目录
	 * @param remotePath
	 * 上传目录
	 * @return boolean
	 * @throws FtpException
	 * e
	 */
	public boolean uploadFiles(File localeFile, String remotePath) throws FtpException
	{
		synchronized (LOCK)
		{
			FileInputStream fis = null;
			try
			{
				if (localeFile.isDirectory())
				{
					boolean flag = false;
					ftpClient.makeDirectory(localeFile.getName());
					ftpClient.changeWorkingDirectory(localeFile.getName());
					logger.info("[" + localeFile.getAbsolutePath() + "]目录");
					File[] files = localeFile.listFiles();
					for (int i = 0; i < files.length; i++)
					{
						if (uploadFiles(files[i], remotePath + "/" + localeFile.getName()))
						{
							flag = true;
						}
						else
						{
							return false;
						}
					}
					ftpClient.changeToParentDirectory();// return parent
					// directory
					return flag;
				}
				else if (!FILELOCK.equals(localeFile.getName()))
				{
					fis = new FileInputStream(localeFile);
					ftpClient.storeFile(localeFile.getName(), fis);
					logger.info("[" + localeFile.getAbsolutePath() + "]上传成功!");
					return true;
				}
				return true;
			}
			catch (IOException e)
			{
				logger.error("", e);
				throw new FtpException("FTP上传[" + localeFile.getAbsolutePath() + "]出错!", e);
			}
			finally
			{
				if (fis != null)
				{
					try
					{
						fis.close();
					}
					catch (IOException e)
					{
					}
				}
			}
		}
	}

	/**
	 * 下载
	 * @param remotePath
	 * 下载目录
	 * @param localPath
	 * 本地目录
	 * @return boolean
	 * @throws FtpException
	 * e
	 */
	public boolean downLoadFile(String remotePath, String localPath) throws FtpException
	{
		synchronized (LOCK)
		{
			try
			{
				if (ftpClient.changeWorkingDirectory(remotePath))
				{// 转移到FTP服务器目录
					FTPFile[] files = ftpClient.listFiles();
					if (files.length > 0)
					{
						File localdir = new File(localPath);
						if (!localdir.exists())
						{
							localdir.mkdir();
						}
					}
					for (FTPFile ff : files)
					{
						if (!downLoadFile(ff, localPath))
						{
							return false;
						}
					}
					return files.length > 0;
				}
			}
			catch (IOException e)
			{
				logger.error("", e);
				throw new FtpException("FTP下载[" + localPath + "]出错!", e);
			}
			return false;
		}
	}

	/**
	 * 递归下载文件
	 * @param ftpFile
	 * 下载文件/目录
	 * @param localPath
	 * 本地目录
	 * @return boolean
	 */
	public boolean downLoadFile(FTPFile ftpFile, String localPath)
	{
		// 当前处理文件本地路径
		String fileLocalPath = localPath + "/" + ftpFile.getName();
		if (ftpFile.isFile())
		{// down file
			if (ftpFile.getName().indexOf("?") == -1)
			{
				try
				{
					File localFile = new File(fileLocalPath);
					if (!localFile.getParentFile().exists())
					{
						localFile.getParentFile().mkdirs();
					}
					List<String> localFiles = getLocalFiles(localFile.getParent());
					if (localFiles == null)
					{
						localFiles = new ArrayList<>();
					}
					if (!localFiles.contains(localFile.getName()))
					{
						OutputStream outputStream = new FileOutputStream(localFile);
						ftpClient.retrieveFile(ftpFile.getName(), outputStream);
						logger.info("[" + localFile.getAbsolutePath() + "]下载成功!");
						outputStream.flush();
						outputStream.close();
						return true;
					}
				}
				catch (Exception e)
				{
					logger.error("", e);
					throw new FtpException("FTP下载[" + fileLocalPath + "]出错!", e);
				}
				finally
				{

				}
			}
		}
		else
		{ // deal dirctory
			File file = new File(fileLocalPath);
			if (!file.exists())
			{
				file.mkdirs();
			}
			try
			{
				// enter relative workdirectory
				if (ftpClient.changeWorkingDirectory(ftpFile.getName()))
				{
					logger.info("[" + file.getAbsolutePath() + "]目录");
					FTPFile[] files = null;
					files = ftpClient.listFiles();
					for (int i = 0; i < files.length; i++)
					{
						downLoadFile(files[i], fileLocalPath);
					}
					ftpClient.changeToParentDirectory();// return parent
					// directory
					return true;
				}
			}
			catch (Exception e)
			{
				logger.error("", e);
				throw new FtpException("FTP下载[" + fileLocalPath + "]出错!", e);
			}
		}
		return false;
	}

	/**
	 * 获得目录下最大文件名
	 */
	public String getMaxFileName(String remotePath)
	{
		try
		{
			ftpClient.changeWorkingDirectory(remotePath);
			FTPFile[] files = ftpClient.listFiles();
			Arrays.sort(files, new Comparator<FTPFile>()
			{
				public int compare(FTPFile o1, FTPFile o2)
				{
					return o2.getName().compareTo(o1.getName());
				}
			});
			return files[0].getName();
		}
		catch (IOException e)
		{
			logger.error("", e);
			throw new FtpException("FTP访问目录[" + remotePath + "]出错!", e);
		}
	}

	/**
	 * 连接参数
	 * @param properties
	 * <br>
	 * FTPHOSTNAME:IP; FTPPORT:端口; FTPUSERNAME:用户名; FTPPASSWORD:密码
	 */
	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}

	/**
	 * 连接参数
	 * @param properties
	 * <br>
	 * FTPHOSTNAME:IP; FTPPORT:端口; FTPUSERNAME:用户名; FTPPASSWORD:密码
	 */
	public void setProperties(Map<String, String> properties)
	{
		this.properties = new Properties();
		String[] key = { "FTPHOSTNAME", "FTPPORT", "FTPUSERNAME", "FTPPASSWORD" };
		for (int i = 0; i < key.length; i++)
		{
			this.properties.put(key[i], properties.get(key[i]));
		}
	}

}