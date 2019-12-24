package com.wangchao.studyDemo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        try {
            Object o = IHelloService.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
