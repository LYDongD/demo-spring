package com.example.demo.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Liam(003046)
 * @date 2020/5/23 下午12:38
 */
@Slf4j
public class TraceUtil {

    public static void printStackTraceClass() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            log.info("call class: {}", stackTraceElement.getClassName());
        }
    }
}
