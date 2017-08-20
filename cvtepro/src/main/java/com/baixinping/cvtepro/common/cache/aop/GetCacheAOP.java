package com.baixinping.cvtepro.common.cache.aop;

import com.alibaba.fastjson.JSON;
import com.baixinping.cacheClient.cache.CacheClient;
import com.baixinping.cacheClient.cache.impl.CacheClientImpl;
import com.baixinping.cvtepro.common.cache.cache.Cache;
import com.baixinping.cvtepro.common.utils.num.UUIDUtils;
import com.baixinping.cvtepro.entity.CacheModel;
import com.baixinping.cvtepro.entity.DepModel;
import com.baixinping.cvtepro.entity.EmpModel;
import com.baixinping.cvtepro.entity.ProModel;
import com.baixinping.cvtepro.service.CacheEbi;
import com.baixinping.cvtepro.service.ProModelEbi;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@Component
@Aspect
 public class GetCacheAOP  {  
	@Resource
    private Cache cache;
    @Resource
    private ProModelEbi proModelEbi;
    @Resource
    private CacheEbi cacheEbi;

	CacheClient client = new CacheClientImpl();
    
//    public void setCache(Cache cache) {
//		this.cache = cache;
//	}
     /*
      * 此方法是给用户获取用户的部门集合的AOP
      * 作为一个设计人员，站的角度更高
      */
	@Around("execution(* com.baixinping.cvtepro.service.impl.DepEbo.listDepByEmp(..))")
	public List<DepModel> arountExecToEmpDep (ProceedingJoinPoint joinPoint) throws Throwable{
		if (!isUseCache(ProModel.EMP_DEPT_RTEE)){
			System.out.println("缓存关闭，开始从数据库获取数据");
			return (List<DepModel>)joinPoint.proceed();
		}

		EmpModel emp = (EmpModel) joinPoint.getArgs()[0];
    	//本地缓存
		List<DepModel> date = cache.getListFromCache(ProModel.EMP_DEPT_RTEE, emp.getId(),
				DepModel.class);
    	 if(date != null){
    	 	System.out.println("从本地redis得到数据");
    		 return date;
    	 }
    	 //缓存服务
		 System.out.println("开始从缓存服务获取数据");
    	 String string = client.get(emp.getId());
    	 if (string != null && !"".equals(string)){
			 List<DepModel> depModels = JSON.parseArray(string, DepModel.class);
			 return depModels;
		 }
		System.out.println("开始从数据库获取数据");
		return getForDataBaseAndSaveCache(joinPoint, emp);
     }

	/**
	 * 从数据库中获取数据并将数据保存在缓存中
	 * @param joinPoint
	 * @param emp
	 * @return
	 * @throws Throwable
	 */
	private List<DepModel> getForDataBaseAndSaveCache(ProceedingJoinPoint joinPoint,
										  EmpModel emp) throws Throwable {
		System.out.println("没有从reids获取到数据，开始访问数据库");
		Object result =  joinPoint.proceed();
		System.out.println("将从数据库中得到的数据保存到本地redis中");
		cache.setValueToCache(ProModel.EMP_DEPT_RTEE, emp.getId(), result);
		System.out.println("将从数据库中得到的数据保存到redis服务中");
		client.set(emp.getId(), JSON.toJSONString(result));
		//继续将缓存的元数据信息添加到数据库中
		//保存数据的元数据信息
		String methodKey = getMethodKey(joinPoint).trim();
		saveCacheSrcInfo(ProModel.EMP_DEPT_RTEE, emp.getId(), methodKey);
		return (List<DepModel>)result;
	}
	private boolean isUseCache(String model){
		boolean use = true;
		ProModel proModel = proModelEbi.getByName(model);
		//如果获取到信息，判断是否需要缓存，如果不需要，直接调用原方法，将结果返回。
		if(proModel != null && ProModel.CACHE_STATUS_CLOSE.equals(proModel.getCache_status()))
			use = false;
		return use;
	}


	private void saveCacheSrcInfo(String model, String key, String methodKey){
		ProModel proModel = proModelEbi.getByName(model);
		if(proModel == null){
			proModel = new ProModel();
			proModel.setId(UUIDUtils.getUuid());
			proModel.setModel_name(model);
			proModel.setCache_status(ProModel.CACHE_STATUS_OPEN);
			proModelEbi.insert(proModel);
		}
		CacheModel cacheModel = new CacheModel();
		cacheModel.setId(UUIDUtils.getUuid());
		cacheModel.setModel_code(proModel.getId());
	   	cacheModel.setCache_info_id(key);
	   	cacheModel.setCache_url(methodKey);
	   	cacheModel.setCache_update_time(new Date());
	   	cacheEbi.insert(cacheModel);
	}
     
     
     /**
      * 根据类名、方法名和参数值获取唯一的缓存键
      * @return 格式为 "包名.类名.方法名.参数类型.参数值"，类似 "your.package.SomeService.getById(int).123"
      */
     private String getMethodKey(JoinPoint joinPoint) {
    	 //获取方法签名
         MethodSignature ms = (MethodSignature) joinPoint.getSignature();
         Method method = ms.getMethod();
         Object target = joinPoint.getTarget();
         //获取类名
         String className = target.getClass().getName();
         //获取方法名
         String methodName = method.getName();
         //获取方法的参数
         Class<?>[] parameterTypes = method.getParameterTypes();
         String par = "(";
         if (parameterTypes != null && parameterTypes.length > 0) {
        	 for (Class<?> clazz : parameterTypes) {
        		 par += clazz.getName()+",";
     		}
        	 par = par.substring(0, par.length() - 1);
		}
         par += ")";
         return className + "." + methodName + par;
     }
 }  