package com.wangchao.studyDemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 2019/12/24
 * by Wang Chao
 */
public class ProcessorHandler implements Runnable {
    private Socket socket;
    private Object service;
    public ProcessorHandler(Socket socket, Object service) {
        this.service = service;
        this.socket = socket;
    }
    private Object invoke(RpcRequest rpcRequest){
        Class<?> clazz = null;
        Object invoke = null;
        try {
            clazz = Class.forName(rpcRequest.getClazz());
            Class<?>[] typeClasses = new Class[rpcRequest.getArgs().length];
            for(int i=0;i<rpcRequest.getArgs().length;i++){
                typeClasses[i] = rpcRequest.getArgs()[i].getClass();
            }
            Method method = clazz.getMethod(rpcRequest.getMethod(),typeClasses);
            invoke = method.invoke(service,rpcRequest.getArgs());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }
    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            //反序列化
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object obj = this.invoke(rpcRequest);
            if(obj != null){
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(obj);
                outputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
                if (outputStream != null){
                    outputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
