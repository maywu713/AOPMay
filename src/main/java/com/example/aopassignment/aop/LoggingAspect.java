package com.example.aopassignment.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* findAll())")
    public void logWithExecution() {}

    @Around("com.example.aopassignment.aop.LoggingAspect.logWithExecution()")
    public Object executionTimeAdviceExecution(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();

        return getObject(pjp, startTime, className, methodName);
    }

    private Object getObject(ProceedingJoinPoint pjp, long startTime, String className, String methodName) throws Throwable {
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.println("ClassName: "+className);
        System.out.println("MethodName: "+methodName);
        System.out.println("Result: "+result);

        log.info(className+"."+methodName+" execution time: "+elapsedTime+" ms");
        return result;
    }

    @Pointcut("this(org.springframework.data.repository.Repository)")
    public void logWithThis() {}
    @Around("com.example.aopassignment.aop.LoggingAspect.logWithThis()")
    public Object executionTimeAdviceThis(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        return getObject(pjp, startTime, className, methodName);
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logAllRequestAndResponse() {}

    @Before("com.example.aopassignment.aop.LoggingAspect.logAllRequestAndResponse()")
    public void withinImpl2BeforeAdvice(JoinPoint joinPoint){
        log.info("\n\nBefore advice: " + joinPoint.getSignature());
    }

    @After("com.example.aopassignment.aop.LoggingAspect.logAllRequestAndResponse()")
    public void withinImpl2AfterAdvice(JoinPoint joinPoint){
        log.info("\n\nAfter Advice: "+ joinPoint.getSignature());
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public void methodWithAnnotationExceptionHandler() {}

    @After("com.example.aopassignment.aop.LoggingAspect.methodWithAnnotationExceptionHandler()")
    public void AfterMethodWithAnnotationExceptionHandler(JoinPoint joinPoint) {
        System.out.println("Defined Exception");
        log.info("\n\nAfter Advice MethodWithAnnotationExceptionHandler: " + joinPoint.getSignature());
    }
}
