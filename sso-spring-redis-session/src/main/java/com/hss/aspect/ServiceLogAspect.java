package com.hss.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * service层日志查看切面
 */
@Aspect
@Component
@Order(200)
public class ServiceLogAspect {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 横切点
     */
    @Pointcut("execution(public * com.hss.service..*.*(..))")
    public void serviceLog() {

    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @Before("serviceLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        if(joinPoint == null){
            return;
        }
        /**
         * Signature 包含了方法名、申明类型以及地址等信息
         */
        String class_name = joinPoint.getTarget().getClass().getName();
        String method_name = joinPoint.getSignature().getName();
        //重新定义日志
        logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        logger.info("class_name = {}",class_name);
        logger.info("method_name = {}",method_name);

        /**
         * 获取方法的参数值数组。
         */
        Object[] method_args = joinPoint.getArgs();

        /**
         * 获取方法参数名称
         */
        String[] paramNames = getFieldsName(class_name, method_name);

        /**
         * 打印方法的参数名和参数值
         */
        logParam(paramNames,method_args);

    }

    @AfterReturning(returning = "obj",pointcut = "serviceLog()")
    public void doAfterReturning(Object obj) throws Throwable {
        //处理完请求，返回内容
        logger.info(">>>>>>>>>>AfterReturning");
        logger.info("RETURN : " + JSON.toJSONString(obj));
    }

    @AfterThrowing(value = "serviceLog()",throwing = "exception")
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

    @Around(value = "serviceLog()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        logger.info(">>>>>>>>>>Around");
        logger.info("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());

        long startTime = System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        Long takeTime = System.currentTimeMillis() - startTime;
        logger.info("耗时：" + takeTime);
        return obj;

    }

    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     * @param paramsArgsName    方法参数名数组
     * @param paramsArgsValue   方法参数值数组
     */
    private void logParam(String[] paramsArgsName,Object[] paramsArgsValue){
        if(paramsArgsName.length <=0 || paramsArgsValue.length <= 0){
            logger.info("该方法没有参数");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<paramsArgsName.length;i++){
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];
            buffer.append(name +" = ");
            if(isPrimite(value.getClass())){
                buffer.append(value + "  ,");
            }else {
                buffer.append(value.toString() + "  ,");
            }
        }
        logger.info(buffer.toString());
    }

    /**
     * 判断是否为基本类型：包括String
     * @param clazz clazz
     * @return  true：是;     false：不是
     */
    private boolean isPrimite(Class<?> clazz){
        if (clazz.isPrimitive() || clazz == String.class){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 使用javassist来获取方法参数名称
     * @param class_name    类名
     * @param method_name   方法名
     * @return
     * @throws Exception
     */
    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if(attr == null){
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i=0;i<paramsArgsName.length;i++){
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }

}
