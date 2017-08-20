package com.baixinping.cvtepro.controller;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.baixinping.cvtepro.entity.ArticleModel;
import com.baixinping.cvtepro.service.ArticleEbi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController{
	@Resource
	ArticleEbi articleEbi;

	/**
	 * 插入或者更新一条文章数据
	 * 前端页面中插入和更新使用的是同一个页面，所以此处通过id进行判断是进行插入数据还是更新数据
	 * @param articleModel
	 * @return
	 */
	@RequestMapping("/articleInsert.action")
	public String insert(ArticleModel articleModel){

		if(StringUtils.isEmpty(articleModel.getId().trim()))
			articleEbi.insert(articleModel);
		else
			articleEbi.update(articleModel);
		return "redirect:/articleList.action";
	}

	/**
	 * 根据id删除一条文章数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/articleDelete.action")
	public String delete(String id){
		articleEbi.deleteById(id);
		return "redirect:/articleList.action";
	}

	/**
	 *获取文章数据集合
	 * @param model
	 * @return
	 */
	@RequestMapping("/articleList.action")
	public String list(Model model)
	{
		List<ArticleModel> articleList = articleEbi.list();
		model.addAttribute("articleList", articleList);
		return JSON.toJSONString(articleList);
	}

	/**
	 * 跳转输入页面或者更新页面
	 * 输入界面和更新界面使用的是同一个页面，所以通过id进行判断下一步是录入操作还是更新操作
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/articleInput.action")
	public String input(Model model, String id){
		//判断id是否为空
		ArticleModel articleModel = new ArticleModel();
		if(id != null){
			articleModel = articleEbi.get(id);
		}
		model.addAttribute("articleModel", articleModel);
		return "article/input";
	}
}
	
	
	
	
	
	