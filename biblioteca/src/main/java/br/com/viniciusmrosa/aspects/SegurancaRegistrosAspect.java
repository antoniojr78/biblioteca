package br.com.viniciusmrosa.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SegurancaRegistrosAspect {

	/*@Before("execution(* br.com.viniciusmrosa.controller.*Controller.*(..))")
	public void tratarAlteração(JoinPoint joinPoint) throws Throwable{
		
	}*/
}
