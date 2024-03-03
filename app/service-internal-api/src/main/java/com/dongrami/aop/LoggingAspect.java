package com.dongrami.aop;

import com.dongrami.exception.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class LoggingAspect {
    private final ObjectMapper objectMapper;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {
    }

    @Around("controller()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String uuid = UUID.randomUUID().toString();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.now();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Method method = getMethod(joinPoint);

        StringBuilder logBuilder = new StringBuilder("\n");

        logBuilder.append("---------------Request Start (").append(uuid).append(")---------------").append("\n");
        logBuilder.append("Time       : ").append(startDateTime.format(dateTimeFormatter)).append("\n");
        logBuilder.append("Source     : ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append("\n");
        logBuilder.append("Method     : ").append(request.getMethod()).append("\n");
        logBuilder.append("Path       : ").append(request.getRequestURI()).append("\n");
        logBuilder.append("Query      : ").append(request.getQueryString()).append("\n");

        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder paramsBuilder = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            paramsBuilder.append(entry.getKey()).append(":[");
            String params = String.join(", ", entry.getValue());
            paramsBuilder.append(params).append("], ");
        }
        logBuilder.append("Params     : ").append(paramsBuilder).append("\n");

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation.annotationType().getSimpleName().equals("RequestBody")) {
                    logBuilder.append("Body       : ").append(objectMapper.writeValueAsString(joinPoint.getArgs()[i])).append("\n");
                    break;
                }
            }
        }
        logBuilder.append("---------------Request End   (").append(uuid).append(")---------------").append("\n");

        log.info(logBuilder.toString());

        Object result = joinPoint.proceed();

        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            LocalDateTime endDateTime = LocalDateTime.now();
            long processTime = endDateTime.toInstant(ZoneOffset.UTC).toEpochMilli() - startDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();

            logBuilder = new StringBuilder("\n");

            logBuilder.append("---------------Response Start (").append(uuid).append(")---------------").append("\n");
            logBuilder.append("Time         : ").append(endDateTime.format(dateTimeFormatter)).append("\n");
            logBuilder.append("Process Time : ").append(processTime).append(" ms").append("\n");
            logBuilder.append("Status       : ").append(response.getStatus()).append("\n");
            logBuilder.append("Body         : ").append(getRequestBody(result)).append("\n");
            logBuilder.append("---------------Response End   (").append(uuid).append(")---------------").append("\n");

            log.info(logBuilder.toString());

        } catch (BaseException e) {
            log.debug(e.getMessage());
        }

        return result;
    }

    private String getRequestBody(Object result) {
        try {
            return objectMapper.readTree(objectMapper.writeValueAsString(result)).get("body").toString();
        } catch (Exception e) {
            return "";
        }
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

}
