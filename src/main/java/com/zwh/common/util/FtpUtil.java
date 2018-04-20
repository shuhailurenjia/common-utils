package com.zwh.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpUtil {

	private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

	/**
	 * 获取ftp连接
	 * @param host	IP地址
	 * @param port	端口
	 * @param userName	用户名
	 * @param password	密码
	 * @param ftp	
	 * @return
	 * @throws Exception
	 */
	public static boolean connectFtp(String host, int port, String userName, String password, FTPClient ftp) throws Exception {
		boolean flag = true;
		int reply;
		try {
			ftp.connect(host, port);
			ftp.login(userName, password);
			ftp.enterLocalActiveMode();//主动模式
//			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return flag;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("FTP客户端连接出错！", e);
		}
		return flag;
	}

	/**
	 * 
	 * @param host
	 * @param port
	 * @param userName
	 * @param password
	 * @param remoteBaseDir
	 * @param localFile
	 */
	public static void uploadFtpFile(String host, int port, String userName, String password, String remoteBaseDir, String localFile) {
		FTPClient ftp = new FTPClient();
		try {
			FtpUtil.connectFtp(host, port, userName, password, ftp);
			
			logger.info("ftp channel opened and connected.");
			
			String pathTrans = pathTransform(localFile);
			File file = new File(pathTrans);
			pathTrans = pathTransform(remoteBaseDir);
			FtpUtil.upload(file,pathTrans,ftp);
			
			logger.info("File transfered successfully to host.");
			ftp.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					logger.info("ftp Channel exited.");
				} catch (IOException ioe) {
				}
			}
		}
	}
	
	/**
	 *  ftp 上传文件
	 * @param file	上传文件
	 * @param remoteBaseDir	上传路径
	 * @param ftp
	 * @throws Exception
	 */
	public static void upload(File file,String remoteBaseDir, FTPClient ftp) throws Exception {
		boolean changedir = ftp.changeWorkingDirectory(remoteBaseDir);
		if(changedir){
			upload(file,ftp);
		}else{
			logger.error("切换FTP目录不成功，目录名称："+remoteBaseDir);
		}
	}
	
	/**
	 * ftp上传文件
	 * @param f		上传文件
	 * @param ftp
	 * @throws Exception
	 */
	public static void upload(File f, FTPClient ftp) throws Exception {
		FileInputStream input = null;
		try {
			if (f.isDirectory()) {
				String pathTrans = pathTransform(f.getName());
				ftp.makeDirectory(pathTrans);
				ftp.changeWorkingDirectory(pathTrans);
				String[] files = f.list();
				for (String fstr : files) {
					File file1 = new File(f.getPath() + "/" + fstr);
					if (file1.isDirectory()) {
						upload(file1,ftp);
						ftp.changeToParentDirectory();
					} else {
						pathTrans = pathTransform(f.getPath() + "/" + fstr);
						File file2 = new File(pathTrans);
						input = new FileInputStream(file2);
						ftp.storeFile(file2.getName(), input);
						input.close();
					}
				}
			} else {
				String pathTrans = pathTransform(f.getPath());
				File file2 = new File(pathTrans);
				input = new FileInputStream(file2);
				ftp.storeFile(file2.getName(), input);
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("上传文件出错！", e);
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void downloadFolder(String host, int port, String userName, String password, String remoteBaseDir, String fileName, String localBaseDir) {
		FTPClient ftp = new FTPClient();
		try {
			startDown(host,port,userName,password,remoteBaseDir,fileName,localBaseDir,ftp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
					logger.info("ftp Channel exited.");
				} catch (IOException ioe) {
				}
			}
		}
	}
	
	/**
	 * 下载链接配置
	 * 
	 * @param f
	 * @param localBaseDir
	 *            本地目录
	 * @param remoteBaseDir
	 *            远程目录
	 * @throws Exception
	 */
	public static void startDown(String host, int port, String userName, String password, String remoteBaseDir,String fileName,String localBaseDir, FTPClient ftp) throws Exception {
		connectFtp(host, port, userName, password, ftp);
		logger.info("ftp channel opened and connected.");
		try {
			FTPFile[] files = null;
			String pathTrans = pathTransform(remoteBaseDir);
			boolean changedir = ftp.changeWorkingDirectory(pathTrans);
			if (changedir) {
				logger.info("切换FTP目录成功，目录名称："+pathTrans);
				ftp.setControlEncoding("UTF-8");
                ftp.configure(new FTPClientConfig("com.credithc.phb.common.util.UnixFTPEntryParser"));
				files = ftp.listFiles();
				for (int i = 0; i < files.length; i++) {
					try {
						if(!files[i].getName().equals(".") && !files[i].getName().equals("..") && fileName.equals(files[i].getName())){
							downloadFile(files[i], localBaseDir, fileName, remoteBaseDir, ftp);
							logger.info("文件名称："+files[i].getName());
							logger.info(fileName + "File transfered successfully to host.");
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("<" + files[i].getName() + ">下载失败");
					}
				}
			} else {
				logger.info("切换FTP目录不成功，目录名称："+remoteBaseDir);
			}
		} catch (Exception e) {
			e.printStackTrace();;
			logger.error("下载过程中出现异常");
		}

	}

	/**
	 * 
	 * 下载FTP文件 当你需要下载FTP文件的时候，调用此方法 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
	 * 
	 * @param ftpFile
	 * @param relativeLocalPath
	 * @param relativeRemotePath
	 */
	private static void downloadFile(FTPFile ftpFile, String relativeLocalPath, String fileName, String relativeRemotePath, FTPClient ftp) {
		if (ftpFile.isFile()) {
			if (ftpFile.getName().indexOf("?") == -1) {
				OutputStream outputStream = null;
				try {
					String pathTrans = pathTransform(relativeLocalPath);
					File locaFile = new File(pathTrans + ftpFile.getName());
					// 判断文件是否存在，存在则返回
					if (locaFile.exists()) {
						return;
					} else {
						FileUtils.createDirectory(pathTrans);
						outputStream = new FileOutputStream(pathTrans + ftpFile.getName());
						ftp.retrieveFile(ftpFile.getName(), outputStream);
						outputStream.flush();
						outputStream.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				} finally {
					try {
						if (outputStream != null) {
							outputStream.close();
						}
					} catch (IOException e) {
						logger.error("输出文件流异常");
					}
				}
			}
		} else {
			String newlocalRelatePath = relativeLocalPath + ftpFile.getName();
			String newRemote = new String(relativeRemotePath + ftpFile.getName().toString());
			File fl = new File(newlocalRelatePath);
			if (!fl.exists()) {
				fl.mkdirs();
			}
			try {
				newlocalRelatePath = newlocalRelatePath + '/';
				newRemote = newRemote + "/";
				String currentWorkDir = ftpFile.getName().toString();
				boolean changedir = ftp.changeWorkingDirectory(currentWorkDir);
				if (changedir) {
					FTPFile[] files = null;
					files = ftp.listFiles();
					for (int i = 0; i < files.length; i++) {
						if(!files[i].getName().equals(".") && !files[i].getName().equals("..") && fileName.equals(files[i].getName())){
							downloadFile(files[i], newlocalRelatePath, fileName, newRemote,ftp);
						}
					}
				}
				if (changedir) {
					ftp.changeToParentDirectory();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}
	
	/**
	 * 路径转换
	 * @param path
	 * @return
	 */
	public static String pathTransform(String path){
		String os= System.getProperty("os.name");
		if(os.indexOf("Windows") != -1){
			path = path;
		} else {
			path = path.replace("\\","/");
		}
		return path;
	}
	
//	public static void main(String[] args) throws Exception {
////		uploadFtpFile("10.150.20.166",21,"hcftp","hengchang2016","/opt/ftp/ZL/huicai","D:\\opt\\backup\\2016-03-17\\Client_51.xml");
////		downloadFolder("outftp.zhaopin.com",21,"credithchuicaitouzi_45582922","zp_20150811","/Resume/20160324","D:\\opt\\backup\\2016-03-24\\resume\\zl\\");
////		System.out.println("done");
//	}
}