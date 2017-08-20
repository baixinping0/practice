package com.baixinping.cvtepro.controller;


import java.util.List;

import javax.annotation.Resource;

import com.baixinping.cvtepro.entity.CacheModel;
import com.baixinping.cvtepro.service.CacheEbi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CacheController {
	@Resource
	CacheEbi cacheEbi;
	
	@RequestMapping("/cacheList.action")
	public String list(Model model){
		List<CacheModel> cacheList =  cacheEbi.find();
		model.addAttribute("cacheList", cacheList);
		return "cache/list";
		
	}
	@RequestMapping("/cacheInput.action")
	public String input(CacheModel cacheModel, Model model){
		if(cacheModel.getId() != null){
			cacheModel = cacheEbi.getById(cacheModel.getId());
			model.addAttribute("cacheModel", cacheModel);
		}
		return "cache/input";
	}
	@RequestMapping("cacheInsert.action")
	public String insert(CacheModel cacheModel){
		if(cacheModel.getId() != null &&  !"".equals(cacheModel.getId().trim())){
			System.out.println("更新***" + cacheModel.getId()+"888");
			cacheEbi.update(cacheModel);
		}else{
			System.out.println("添加");
			cacheEbi.insert(cacheModel);
		}
		return "redirect:/cacheList.action";
	}
	
}
