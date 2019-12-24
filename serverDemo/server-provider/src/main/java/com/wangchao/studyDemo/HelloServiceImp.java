package com.wangchao.studyDemo;

/**
 * 2019/12/24
 * by Wang Chao
 */
public class HelloServiceImp implements IHelloService {
    @Override
    public String sayHello(String flag) {
        System.out.println("client come in");
        return "hello "+flag;
    }

    @Override
    public String saveUser(String name) {
        System.out.println("client wants to save name");
        return "saved "+name;
    }
}
