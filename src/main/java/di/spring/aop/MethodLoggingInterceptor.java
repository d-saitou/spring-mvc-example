package di.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * AOP Advice implementation<br>
 * <p>
 *  This class uses Spring AOP to implement log output at the start and 
 *  end of a specific method.
 *  In order to use Aspect annotation, it is necessary to set the 
 *  configuration in the Spring configuration.<br>
 *  * See "aop: aspectj-autoproxy" tag in application-config.xml.
 * <p>
 *  This class implements Before annotation and After annotation within 
 *  AOP Advice which can be used.
 *  In addition, the AfterReturning annotation, AfterThrowing annotation 
 *  and Around annotation are commented as a reference.
 *  Refer to the startLog() method and endLog() method for specifying the 
 *  pointcut.
 */
@Aspect @Component @Slf4j
public class MethodLoggingInterceptor {
	
	/**
	 * Named pointcut (Controller class specification)
	 */
	@Pointcut("execution(* di..*Controller.*(..))")
	private void controllerPointCut() {	}
	
	/**
	 * Named pointcut (Service class specification)
	 */
	@Pointcut("execution(* di..*Service*.*(..))")
	private void servicePointCut() {}
	
	/**
	 * Output the method start log
	 * @param jp JoinPoint
	 */
	//@Before("execution(* di..*Controller.*(..))")			// Pointcut expression
	@Before("controllerPointCut()")							// Named pointcut (single)
	//@Before("controllerPointCut() || servicePointCut()")	// Named pointcut (multiple)
	public void startLog(JoinPoint jp) {
		log.info("method start:{}", getMethodName(jp));
	}
	
	/**
	 * Output the method end log
	 * @param jp JoinPoint
	 */
	//@After("execution(* di..*Controller.*(..))")			// Pointcut expression
	@After("controllerPointCut()")							// Named pointcut (single)
	//@After("controllerPointCut() || servicePointCut()")	// Named pointcut (multiple)
	public void endLog(JoinPoint jp) {
		log.info("method end:{}", getMethodName(jp));
	}
	
//	/**
//	 * Output the method end log (AfterReturning)
//	 * @param jp JoinPoint
//	 */
//	@AfterReturning("execution(* di..*Controller.*(..))")
//	public void endAfterReturningLog(JoinPoint jp) {
//		log.info("method end:{}", getMethodName(jp));
//	}
	
//	/**
//	 * Output the method end log (AfterThrowing)
//	 * @param jp JoinPoint
//	 * @param e RuntimeException
//	 */
//	@AfterThrowing(value = "execution(* di..*Controller.*(..))", throwing = "e")
//	public void endAfterThrowingLog(JoinPoint jp, RuntimeException e) {
//		log.error("method exception end:{}", getMethodName(jp));
//	}
	
//	/**
//	 * Output the method start and end log
//	 * @param jp ProceedingJoinPoint
//	 * @return method return value
//	 * @throws Throwable Throwable
//	 */
//	@Around("execution(* di..*Controller.*(..))")
//	public Object aroundLog(ProceedingJoinPoint jp) throws Throwable {
//		log.info("method start:{}", getMethodName(jp));
//		try {
//			Object result = jp.proceed();
//			log.info("method end:{}", getMethodName(jp));
//			return result;
//		} catch (Exception e) {
//			log.error("method exception end:{}", getMethodName(jp));
//			throw e;
//		}
//	}
	
	/**
	 * Get method name of JoinPoint
	 * @param jp JoinPoint
	 * @return method name 
	 */
	private String getMethodName(JoinPoint jp) {
		return jp.getTarget().getClass().toString()
				.replace("class ", "") + "#" + jp.getSignature().getName() + "()";
	}
	
}
