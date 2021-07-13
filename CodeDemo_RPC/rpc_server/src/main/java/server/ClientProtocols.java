package server;

public interface ClientProtocols {
    long versionID = 1234L ;

    void mkdir(String path) ;

    void createFile(String name) ;
}
