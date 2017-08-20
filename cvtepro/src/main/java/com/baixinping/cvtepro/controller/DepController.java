package com.baixinping.cvtepro.controller;


import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.baixinping.cvtepro.common.utils.format.CodeUtils;
import com.baixinping.cvtepro.entity.DepModel;
import com.baixinping.cvtepro.entity.EmpModel;
import com.baixinping.cvtepro.entity.MenuModel;
import com.baixinping.cvtepro.service.DepEbi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DepController {
	@Resource
	DepEbi depEbi;

	/**
	 * 页面上的ifram通过此方法获取menu菜单
	 * @param depq
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("depList.action")
	public String depList(DepModel depq, Model model) throws UnsupportedEncodingException{
		if(depq != null && depq.getDept_name() != null && !"".equals(depq.getDept_name().trim())){
			model.addAttribute("dept_name", depq.getDept_name());
		}
		return "menu";
	}

	/**
	 * menu菜单调用此方法对页面上的数据进行展示
	 * 第一次进入时候，调用showMenu()方法，传入的root参数的值是“source”，此时应当获取一级菜单并返回显示
	 * 当点击一级菜单的时候，调用showMenu()方法，传入的root参数的值是点击的一级菜单的id值
	 * 此时通过一级菜单的id获取对应的二级菜单并返回显示
	 * @param session
	 * @param response
	 * @param root 第一次是resuce，点击父菜单此字段传递的是父菜单的id
	 * @param dept_name
	 * @throws IOException
	 */
	@RequestMapping("showDep.action")
	public void depShow(HttpSession session, HttpServletResponse response, String root, String dept_name) throws IOException{
		List<MenuModel> menus = new ArrayList<>();
		if ("source".equals(root))
			menus = depEbi.getRootMenu(session, dept_name);
		else
			menus = depEbi.getLeavel2Menu(session,root);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(JSON.toJSONString(menus));
		writer.flush();
	}


}






























