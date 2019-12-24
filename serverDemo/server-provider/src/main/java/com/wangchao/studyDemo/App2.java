package com.wangchao.studyDemo;

/**
 * Hello world!
 *
 */
public class App2
{
    public static void main( String[] args )
    {
       IHelloService service = new HelloServiceImp();
       RpcProxyServer server = new RpcProxyServer();
       server.publisher(service,8080);
    }
}
