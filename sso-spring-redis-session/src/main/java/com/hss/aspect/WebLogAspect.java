package com.hss.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * web层日志查看切面
 */
//@Aspect
//@Component
//@Order(100)
public class WebLogAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 横切点
     */
    @Pointcut("execution(public * com.hss.controller..*.*(..))")
    public void webLog() {

    }

    /**
     * 记录HTTP请求结束时的日志
     */
    /**
     * 记录HTTP请求结束时的日志
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info(">>>>>>>>>>Before");
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("PATH : " +  request.getServletPath());
        logger.info("METHOD : " + request.getMethod());
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            logger.info("PARAM----" + paraName+": "+request.getParameter(paraName));
        }
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "obj",pointcut = "webLog()")
    public void doAfterReturning(Object obj) throws Throwable {
        //处理完请求，返回内容
        logger.info(">>>>>>>>>>AfterReturning");
        logger.info("RESPONSE : " + JSON.toJSONString(obj));
    }

    @AfterThrowing(value = "webLog()",throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        logger.info(">>>>>>>>>>AfterThrowing");
        logger.info(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            logger.info("发生了空指针异常!!!!!");
        }else{
            logger.info("发生了未知异常!!!!!");
        }
    }

    @Around(value = "webLog()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info(">>>>>>>>>>Around");
        logger.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());

        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        Long takeTime = System.currentTimeMillis() - startTime;
        logger.info("耗时：" + takeTime);
        return obj;

    }

}
