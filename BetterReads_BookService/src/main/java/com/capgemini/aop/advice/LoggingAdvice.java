package com.capgemini.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.capgemini.aop.advice.LoggingAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice 
{
	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);
	@Pointcut(value="execution(* com.example.demo.aop.advice.*.*.*(..) )")
	public void myPointCut()
	{
		
	}
	
	@Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable
    {	
		ObjectMapper mapper = new ObjectMapper();         //create object to print array in json
		String methodName = pjp.getSignature().getName();
 	    String className = pjp.getTarget().getClass().toString();
 	    Object[] array = pjp.getArgs();
 	     Object obj = pjp.proceed();
 	    log.info("Method invoked" +className+ " : " +  methodName+ "()" +"arguments :" +mapper.writeValueAsString(array));
        log.info(className+ " : " + "methodName"+ "()" +"Response :" +mapper.writeValueAsString(array));
        return obj; 
    }
}
