package com.thoughtworks.springbootemployee.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Aspect
@Component
public class LoggingAdvice {

    private Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.thoughtworks.springbootemployee.*.*.*(..))")
    public void myPointCut(){
    }

    @Around("myPointCut()")
//    @Around("@annotation(com.thoughtworks.springbootemployee.advice.IonlyWantThisMethod)")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String classNmae = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        Object object = proceedingJoinPoint.proceed();
        logger.info(format("method invoked: %s : %s() Arguments: %s", classNmae, methodName, mapper.writeValueAsString(array)));
        logger.info(format("%s : %s()Response: %s", classNmae, methodName,
                mapper.writeValueAsString(object)));
        return  object;
    }
}
