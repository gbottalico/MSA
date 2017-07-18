package msa.infrastructure.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {
	
	private Logger getClassLogger(final JoinPoint joinPoint) {
		return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType().getName());
	}

	@Before("execution(* msa.web.controllers.*.*(..))")
	public void entryMethod(JoinPoint joinPoint) throws Throwable {
		Logger log =  getClassLogger(joinPoint);
		log.info("Inizio metodo "+joinPoint.getSignature().getName());
		Object[] signatureArgs = joinPoint.getArgs();
		for (Object arg : signatureArgs){
			if (arg !=null){
				log.debug(arg.toString());
			}
		}
	}
	
	@After("execution(* msa.web.controllers.*.*(..))")
	public void exitMethod(JoinPoint joinPoint) throws Throwable {
		Logger log =  getClassLogger(joinPoint);
		log.info("Fine metodo "+joinPoint.getSignature().getName());
	}
	
	@Before("execution(* msa.web.controllers.*.*(..))")
	public void entryMethodService(JoinPoint joinPoint) throws Throwable {
		Logger log =  getClassLogger(joinPoint);
		log.debug("Inizio metodo "+joinPoint.getSignature().getName()+ " service");
		Object[] signatureArgs = joinPoint.getArgs(); 
		for (Object arg : signatureArgs){
			if (arg !=null){
				log.debug(arg.toString());
			}
		}
	}
	
	@After("execution(* msa.application.service.*.*(..))")
	public void exitMethodService(JoinPoint joinPoint) throws Throwable {
		Logger log =  getClassLogger(joinPoint);
		log.debug("Fine metodo "+joinPoint.getSignature().getName()+ " service");
	}
}
