import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;

import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;

import org.slf4j.MDC;

import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Aspect

@Configuration

public class CorelationIdAspect {

    private static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";

    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    @Before(value = "execution(* com.gaurav.services.AuthorizationService.*(..))")

    public void before(JoinPoint joinPoint) {

        final String correlationId = generateUniqueCorrelationId();

        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);

    }

    @After(value = "execution(* com.gaurav.services.AuthorizationService.*(..))")

    public void afterReturning(JoinPoint joinPoint) {

        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);

    }

    private String generateUniqueCorrelationId() {

        return UUID.randomUUID().toString();

    }

}
