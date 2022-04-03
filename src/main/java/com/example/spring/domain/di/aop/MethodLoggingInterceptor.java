package com.example.spring.domain.di.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.example.spring.config.AppConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * Component that implements AOP advice.
 */
@Aspect
@Component
@Slf4j
public class MethodLoggingInterceptor {

	/**
	 * Named pointcut. (Controller class)
	 */
	@Pointcut("execution(* " + AppConstants.PKG_APPLICATION_DI + "..*Controller.*(..))")
	public void controllerPointCut() {}

	/**
	 * Named pointcut. (Service class)
	 */
	@Pointcut("execution(* " + AppConstants.PKG_DOMAIN_DI + "..*Service*.*(..))")
	public void servicePointCut() {
	}

	/**
	 * Output method start log.
	 * @param jp JoinPoint object.
	 */
	// @Before("execution(* " + Constants.JAVA_PKG_DI + ".*..*Controller.*(..))")
	@Before("controllerPointCut()") // Named pointcut (single)
	// @Before("controllerPointCut() || servicePointCut()") // Named pointcut (multiple)
	public void startLog(JoinPoint jp) {
		log.info("method start:{}", getMethodName(jp));
	}

	/**
	 * Output method end log.
	 * @param jp JoinPoint object.
	 */
	// @After("execution(* " + Constants.JAVA_PKG_DI + "..*Controller.*(..))")
	@After("controllerPointCut()") // Named pointcut (single)
	// @After("controllerPointCut() || servicePointCut()") // Named pointcut (multiple)
	public void endLog(JoinPoint jp) {
		log.info("method end:{}", getMethodName(jp));
	}

//	/**
//	 * Output method end log (AfterReturning)
//	 * @param jp JoinPoint object.
//	 */
//	@AfterReturning("execution(public * " + AppConstants.PKG_APPLICATION_DI + "..*Controller.*(..))")
//	public void endAfterReturningLog(JoinPoint jp) {
//		log.info("method end:{}", getMethodName(jp));
//	}
//
//	/**
//	 * Output method end log. (AfterThrowing)
//	 * @param jp JoinPoint object.
//	 * @param e  Throwable object.
//	 */
//	@AfterThrowing(
//			value = "execution(public * " + AppConstants.PKG_APPLICATION_DI + "..*Controller.*(..))", throwing = "e")
//	public void endAfterThrowingLog(JoinPoint jp, Throwable e) {
//		log.error("method exception end:{}", getMethodName(jp), e);
//	}
//
//	/**
//	 * Output method start and end log.
//	 * @param jp ProceedingJoinPoint object.
//	 * @return method return value.
//	 * @throws Throwable Throwable object.
//	 */
//	@Around("execution(public * " + AppConstants.PKG_APPLICATION_DI + "..*Controller.*(..))")
//	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
//		log.info("method start:{}", getMethodName(jp));
//		try {
//			Object result = jp.proceed();
//			log.info("method end:{}", getMethodName(jp));
//			return result;
//		} catch (Exception e) {
//			log.error("method exception end:{}", getMethodName(jp), e);
//			throw e;
//		}
//	}

	/**
	 * Get method name of JoinPoint.
	 * @param jp JoinPoint object.
	 * @return method name.
	 */
	private String getMethodName(JoinPoint jp) {
		return jp.getTarget().getClass().toString()
				.replace("class ", "") + "#" + jp.getSignature().getName() + "()";
	}

}
