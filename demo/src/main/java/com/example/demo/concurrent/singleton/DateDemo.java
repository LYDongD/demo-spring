package com.example.demo.concurrent.singleton;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Date;

import static java.time.temporal.ChronoField.MICRO_OF_SECOND;


/**
 *  thread safe date formation
 */
@Slf4j
public class DateDemo {

    public static void main(String[] args) {
        Instant date = Instant.now();
        Long now = System.currentTimeMillis();
        log.info(date.toString());
        log.info(now + "");

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatter("YYYY-MM-DD HH:mm:ss");

    }
}
