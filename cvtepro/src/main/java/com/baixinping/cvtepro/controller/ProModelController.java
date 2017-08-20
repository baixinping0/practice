package com.baixinping.cvtepro.controller;

import java.util.List;

import javax.annotation.Resource;

import com.baixinping.cvtepro.entity.ProModel;
import com.baixinping.cvtepro.service.ProModelEbi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProModelController {
	@Resource
	ProModelEbi proModelEbi;
	
	@RequestMapping("/proModelList.action")
	public String list(Model model){
		List<ProModel> proModelList = proModelEbi.find();
		model.addAttribute("proModelList", proModelList);
		return "promodel/list";
	}
	@RequestMapping("/proModelInput.action")
	public String input(ProModel proModel,Model model){
		proModel = proModelEbi.get(proModel.getId());
		model.addAttribute("proModel", proModel);
		return "promodel/input";
	}
	@RequestMapping("/proModelUpdate.action")
	public String update(ProModel proModel){
		proModelEbi.update(proModel);
		return "redirect:/proModelList.action";
	}
	
}
