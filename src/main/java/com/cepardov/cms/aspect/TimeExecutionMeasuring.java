package com.cepardov.cms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeExecutionMeasuring {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeExecutionMeasuring.class);

    @Around("@annotation(com.cepardov.cms.annotation.TimeExecutionMeasuring)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long timeInit = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();
        long tiempoEjecucion = (System.currentTimeMillis() - timeInit);
        if(joinPoint.getSignature().getName().equals("generarXmlOrdenNueva")){
            LOGGER.info("Method: "+joinPoint.getSignature().getName() + " Time excecution:" + tiempoEjecucion + "ms");
        } else {
            LOGGER.debug("Method: "+joinPoint.getSignature().getName() + " Time excecution:" + tiempoEjecucion + "ms");
        }
        return proceed;
    }
}
