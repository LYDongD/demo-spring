package com.example.demo.exception;

/**
 * jvm是如何处理异常的
 * @author Liam(003046)
 * @date 2020/6/25 上午9:27
 */
public class ExceptionDemo {

    private Integer tryBlock;

    private Integer catchBlock;

    private Integer finallyBlock;

    public void exceptionHandle(){
        try {
            tryBlock = 1;
        }catch (ArrayIndexOutOfBoundsException ae){
            catchBlock = 2;
        }finally {
            finallyBlock = 3;
        }
    }
}
