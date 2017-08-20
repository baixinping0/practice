package com.baixinping.cvtepro.controller;

import java.util.List;

import javax.annotation.Resource;

import com.baixinping.cvtepro.entity.LogiscustomModel;
import com.baixinping.cvtepro.entity.LogisticsModel;
import com.baixinping.cvtepro.service.LogiscustomEbi;
import com.baixinping.cvtepro.service.LogisticsEbi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LogisticsController {
	@Resource
	LogisticsEbi logisticsEbi;
	@Resource
	LogiscustomEbi logiscustomEbi;

	@RequestMapping("logisticsList.action")
	public String list(Model model){
		List<LogisticsModel> logisticsList = logisticsEbi.find();
		model.addAttribute("logisticsList", logisticsList);
		return "logistics/list";
	} 
	@RequestMapping("logisticsInput.action")
	public String input(LogisticsModel logisticsModel, Model model){
		System.out.println("id   " + logisticsModel.getId());
		logisticsModel = logisticsEbi.get(logisticsModel.getId());
		model.addAttribute("logisticsModel", logisticsModel);
		//获取所有的物流公司
		List<LogiscustomModel> logiscustomList = logiscustomEbi.find();
		model.addAttribute("logiscustomList", logiscustomList);
		return "logistics/input";
	}
	@RequestMapping("logisticsSend.action")
	public String send(String logisticsId, String logiscustomId){
		logisticsEbi.send(logisticsId, logiscustomId);
		return "redirect:/logisticsList.action";
	}
}
