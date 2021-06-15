package com.bingoabin.technology.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class NameNodeRpcServer implements ClientProtocols {
	public void mkdir(String path) {
		System.out.println("当前服务端接收到命令，创建文件夹:" + path);
	}

	public void createFile(String name) {

	}

	public static void main(String[] args) throws IOException {
		final RPC.Server server = new RPC.Builder(new Configuration())
				.setBindAddress("localhost")
				.setPort(9999)
				.setProtocol(ClientProtocols.class)
				.setInstance(new NameNodeRpcServer())
				.build();
		//启动服务端
		server.start();
	}
}
