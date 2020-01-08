package com.cepardov.cms.util;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class NtpUtil {

    private static final Logger LOGGER = LogManager.getLogger(NtpUtil.class.getName());

    /**
     * Obtiene la hora desde un servidor NTP, si no se puede obtener retorna la hora local
     * @return Java 8 LocalDateTime Fecha y hora.
     */
    public LocalDateTime getNtpServerDateTime() {
        try {
            NTPUDPClient client = new NTPUDPClient();
            client.open();
            InetAddress inetAddress = InetAddress.getByName("ntp.shoa.cl");
            TimeInfo timeInfo = client.getTime(inetAddress);
            timeInfo.computeDetails();
            return timeInfo.getMessage().getTransmitTimeStamp().getDate().toInstant().atZone(ZoneId.of("America/Santiago")).toLocalDateTime();
        } catch (Exception e) {
            LOGGER.error("No se pudo obtener fecha y hora desde servidor NTP\n" + e.getLocalizedMessage());
            return null;
        }
    }
}
