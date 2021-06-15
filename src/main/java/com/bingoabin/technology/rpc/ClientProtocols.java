package com.bingoabin.technology.rpc;

//远程接口协议
public interface ClientProtocols {
	long versionID = 1234L ;
	void mkdir(String path);

	void createFile(String name);
}
