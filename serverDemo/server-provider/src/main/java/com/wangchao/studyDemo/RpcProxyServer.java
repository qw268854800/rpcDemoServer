package com.wangchao.studyDemo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2019/12/24
 * by Wang Chao
 */
public class RpcProxyServer {

    ExecutorService thirdPoll = Executors.newCachedThreadPool();

    public void publisher(Object service,int port){
        ServerSocket server = null;
        try {
            while (true){
                server = new ServerSocket(port);
                System.out.println("server start");
                Socket socket = server.accept();
                thirdPoll.execute(new ProcessorHandler(socket,service));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(server != null){
                    server.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
}
