package com.cepardov.cms.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final Logger LOGGER = LogManager.getLogger(DateUtil.class.getName());

    public Timestamp getDateTimeFromNtpServer() {
        NtpUtil ntpUtil = new NtpUtil();
        if (ntpUtil.getNtpServerDateTime() != null) {
            return Timestamp.valueOf(ntpUtil.getNtpServerDateTime());
        } else {
            LOGGER.warn("Error al obtener la fecha desde servidor NTP, retornando fecha de sistema.");
            return Timestamp.valueOf(LocalDateTime.now());
        }
    }

    /**
     * Obtiene una fecha de un servidor NTP y la trata para resultar en una fecha SQL
     *
     * @return sql.Date: yyyy-MM-dd
     */
    public Date getDateFromNtpServer() {
        NtpUtil ntpUtil = new NtpUtil();
        if (ntpUtil.getNtpServerDateTime() != null) {
            return localDateToSqlDate(ntpUtil.getNtpServerDateTime().toLocalDate());
        } else {
            LOGGER.warn("Error al obtener la fecha desde servidor NTP, retornando fecha de sistema.");
            return localDateToSqlDate(LocalDate.now());
        }
    }

    public Time getTimeFromNtpServer() {
        NtpUtil ntpUtil = new NtpUtil();
        LocalDateTime localDateTime = ntpUtil.getNtpServerDateTime();
        if (null != localDateTime) {
            return localTimeToSqlTime(localDateTime.toLocalTime());
        } else {
            LOGGER.warn("No se pudo obtener el horario desde servidor NTP, se retornará horario del sistema.");
            return localTimeToSqlTime(LocalTime.now());
        }
    }

    /**
     * Trata una fecha en formato yyyy-MM-dd a entero
     *
     * @return Entero: yyyyMMdd
     */
    public Integer getDateToInteger() {
        Date date = getDateFromNtpServer();
        try {
            return Integer.parseInt(date.toString().replace("-", ""));
        } catch (Exception e) {
            LOGGER.error("Error durante el tratamiento de la fecha a entero:\n" + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Convierte Java 8 LocalDate a Sql Date
     * @param date
     * @return Sql Date
     */
    public Date localDateToSqlDate(LocalDate date) {
        try {
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return Date.valueOf(formateador.format(date));
        } catch (DateTimeException e) {
            LOGGER.error("Error durante el tratamiento de la fecha a sql.Date" + e.getLocalizedMessage());
            return null;
        }
    }

    public Time localTimeToSqlTime(LocalTime time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            return Time.valueOf(formatter.format(time));
        } catch (DateTimeException e) {
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
    }

    public String dateToString(java.util.Date fecha){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaComoCadena = sdf.format(fecha);

        return fechaComoCadena.replace("/","");

    }

}
