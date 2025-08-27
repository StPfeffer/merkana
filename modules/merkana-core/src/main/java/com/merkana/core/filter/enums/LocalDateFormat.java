package com.merkana.core.filter.enums;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public enum LocalDateFormat {

    DDMMYY(DateTimeFormatter.ofPattern("dd/MM/yy")),
    DDMMYYYY(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
    DDMMYYHHMM(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")),
    DDMMYYYYHHMM(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
    DDMMYYYYHHMMSS(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
    YYYYMMDD(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
    YYYYMMDDHHMMSS(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
    YYYYMMDDTHHMMSS(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
    YYYYMMDDTHHMMSSSSSXXX(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")),
    FULLTIMESTAMP(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

    private final DateTimeFormatter formatter;

    LocalDateFormat(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

}

