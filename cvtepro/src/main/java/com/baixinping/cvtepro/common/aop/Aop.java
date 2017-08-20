package com.baixinping.cvtepro.common.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpSession;

import com.baixinping.cvtepro.entity.EmpModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class Aop {
	public String arountOperFilter(ProceedingJoinPoint joinPoint) throws Throwable{
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    	HttpSession session=attr.getRequest().getSession(true);
    	EmpModel user = (EmpModel)session.getAttribute(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
    	//获取类名
    	String className = joinPoint.getTarget().getClass().getName();
    	//获取调用的方法名
    	MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        String methodName = method.getName();
        String key = className + "." + methodName;
        System.out.println(key);
        if(user != null || "com.bxp.cvtep.auth.home.controller.HomeController.login".equals(key)
        		|| "com.bxp.cvtep.auth.emp.controller.EmpController.login".equals(key))
    		return (String)joinPoint.proceed();
    	else
    		return "/index.jsp";
	}
}
