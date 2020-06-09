package com.demo.hutool.crypt;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * @author Liam(003046)
 * @date 2019/8/21 下午3:03
 */
public class AESUtilDemo {

    public static void main(String args[]) throws Exception{
        String jsonStr = "{\n" +
                "  \"cabinetCode\": \"FC7555555\",\n" +
                "  \"appTime\": \"2018/05/27 20:56:00\",\n" +
                "  \"heartNum\": 2384923\n" +
                "}";

        String keyStr = "D4fg61^&pD0!@$)S";
        AES aes = SecureUtil.aes(keyStr.getBytes(Charset.forName("UTF-8")));
        byte[] result = aes.encrypt(jsonStr);

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/lee/Desktop/heart");
        fileOutputStream.write(result);
//        byte[] decrypt = aes.decrypt(result);
//        System.out.println("加密结果为：" + new String(result));
//        System.out.println("解密结果为：" + new String(decrypt));
    }
}
