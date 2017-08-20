package com.baixinping.cvtepro.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.baixinping.cvtepro.entity.DepModel;
import com.baixinping.cvtepro.entity.EmpModel;
import com.baixinping.cvtepro.service.DepEbi;
import com.baixinping.cvtepro.service.EmpEbi;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmpController{
	@Resource
	EmpEbi empEbi;
	@Resource
	DepEbi depEbi;
	
	/*redis单点登录：
	 * 用户登录成功后，将用户的数据保存在session中，每个用户都是一个单独的session。
	 * 单点登录：用户登录成功后，将用户从数据库中查询到的用户信息保存在redis中<用户id, 用户ip><用户ip， emp>
	 *1、用户通过用户名和密码在数据库中进行匹配用户信息
	 *2、匹配成功后，通过用户的id从redis获取当前正在登录的用户的ip
	 *3、如果不存在，则存储<用户id, 用户ip><用户ip， emp>
	 *4、如果存在，则重新设置<用户id, 用户ip>中的用户ip，删除已有的<用户ip， emp>，设置新的<用户ip， emp>
	 *5、将用户的信息存储在本地的session中。
	 *6、对用户的所有请求进行拦截，通过用户的ip从redis中获取用户的信息，如果返回值为空，则跳会登录页面，并给出掉线提醒。
	 *7、当用户自动注销后，将redis中<用户id, 用户ip><用户ip， emp>删除
	 *
	 */
	@RequestMapping("/login.action")
	public String login(EmpModel empModel, HttpSession session, HttpServletRequest request){
		EmpModel currentEmp = null;
		//判断传入的参数是否有效，如果参数无效，说明服务器重启，用户数据被清空，直接跳转到index
		if(StringUtils.isEmpty(empModel.getUser_name().trim())
				|| StringUtils.isEmpty(empModel.getUser_pwd().trim())
				){
			currentEmp = (EmpModel) session.getAttribute(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
			if (currentEmp == null)
				return "index";
		}
		//执行到此说明是真正的登录操作
		currentEmp = empEbi.getEmpByUserNameAndUserPawd(empModel.getUser_name(), empModel.getUser_pwd());
		List<DepModel> depList = null;
//		long start = System.currentTimeMillis();
//		for (int i = 0; i < 1000; i++){
//			System.out.println(i);
			depList = depEbi.listDepByEmp(currentEmp);
//		}
////
//		long time = System.currentTimeMillis()-start;
//		System.out.println("获取部门信息花费时间   " + (time/1000));

		session.setAttribute(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, currentEmp);
		session.setAttribute(EmpModel.EMP_DEP_LIST_SEARCH, depList);
		return "main";
	}

	/**
	 * 注销操作
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout.action")
	public String logout(HttpSession session){
		session.removeAttribute(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		return "index";
	}
}
