package com.bingoabin.sftp;

import com.jcraft.jsch.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author bingoabin
 * @date 2023/5/24 9:14
 * @Description:
 */
public class SFTPTest {
	public static void main(String[] args) {
		// downloadFiles("hadoop12","bingo","111111","/opt/module/hadoop3.1.3/etc/hadoop/core-site.xml","CodeDemo_Java/src/main/files/core-site.xml");
		// commandExec("hadoop12","bingo","111111","ls -l");
		commandMultiExec("hadoop12","bingo","111111","CodeDemo_Java/src/main/files/script.sh","/home/bingo/bin/script.sh");
	}

	public static void commandMultiExec(String hostname,String username,String password,String srcScriptPath,String destScriptPath){
		int port = 22;
		JSch jsch = new JSch();
		Session session = null;

		try {
			// 创建Session
			session = jsch.getSession(username, hostname, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();

			// 打开SFTP通道
			ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();

			// 上传脚本文件
			channelSftp.put(srcScriptPath, destScriptPath);

			// 打开执行命令的通道
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

			//解决windows环境下创建文件后，在Windows系统上使用了不兼容的换行符格式（CRLF，即回车换行），而不是Unix/Linux系统上的正确换行符格式（LF，即换行）。
			// channelExec.setCommand("sed -i 's/\\r$//' " + destScriptPath);

			// 指定要执行的脚本文件
			channelExec.setCommand("sed -i 's/\\r$//' " + destScriptPath + " && " + "chmod +x " + destScriptPath + " && " + destScriptPath);

			// 获取命令的输出流
			InputStream in = channelExec.getInputStream();

			// 执行命令
			channelExec.connect();

			// 读取命令的输出结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			StringBuilder output = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			// 关闭通道和会话
			channelExec.disconnect();
			channelSftp.disconnect();
			session.disconnect();

			// 输出命令的执行结果
			System.out.println("Command output:\n" + output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void commandExec(String hostname,String username,String password,String command){
		int port = 22;

		JSch jsch = new JSch();
		Session session = null;

		try {
			// 创建Session
			session = jsch.getSession(username, hostname, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();

			// 打开执行命令的通道
			ChannelExec channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);

			// 获取命令的输出流
			InputStream in = channel.getInputStream();

			// 执行命令
			channel.connect();

			// 读取命令的输出结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			StringBuilder output = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			// 关闭通道和会话
			channel.disconnect();
			session.disconnect();

			// 输出命令的执行结果
			System.out.println("Command output:\n" + output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadFiles(String hostname,String username,String password,String remoteFilePath,String localFilePath){
		int port = 22;
		JSch jsch = new JSch();
		Session session = null;
		ChannelSftp channelSftp = null;

		try {
			// 创建Session
			session = jsch.getSession(username, hostname, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();

			// 打开SFTP通道
			Channel channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;

			// 下载文件
			channelSftp.get(remoteFilePath, localFilePath);
			System.out.println("文件下载成功！");

			// 上传文件
			// channelSftp.put(localFilePath, remoteFilePath);
			// System.out.println("文件上传成功！");
		} catch (JSchException | SftpException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			if (channelSftp != null) {
				channelSftp.exit();
			}
			if (session != null) {
				session.disconnect();
			}
		}
	}
}
