package com.jci.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("execution(* com.jci.controller..*(..)) && args(@RequestBody body,..)")
	public Object profileExecutionTime(ProceedingJoinPoint joinPoint, Object body) throws Throwable {

		long start = System.currentTimeMillis();
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String apiName = className + "." + methodName;

		Object result = joinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		logger.info("Time taken for completion of {} : {} ms", apiName, elapsedTime);

		return result;
	}
}
