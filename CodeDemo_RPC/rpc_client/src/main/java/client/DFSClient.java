package client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import server.ClientProtocols;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DFSClient {
    public static void main(String[] args) throws IOException {
        final ClientProtocols proxy = RPC.getProxy(//动态代理-->反射、序列化、非阻塞异步IO（NIO）
                                                   ClientProtocols.class,
                                                   1234L,
                                                   new InetSocketAddress("localhost", 9999),
                                                   new Configuration()

                                                  );

        proxy.mkdir("/usr/local");
        proxy.createFile("");
    }
}
